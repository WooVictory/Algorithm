package programmers;

/**
 * created by victory_woo on 2020/03/22
 * 약수의 합.
 * 주어진 숫자의 약수의 합을 구하는 문제이다.
 * 1 ~ n까지 돌리면서 해당 숫자로 n이 나누어 떨어진다면 answer 에 더해서 값을 구하고 return 한다,
 *
 * 조금 더 효율적인 방법을 보자면 n까지 돌 필요가 없고, n/2까지만 돌면 된다.
 * 그리고 결과는 answer + n 을 return 한다.
 */
public class PGM12928 {
    public static void main(String[] args) {
        System.out.println(solution(12));
        System.out.println(solution(5));
    }

    public static int solution(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                answer+=i;
            }
        }
        return answer;
    }
}
