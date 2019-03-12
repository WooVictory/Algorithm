package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 12/03/2019
 * DP : 포도주 시식
 * 1차원 배열로 푸는 방식.
 */
public class BOJ2156_RE_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int value = Integer.parseInt(br.readLine());
            arr[i] = value;
        }

        // 포도주가 1잔일 때는 1잔 마시면 된다.
        dp[1] = arr[1];
        // 포도주가 2잔일 때는 2잔 마시면 된다. 예외 처리해줘야 한다.
        if (n >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        for (int i = 3; i <= n; i++) {
            // 0번 연속일 때.
            dp[i] = dp[i - 1];
            // 1번 연속일 때. 최대값을 저장하기 위함.
            dp[i] = Math.max(dp[i], dp[i - 2] + arr[i]);
            // 2번 연속일 때, 최대값을 저장하기 위함.
            dp[i] = Math.max(dp[i], dp[i - 3] + arr[i - 1] + arr[i]);

          /*  // 1번 연속일 때. 최대값을 저장하기 위함.
            if (dp[i] < dp[i - 2] + arr[i]) {
                dp[i] = dp[i - 2] + arr[i];
            }

            // 2번 연속일 때, 최대값을 저장하기 위함.
            if (dp[i] < dp[i - 3] + arr[i - 1] + arr[i]) {
                dp[i] = dp[i - 3] + arr[i - 1] + arr[i];
            }*/
        }

        bw.write(dp[n] + "");

        bw.flush();
        bw.close();
        br.close();

    }
}
