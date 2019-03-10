package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 10/03/2019
 * DP : 2xn 타일링 2 Bottom up
 * 88ms
 */
public class BOJ10727_BOTTOM_UP {
    private static int[] dp = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        bw.write(solutionTile2(num) + "");

        bw.flush();
        bw.close();
        br.close();

    }

    private static int solutionTile2(int n) {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 2 * dp[i - 2];
            dp[i] %= 10007;
        }
        return dp[n];
    }
}
