package 카카오20;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 문자열 압축.
 * 최대 시간 복잡도 : O(500 * 1000)
 */
public class RE_Test1 {
    public static void main(String[] args) {
        //System.out.println(solution("aabbaccc"));
        //System.out.println(solution("ababcdcdababcdcd"));
        //System.out.println(solution("abcabcdede"));
        //System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }

    private static int min = Integer.MAX_VALUE;

    public static int solution(String s) {
        int len = s.length();
        // 1. 테케 5번이 틀리던데, 이는 문자열의 길이가 1인 경우에 대한 예외 테스트 케이스이다.
        if (s.length() == 1) return s.length();
        int answer;

        StringBuilder sb;
        String result;

        // 2. s 문자열을 문자열 길이의 반까지만 자른다.
        // aaccbbdd 라고 할 때, aacc / bbdd 로 서로 4개씩 자를 수 있다.
        // 하지만, aaccb / bdd 처럼 균등하게 자를 수 없기 때문이다.
        // 따라서 문자열을 정해진 길이만큼 잘라야 하므로 문자열 길이의 반만큼만 자른다.
        for (int i = 1; i <= len / 2; i++) {
            sb = new StringBuilder();
            String now, next = "";
            int count = 1;

            // 3. i 즉, 몇개씩 자를지에 따라서 문자열 s가 몇 등분으로 쪼개지는지 j를 이용해 구할 수 있다.
            for (int j = 0; j <= len / i; j++) {
                int start = i * j; // 자를 첫 위치.
                int end = i * (j + 1); // 자를 끝 위치.
                if (end > len) end = len; // 끝 위치가 길이보다 커지면, 길이까지만 자르면 된다.
                now = next;
                next = s.substring(start, end);

                // 4. now, next 가 같다면 count 증가시킨다.
                if (now.equals(next)) {
                    count++;
                } else {
                    // 5. 다르다고 하면 count 값을 계산해서 now 와 붙여준다.
                    // 그리고 count 값을 1로 초기화한다.
                    sb.append(getCount(count)).append(now);
                    count = 1;
                }
            }

            // 6. 마지막으로 처리되지 않고 남은 count, 문자열을 처리한다.
            sb.append(getCount(count)).append(next);
            result = sb.toString();

            // 7. 길이를 비교해서 더 짧은 문자열의 길이를 저장한다.
            min = Math.min(min, result.length());

            System.out.println(i + "개씩 짜른 경우 : " + sb.toString());
        }

        answer = min;
        return answer;
    }

    private static String getCount(int count) {
        return count > 1 ? String.valueOf(count) : "";
    }
}
