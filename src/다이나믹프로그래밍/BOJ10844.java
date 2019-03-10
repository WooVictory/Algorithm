package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 10/03/2019
 * DP : 쉬운 계단 수
 *
 */
public class BOJ10844 {
    private static long[][] dp = new long[101][11];
    private static final long MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        bw.write(solutionStairs(n) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static long solutionStairs(int n) {
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                dp[i][j] = 0;
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }

                if (j + 1 <= 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }

                dp[i][j] %= MOD;
            }
        }
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[n][i];
        }
        result %= MOD;
        return result;
    }
}
