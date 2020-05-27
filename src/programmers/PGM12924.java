package programmers;

/**
 * created by victory_woo on 2020/05/27
 * 숫자의 표현.
 */
public class PGM12924 {
    public static void main(String[] args) {
        System.out.println(solution(15));
    }

    public static int solution(int n) {
        int answer;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            answer = 0;
            for (int j = i; j <= n; j++) {
                // 먼저 더해준다.
                answer += j;

                // 합이 n과 같은지 비교한다.
                // 같다면 갯수를 세어주고 이 반복문을 빠져나온다.
                if (answer == n) {
                    count++;
                    break;
                } else if (answer > n) { // answer 가 n 보다 크다면 n을 만들 수 없으므로 반복문을 빠져나온다.
                    break;
                }
            }
        }

        // 자기 자신까지 포함해서 +1 해줘야됨!
        return count;
    }
}
