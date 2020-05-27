package programmers;

/**
 * created by victory_woo on 2020/05/27
 * 다음 큰 숫자.
 */
public class PGM12911 {
    public static void main(String[] args) {
        System.out.println(solution(78));
        System.out.println(solution(15));
    }

    public static int solution(int n) {
        int answer = n;

        // 주어진 n을 이진수로 만든 뒤, 1의 갯수를 파악한다.
        String s = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') count++;
        }

        // n 을 증가시키면서 1의 갯수가 동일한 수를 찾을 때까지 반복한다.
        int cnt;
        do {
            cnt = 0;
            answer++;
            String a = Integer.toBinaryString(answer);

            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) == '1') cnt++;
            }
        } while (count != cnt);

        return answer;
    }
}
