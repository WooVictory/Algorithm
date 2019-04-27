package 완전탐색;

import java.io.*;

/**
 * created by victory_woo on 27/04/2019
 * 완전 탐색 : 1,2,3 더하기
 * 근데, 난 DP로 품.
 */
public class BOJ9095 {
    private static int[] dp = new int[12];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // Test case
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 숫자.
            bw.write(solution(n) + "\n");
        }

        bw.flush();
    }

    private static int solution(int n) {
        // 0의 경우에도 1개의 방법이 있다고 가정해야 한다.
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}