package programmers;

/**
 * created by victory_woo on 2020/04/04
 * 타일 장식물.
 * 유형 : dp.
 */
public class PGM43104 {
    private static final int MAX = 80;
    private static long[] dp = new long[MAX + 1];

    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(6));
    }

    public static long solution(int N) {
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= MAX; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return (dp[N] + dp[N - 1]) * 2 + dp[N] * 2;
    }
}
