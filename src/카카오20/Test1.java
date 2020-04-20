package 카카오20;

/**
 * created by victory_woo on 2020/04/20
 * 카카오 20 기출.
 * 문자열 압축.
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }

    public static int solution(String s) {
        int length = s.length();
        int answer = s.length();
        if (length == 1) return 1;

        // 문자열 처리를 효율적으로 하기 위해서 StringBuilder 사용.
        StringBuilder sb;
        for (int i = 1; i <= length / 2; i++) {
            sb = new StringBuilder();
            String now, next = "";
            int count = 1;
            for (int j = 0; j <= length / i; j++) {
                // end 값에 대한 설명.
                // i개로 잘라서 j개의 조각들을 낸다.
                // 이때, 문자열 s = aabbaacc 인 경우에 i = 3이므로 3개씩 자른다고 가정하자.
                // aab, bac, cc() -> j=2인 경우, end 값은 (2+1)*3 = 9가 된다. 하지만,
                // 9는 문자열의 길이를 넘어가는 값이기 때문에 end 값의 계산이 문자열의 길이보다 커진다면 문자열의 길이까지만, 잘라 cc가 된다.
                int start = j * i;
                int end = s.length() < (j + 1) * i ? s.length() : (j + 1) * i;
                now = next;
                next = s.substring(start, end);

                // 연속된 문자열일 경우, count 값을 증가시킨다.
                if (now.equals(next)) {
                    count++;
                } else {
                    // 그렇지 않은 경우, 문자열과 count 값을 붙이고 count 는 1로 초기화 한다.
                    sb.append(getCount(count)).append(now);
                    count = 1;
                }
            }

            // 마지막에 붙이지 못하고 남은 문자열을 끝에 붙여준다.
            //result += (getCount(count) + next);
            sb.append(getCount(count)).append(next);

            // 원래 입력 문자열의 길이와 압축한 뒤, 문자열의 길이를 비교하여 가장 짧은 문자열의 길이를 찾는다.
            answer = Math.min(answer, sb.toString().length());
        }

        return answer;
    }

    // 1보다 큰 경우에는 count 값을 붙이도록 한다. 1개인 경우에는 앞에 count 값을 붙이지 않는다.
    private static String getCount(int count) {
        return count > 1 ? String.valueOf(count) : "";
    }
}
