package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 10/03/2019
 * DP : 오르막수
 * 어려움. 다시 풀기.
 * n-1번째 자리에 어떤 수가 올 수 있는지 고민하면서 풀면 된다.
 */
public class BOJ11057 {
    private static long[][] dp = new long[1001][11];
    private static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        bw.write(solutionIncrease(n) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private static long solutionIncrease(int n) {
        // 1자리수에 대해서는 1로 초기화한다.
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }
        // 증가시켜가면서 진행한다.
        // i는 몇 자리수인지 나타내는 for문.
        // j는 마지막 수를 나타내는 for문.
        // k는 0~j까지를 나타낸다.
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= MOD;
                }
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
