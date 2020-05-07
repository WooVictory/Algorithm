package 카카오19;

import java.util.*;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 매칭 점수.
 */
public class RE_Test5_1 {
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
        Map<String, Integer> map = new HashMap<>();
        List<Page> list = new ArrayList<>();

        // 1. 검색어가 대소문자를 구분하지 않기 때문에 소문자로 변환.
        word = word.toLowerCase();
        for (int i = 0; i < pages.length; i++) {
            // 2. 웹 페이지의 내용 중 텍스트도 모두 소문자로 변경한다.
            String page = pages[i] = pages[i].toLowerCase();

            // 3. <meta 태그를 먼저 찾는다.
            // 그 안에서 url 을 찾아낸다.
            int start = 0, mid = 0, end = 0;
            while (mid <= start) {
                start = page.indexOf("<meta", start + 1);
                end = page.indexOf(">", start);
                mid = page.lastIndexOf("https://", end);
            }

            // url 의 종료는 큰 따옴표(")가 되며, url 을 구한다.
            end = page.indexOf("\"", mid);
            String url = page.substring(mid, end);

            // 4. 다음으로 <body> 태그의 위치를 찾고, 이 안에서 검색어와 일치하는 텍스트가 몇 개인지
            // 찾아서 기본 점수를 카운트 한다.
            start = page.indexOf("<body>", end);
            int basic = 0;
            for (int positionLeft = start; ; ) {
                // <body> 안에서 word 와 같은 텍스트의 위치를 찾는다.
                positionLeft = page.indexOf(word, positionLeft + 1);
                if (positionLeft == -1) break; // 없다면 -1을 반환.

                // 검색어와 일치하는 단어의 위치를 찾았다면, 해당 단어 앞뒤로 다른 알파벳이 존재하면 매칭되지 않는다.
                // 따라서 앞,뒤로 알파벳이 존재하지 않는지 확인한다.
                if (!Character.isAlphabetic(page.charAt(positionLeft - 1)) && !Character.isAlphabetic(page.charAt(positionLeft + size))) {
                    basic++;
                    positionLeft += size; // 다음 위치를 검색어 길이만큼 이동하여 시작하도록 한다.
                }
            }

            // 5. 외부 링크 수를 구한다.
            int link = 0;
            for (int positionLeft = start; ; ) {
                positionLeft = page.indexOf("<a href", positionLeft + 1);
                if (positionLeft == -1) break;
                link++;
            }

            // 링크를 Key 로, 인덱스를 Value 로 하여 map 에 저장한다.
            map.put(url, i);
            // 리스트에 page 의 정보를 저장한다.
            list.add(new Page(i, basic, link, (double) basic));
        }

        for (Page page : list) System.out.println(page);

        // 페이지를 돌면서 외부 링크를 확인하여 링크 점수를 구한 뒤, 매칭 점수를 업데이트 한다.
        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];

            for (int start = 0, end = 0; ; ) {
                start = page.indexOf("<a href", start);
                if (start == -1) break;

                // 링크를 찾는다.
                start = page.indexOf("https://", start);
                end = page.indexOf("\"", start);

                // 해당 링크를 찾아, map 에서 이 링크가 가리키는 웹 페이지의 인덱스를 얻어온다.
                String linkUrl = page.substring(start, end);
                Integer value = map.get(linkUrl);

                // 인덱스가 존재하면, 그 페이지의 매칭 점수를 가리키는 페이지의 기본 점수와 링크 점수로 매칭 점수를 업데이트 한다.
                if (value != null) {
                    list.get(value).score += (double) list.get(i).basic / list.get(i).link;
                }
            }
        }

        // list 를 정렬한다.
        list.sort(comp);
        return list.get(0).index;
    }

    // 매칭 점수가 같다면 인덱스 번호가 낮은 순으로 정렬하고, 그렇지 않다면 매칭 점수가 높은 순으로 정렬한다.
    private static Comparator<Page> comp = new Comparator<Page>() {
        @Override
        public int compare(Page a, Page b) {
            if (a.score == b.score) return a.index - b.index;
            else if (a.score < b.score) return 1;
            else return -1;
        }
    };

    static class Page {
        int index;
        int basic, link;
        double score;

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
                    ", score=" + score +
                    '}';
        }
    }
}
