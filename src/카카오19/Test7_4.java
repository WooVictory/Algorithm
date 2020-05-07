package 카카오19;

import java.util.*;

/**
 * created by victory_woo on 2020/04/29
 * 카카오 19 기출.
 * 매칭 점수.
 */
public class Test7_4 {
    public static void main(String[] args) {
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        String word = "blind";
        System.out.println(solution(word, pages));

        String[] pages2 = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        String word2 = "Muzi";
        System.out.println(solution(word2, pages2));
    }

    public static int solution(String word, String[] pages) {
        int size = word.length();
        // map : key = url, value = index
        Map<String, Integer> pageMap = new HashMap<>();

        // 페이지의 정보를 저장한다.
        List<Page> pageList = new ArrayList<>();

        // 대소문자를 구별하지 않으므로.
        word = word.toLowerCase();
        for (int i = 0; i < pages.length; i++) {
            String s = pages[i] = pages[i].toLowerCase();

            // meta 태그를 먼저 찾고 다음으로 url 주소를 얻는다.
            int mid = 0, positionLeft = 0, positionRight = 0;
            while (mid <= positionLeft) {
                positionLeft = s.indexOf("<meta", positionLeft + 1);
                positionRight = s.indexOf(">", positionLeft);
                mid = s.lastIndexOf("https://", positionRight);
            }

            positionRight = s.indexOf("\"", mid);
            String url = s.substring(mid, positionRight);

            // body 태그 안에서 검색어가 해당 웹 페이지에 몇번 등장하는 지 찾아서 기본 점수를 센다.
            positionLeft = s.indexOf("<body>", positionRight);
            int basic = 0;
            for (int start = positionLeft; ; ) {
                start = s.indexOf(word, start + 1);
                if (start == -1) break;

                // 해당 검색어 앞뒤로 다른 알파벳 단어가 붙어있으면 매칭되지 않음.
                if (!Character.isLetter(s.charAt(start - 1)) && !Character.isLetter(s.charAt(start + size))) {
                    basic++;
                    start += size;
                }
            }

            // 링크 점수를 센다.
            int link = 0;
            for (int start = positionLeft; ; ) {
                start = s.indexOf("<a href", start + 1);
                if (start == -1) break;

                link++;
            }

            // map, 리스트에 저장한다.
            pageMap.put(url, i);
            pageList.add(new Page(i, basic, link, (double) basic));
        }

        // a -> b 참조한다고 가정!
        // 다음으로 (a)웹 페이지가 가지고 있는 참조링크를 찾아내서 그 (b)참조 페이지의 매칭 점수를
        // (a) 참조하고 있는 페이지의 기본 점수와 링크 점수를 이용해 업데이트한다.
        for (int i = 0; i < pages.length; i++) {
            String s = pages[i];
            for (int positionLeft = 0, positionRight = 0; ; ) {
                positionLeft = s.indexOf("<a href", positionRight);
                if (positionLeft == -1) break;

                positionLeft = s.indexOf("https://", positionLeft);
                positionRight = s.indexOf("\"", positionLeft);
                String linkUrl = s.substring(positionLeft, positionRight);

                Integer value = pageMap.get(linkUrl);
                if (value != null) {
                    pageList.get(value).score += (double) pageList.get(i).basic / pageList.get(i).link;
                }
            }
        }

        pageList.sort(comp);

        // 정렬을 통해서 맨 앞에 있는 원소의 인덱스가 정답이 된다.
        return pageList.get(0).index;
    }

    // 매칭 점수가 같으면 인덱스 번호가 작은 순으로 정렬.
    // 그렇지 않은 경우, 매칭 점수가 큰 순으로 정렬.
    private static Comparator<Page> comp = new Comparator<Page>() {
        @Override
        public int compare(Page a, Page b) {
            if (a.score == b.score) return a.index - b.index;
            else if (a.score < b.score) return 1;
            else return -1;
        }
    };

    // 페이지의 정보를 저장한다.
    static class Page {
        int index; // 인덱스.
        int basic, link; // 기본 점수와 링크 수.
        double score; // 매칭 점수.

        Page(int index, int basic, int link, double score) {
            this.index = index;
            this.basic = basic;
            this.link = link;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Page{" +
                    "index=" + index +
                    ", basic=" + basic +
                    ", link=" + link +
                    ", rate=" + score +
                    '}';
        }
    }
}
