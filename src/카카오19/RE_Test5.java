package 카카오19;

import java.util.*;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 매칭 점수.
 */
public class RE_Test5 {
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

        // 1. word 는 대소문자를 구분하지 않는다.
        word = word.toLowerCase();
        int index = 0;
        for (String page : pages) {
            page = page.toLowerCase(); // 페이지의 값도 대소문자를 구분하지 않는다.

            // 2. meta 태그를 먼저 찾는다.
            int start = 0, mid = 0, end = 0;
            while (mid <= start) {
                start = page.indexOf("<meta", start + 1);
                end = page.indexOf(">", start);
                mid = page.lastIndexOf("https://", end);
            }

            // 3. 그 안에서 url 을 찾는다.
            // url 의 종료 위치는 " 따옴표가 된다.
            end = page.indexOf("\"", mid);
            String url = page.substring(mid, end);

            start = page.indexOf("<body>", end);
            int basic = 0;
            for (int positionLeft = start; ; ) {
                positionLeft = page.indexOf(word, positionLeft + 1);
                if (positionLeft == -1) break;

                if (!Character.isLetter(page.charAt(positionLeft - 1)) && !Character.isLetter(page.charAt(positionLeft + size))) {
                    basic++;
                    positionLeft += size;
                }
            }

            int link = 0;
            // 외부 링크를 구하기 위해서 다음 위치부터 찾아야 한다.
            // 그렇지 않으면 무한 루프!
            for (int positionLeft = start; ; ) {
                positionLeft = page.indexOf("<a href", positionLeft + 1);
                if (positionLeft == -1) break;
                link++;
            }

            map.put(url, index);
            list.add(new Page(index, basic, link, (double) basic));
            index++;
        }

        for (Page p : list) System.out.println(p);

        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            // 이 웹 페이지에 연결된 다른 링크가 여러 개 있을 수 있기 때문에 반복문을 돌려서 찾아야 한다.
            for (int start = 0, end = 0; ; ) {
                start = page.indexOf("<a href", end);
                if (start == -1) break;

                start = page.indexOf("https://", start);
                end = page.indexOf("\"", start);
                String linkUrl = page.substring(start, end);
                Integer value = map.get(linkUrl);

                if (value != null) {
                    list.get(value).score += (double) list.get(i).basic / list.get(i).link;
                }
            }
        }

        list.sort(cmp);

        return list.get(0).index;
    }

    private static Comparator<Page> cmp = new Comparator<Page>() {
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

        public Page(int index, int basic, int link, double score) {
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
