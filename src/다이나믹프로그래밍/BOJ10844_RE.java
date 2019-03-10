package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 10/03/2019
 * DP : 쉬운 계단 수.
 */
public class BOJ10844_RE {
    private static long[][] dp = new long[101][11];
    private static final long MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        bw.write(solution10844(n) + "");

        bw.flush();
        bw.close();
        br.close();

    }

    private static long solution10844(int n) {

        // 길이가 1인 경우 1로 초기화.
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // 길이가 증가시켜 가면서 진행한다.
        // 몇 자리 계단 수(i)에 마지막으로 어떤 수가 오는지(0~9)의 조합으로 문제를 풀 수 있다.
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {

                // 0일 경우 예외 처리.
                // -1을 추가할 수 없기 때문이다.
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }

                // 9일 경우 예외 처리.
                // 10을 추가할 수 없기 때문이다.
                if (j + 1 <= 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }

                dp[i][j] %= MOD;
            }
        }

        // 길이가 n인 계단의 총 개수의 합.
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[n][i];
        }
        result %= MOD;
        return result;
    }
}
