package 카카오19;

import java.util.*;

/**
 * created by victory_woo on 2020/04/28
 * 카카오 19 기출.
 * 매칭 점수.
 */
public class Test7 {
    public static void main(String[] args) {
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        String word = "blind";
        System.out.println(solution(word, pages));
    }

    public static int solution(String word, String[] pages) {
        int size = word.length(); // 검색어의 길이 저장.
        // 외부 링크를 통해서 어떤 페이지를 링킹하는지 찾아내야 함.
        // url에 해당하는 페이지 인덱스를 쉽게 찾기 위해서 map 을 이용해서 쉽게 찾도록 한다.
        // url을 key로 하고 index 를 value 로 한다.
        Map<String, Integer> pageMap = new HashMap<>();

        // 각 페이지에 대한 정보를 리스트로 유지하면서 추후에 정렬을 할 때, 편하게 할 수 있다.
        // 매칭 점수가 높은 페이지를 찾을 수 있다.
        List<Page> pageList = new ArrayList<>();

        // 대소문자 구별을 하지 않기 때문에 검색어 word, page 의 값을 모두 소문자로 바꾼다.
        word = word.toLowerCase();
        for (int i = 0; i < pages.length; i++) {
            String s = pages[i] = pages[i].toLowerCase();
            // 연산의 편의를 위해서!

            // meta 태그 안의 url 을 찾는 과정이다.
            // meta 태그 안에서 url 을 찾지 못했다면, 다음 meta 태그를 찾아 그 안에 있는
            // url 을 찾을 때까지 반복한다.
            int mid = 0, positionLeft = 0, positionRight = 0;
            while (mid <= positionLeft) {
                // <meta 태그를 찾는다. 2번째 파라미터는 검색을 시작하는 위치!
                // <meta 태그의 시작은 positionLeft 값에 저장한다.
                positionLeft = s.indexOf("<meta", positionLeft + 1);
                positionRight = s.indexOf(">", positionLeft);
                // meta 태그의 끝을 찾는다. 이는 닫는 태그를 찾으면 되고, 매타 태그의 시작에서부터!

                // 이제 meta 태그 안의 url 을 찾을 것이다. url 을 mid 에 저장한다.
                // url 은 뒤에서부터 찾는다.
                mid = s.lastIndexOf("https://", positionRight);

                // url 이 메타 태그 안에 있다고 하면 원하는 url을 찾은 것!
                // 아닌 경우도 있음.
                // url 이 positionLeft 보다 작다고 하면, 이는 meta 태그 안에 있는 url 이 아니라는 것이다.
                // 하지만, 우리가 작은 url 이 meta 태그 안에 있다고 하면 while 의 조건문은 false 가 된다. 그 url 을 사용하면 된다.
            }

            // url 마지막 위치를 구한다. 이는 mid 부터 시작해서 "(큰따옴표)를 만날 때까지 검색한다.
            // url 의 마지막이 positionRight 에 들어간다.
            positionRight = s.indexOf("\"", mid);
            // url 을 구한다.
            String url = s.substring(mid, positionRight);

            // 검색어를 찾아서 카운팅해야 한다.
            // body 부터 찾으면 된다. 처음부터 찾을 필요는 없다. meta 태그 다음에 body 가 있을테니까!
            // positionLeft 가 기준이 되서 여기부터 검색어를 하나씩 찾아서 카운팅한다.
            positionLeft = s.indexOf("<body>", positionRight);
            int basic = 0;
            for (int start = positionLeft; ; ) {
                start = s.indexOf(word, start + 1);
                // 검색어를 찾고 매번 for 문이 반복될 때마다 앞으로 진행시키기 위해 start + 1 을 해서 찾기 시작한다.
                // 찾았으면 0 이상의 인덱스가 반환되고, 못찾으면 -1이 반환됨.
                if (start == -1) break;

                // 찾은 경우. 대신, 검색어의 앞 뒤로 알파벳이 붙어있으면 안됨. 매칭안됨!
                // 기호나 공백으로 구별이 되어야 함. 그래야 검색어와 매칭되는 단어!
                // 그래서 찾은 검색어의 시작이 start에 들어있다.
                // 따라서 start 의 바로 앞 문자가 알파벳이 아니어야 하고, 바로 다음 문자는 start + size(검색어 길이)가 알파벳이 아니어야 한다.
                if (!Character.isLetter(s.charAt(start - 1)) && !Character.isLetter(s.charAt(start + size))) {
                    basic++;
                    start += size;
                    // start 는 찾은 검색어의 길이만큼 증가시켜서 반복하면 된다.
                }
            }

            // for 문이 끝나면 검색어 개수만큼 basic 값이 증가한다. -> 기본 점수 구함.

            int link = 0; // 외부 링크 수를 찾는다.
            // positionLeft 는 사용하지 않았으므로 여전히 body 부터 시작한다.
            for (int start = positionLeft; ; ) {
                start = s.indexOf("<a href", start + 1);
                if (start == -1) break; // 더 이상 하이퍼링크가 없음.
                link++; // 아니면 하이퍼링크를 찾은 것이므로 증가시켜준다.
            }

            // 지금은 매칭 점수 구할 수 없음. 링크 점수를 구할 수 없기 때문에
            // 나머지 모든 페이지에 대한 기본 점수와 외부 링크 수가 있어야 링크 점수를 구할 수 있음.
            // 따라서 현재 페이지의 url에 해당하는 값을 key 로 하고, 페이지에 대한 인덱스를 value 로 하여
            // 맵에 저장한다. -> 이를 통해 나중에 링크 점수를 계산한다.
            pageMap.put(url, i);

            // 페이지 정보를 추가한다.
            pageList.add(new Page(i, basic, link, (double) basic));
        }

        // 이제 링크 점수를 구한다.
        for (int i = 0; i < pages.length; i++) {
            String s = pages[i];
            for (int posl = 0, posr = 0; ; ) {
                // 하이퍼링크를 찾는다. 이 하이퍼 링크가 어떤 페이지를 참조하고 있는지 봐야하기 때문에!
                posl = s.indexOf("<a href", posr);
                if (posl == -1) break;

                // 하이퍼링크 안에 있는 url 을 찾아서 그 시작점이 posl에 저장된다.
                // 끝 지점을 구한다. 그래야 substring 을 할 수 있음.
                posl = s.indexOf("https://", posl);
                posr = s.indexOf("\"", posl);
                String linkUrl = s.substring(posl, posr);

                // 해당하는 url이 두번째 파라미터로 주어진 페이지 중에 있다고 하면 그 페이지의 매칭 점수를 업데이트 해준다.
                // 모든 외부 링크가 입력으로 주어진 페이지에 해당하지 않을 수도 있음. -> null 반환.
                // 따라서 null 이 아닌 경우에만 진행한다.(입력으로 주어진 페이지 중의 하나를 참조하고 있음)
                Integer value = pageMap.get(linkUrl);
                if (value != null) {
                    // 그 페이지의 매칭 점수를 갱신해준다.
                    pageList.get(value).matchingScore += (double) pageList.get(i).basic / pageList.get(i).link;
                }
            }
        }

        // 위의 과정을 반복하면, 모든 페이지의 매칭 점수가 구해진다.
        pageList.sort(comp);

        return pageList.get(0).index;
    }

    // 매칭 점수가 같다면 인덱스 번호가 작은게 앞으로 오도록 하고
    // 그렇지 않다면 매칭 점수가 큰 수가 앞으로 오도록 정렬한다.
    private static Comparator<Page> comp = new Comparator<Page>() {
        @Override
        public int compare(Page a, Page b) {
            if (a.matchingScore == b.matchingScore) return a.index - b.index;
            else if (a.matchingScore < b.matchingScore) return 1;
            else return -1;
        }
    };

    // 페이지 정보를 저장하기 위한 클래스.
    static class Page {
        int index; // 인덱스.
        int basic, link; // 기본 점수와 링크 수.
        double matchingScore; // 매칭 점수.

        public Page(int index, int basic, int link, double matchingScore) {
            this.index = index;
            this.basic = basic;
            this.link = link;
            this.matchingScore = matchingScore;
        }
    }
}
