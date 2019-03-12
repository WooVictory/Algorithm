package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 12/03/2019
 * DP : 포도주 시식
 * 2차원 dp 배열로 푸는 방식.
 */
public class BOJ2156_RE {
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        // 포도주의 양을 담고 있는 배열.
        int[] arr = new int[n + 1];
        dp = new int[n + 1][3];

        // 입력받은 값을 포도주 양을 담고 있는 배열에 넣는다.
        for (int i = 1; i <= n; i++) {
            int value = Integer.parseInt(br.readLine());
            arr[i] = value;
        }

        bw.write(go(arr, n) + "");

        bw.flush();
        bw.close();
        br.close();


    }

    private static long go(int[] arr, int n) {

        for (int i = 1; i <= n; i++) {
            // 0번 연속의 경우. 앞의 경우는 모든 게 다 올 수 있다.
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            // 1번 연속의 경우. 앞의 경우는 0번 연속만 올 수 있다.
            dp[i][1] = dp[i - 1][0] + arr[i];
            // 2번 연속의 경우. 앞의 경우는 1번 연속만 올 수 있다.
            dp[i][2] = dp[i - 1][1] + arr[i];
        }

        return Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
    }

}
