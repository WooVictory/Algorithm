package 카카오19;

import java.util.*;

/**
 * created by victory_woo on 2020/04/28
 * 카카오 19 기출.
 * 매칭 점수.
 */
public class Test7_re {
    public static void main(String[] args) {
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        String word = "blind";

        String[] pages2 = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        String word2 = "Muzi";
        System.out.println(solution(word, pages));
        System.out.println(solution(word2, pages2));
    }

    // 매칭 점수를 기준으로 큰 값이 먼저 오도록 하여 내림 차순 정렬!
    // 매칭 점수가 같다면 인덱스 번호가 작은게 먼저 오도록 하여 오름 차순 정렬!
    private static Comparator<Page> comp = (a, b) -> {
        if (a.matchingScore == b.matchingScore) return a.index - b.index;
        else if (a.matchingScore < b.matchingScore) return 1;
        else return -1;
    };

    private static int solution(String word, String[] pages) {
        int answer = 0;
        int size = word.length();
        word = word.toLowerCase();
        Map<String, Integer> pageMap = new HashMap<>();
        List<Page> pageList = new ArrayList<>();

        for (int i = 0; i < pages.length; i++) {
            pages[i] = pages[i].toLowerCase();
            String s = pages[i];

            // meta 태그 안의 url 의 위치를 찾는다.
            int mid = 0, positionLeft = 0, positionRight = 0;
            while (mid <= positionLeft) {
                positionLeft = s.indexOf("<meta", positionLeft + 1);
                positionRight = s.indexOf(">", positionLeft);
                // url 은 뒤에서부터 찾는다.
                mid = s.lastIndexOf("https://", positionRight);
            }

            // url 의 종료 위치를 찾아서 url 을 구한다.
            positionRight = s.indexOf("\"", mid);
            String url = s.substring(mid, positionRight);

            // body 태그를 찾고, 그 안에서 웹 페이지가 검색어를 몇 개나 가지고 있는지 확인한다.
            positionLeft = s.indexOf("<body>", positionRight);
            int basic = 0;
            for (int start = positionLeft; ; ) {
                start = s.indexOf(word, start + 1);
                if (start == -1) break;

                if (!Character.isLetter(s.charAt(start - 1)) && !Character.isLetter(s.charAt(start + size))) {
                    basic++;
                    start += size;
                }
            }

            // 외부 링크 수를 찾는다.
            int link = 0;
            for (int start = positionLeft; ; ) {
                start = s.indexOf("<a href", start + 1);
                if (start == -1) break;

                link++;
            }

            pageMap.put(url, i);
            pageList.add(new Page(i, basic, link, (double) basic));
        }

        // 외부 링크를 통한 매칭 점수를 구해서 넣는다.
        for (int i = 0; i < pages.length; i++) {
            String s = pages[i];

            // 하이퍼링크 태그의 위치를 찾아준다.
            for (int positionLeft = 0, positionRight = 0; ; ) {
                positionLeft = s.indexOf("<a href", positionRight);
                if (positionLeft == -1) break;

                // url 의 정보를 찾아낸다.
                positionLeft = s.indexOf("https://", positionLeft);
                positionRight = s.indexOf("\"", positionLeft);
                String linkUrl = s.substring(positionLeft, positionRight);

                Integer index = pageMap.get(linkUrl);
                if (index != null) {
                    pageList.get(index).matchingScore += (double) pageList.get(i).basic / pageList.get(i).link;
                }
            }
        }

        pageList.sort(comp);

        return pageList.get(0).index;
    }

    static class Page {
        int index;
        int basic, link;
        double matchingScore;

        Page(int index, int basic, int link, double matchingScore) {
            this.index = index;
            this.basic = basic;
            this.link = link;
            this.matchingScore = matchingScore;
        }
    }
}
