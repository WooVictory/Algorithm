package 카카오20;

/**
 * created by victory_woo on 2020/04/20
 * 카카오 20 기출
 * 문자열 압축.
 */
public class Test1_Re {
    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }

    private static int solution(String s) {
        int len = s.length();
        int answer = s.length();

        if (len == 1) return 1;

        StringBuilder sb;

        // i개 만큼 반복되는 문자열을 확인한다. 즉, i개씩 자른다.
        for (int i = 1; i <= len / 2; i++) {
            sb = new StringBuilder();
            String now, next = "";
            int count = 1;

            // i개씩 자르고 j개의 덩어리가 나온다.
            // 길이 : 8이고, i가 2이면, 8 / 2 = 4이기 때문에 j는 4번 반복한다.
            for (int j = 0; j <= len / i; j++) {

                // start, end 값을 구한다. 문자열을 자르기 위한 시작과 끝 위치.
                // i = 3, j = 2일 때, start = 6, end = 9가 나온다.
                // 하지만, 9는 문자열의 길이 8을 넘어서기 때문에 문자열까지만 자르면 된다.
                // 그러한 예외를 처리하기 위해서 end 에서는 조건을 검사하여 값을 지정한다.
                int start = i * j;
                int end = s.length() < i * (j + 1) ? s.length() : i * (j + 1);

                // now 값은 next 를 받는다.
                // next 값은 문자열에서 자른 값을 받는다.
                now = next;
                next = s.substring(start, end);

                // 두 문자열이 같으면 반복되는 것이므로 count 값을 증가시킨다.
                if (now.equals(next)) {
                    count++;
                } else {
                    // 같지 않다면 count 값을 확인하고 sb 객체에 붙인다.
                    // 그리고 count 값을 1로 초기화 한다.
                    sb.append(getCount(count)).append(now);
                    count = 1;
                }
            }

            // 위의 문자열을 자르고 비교하는 과정에서 끝까지 돌았는데, 다른 문자열이 나오지 않고 같은 문자열만 나오면
            // 위에서는 그대로 종료한다. 따라서 마지막에 처리하지 못한 부분을 처리하기 위해서 마지막에 sb 객체에 값들을 붙여준다.
            sb.append(getCount(count)).append(next);

            // 여기서 answer 값에는 초기에 최대값이 들어있는데, 이 최대값은 문자열의 길이다. 압축을 하기 때문에
            // 기존 문자열의 길이보다 커질 수는 없다.
            // 문자열의 길이와 압축한 문자열의 길이를 비교하여 가장 작은 값을 찾는다.
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }

    // 1보다 작거나 같은 값은 count 값을 붙이지 않는다.
    // 1보다 큰 값만 count 값을 붙인다.
    private static String getCount(int count) {
        return 1 < count ? String.valueOf(count) : "";
    }
}
