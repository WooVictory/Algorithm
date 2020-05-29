package programmers;

/**
 * created by victory_woo on 2020/05/29
 * 점프와 순간 이동.
 * 단순한 나눗셈 연산으로 해결!
 */
public class PGM12980 {
    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(6));
        System.out.println(solution(5000));
    }

    public static int solution(int n) {
        int answer = 0;

        // 문제에 대해 역발상이 필요하다.
        // N에서 0으로 만드는 것으로 생각해보자.
        // 순간이동은 배터리 소모가 없음. 2로 나누어 떨어지면 2로 나누면 된다.
        // 점프는 나누어 떨어지지 않는 경우, 1칸에 1씩 배터리 소모.
        // 0이 될때까지 반복!!
        while (n != 0) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = n - 1;
                answer++;
            }
        }
        return answer;
    }
}
