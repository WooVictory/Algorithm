package 다이나믹프로그래밍;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 15/03/2019
 * DP : 합분해
 * 메모리 할당은 전역에서 N의 범위보고 정적으로 잡아주는게 시간이 덜 걸리는 듯 보인다.
 */
public class BOJ2225 {
    private static final int MOD = 1000000000;
    private static long[][] dp = new long[201][201];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        // 정수 i를 1개로 합을 만드는 방법은 자기 자신 밖에 없다.
        for (int i = 0; i <= n; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i <= k; i++) { // k
            for (int j = 0; j <= n; j++) { // n
                for (int l = 0; l <= j; l++) { // 0<=l<=n. n이 0부터 시작하니까.
                    dp[i][j] += dp[i - 1][j - l];
                    dp[i][j] %= MOD;

                }
            }
        }

        System.out.println(dp[k][n] % MOD);

    }
}
