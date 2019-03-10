package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 10/03/2019
 */
public class BOJ11726_BOTTOM_UP {
    private static int[] dp = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        bw.write(solutionTile(num) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solutionTile(int n) {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
            dp[i] %= 10007;
        }

        return dp[n];
    }
}
