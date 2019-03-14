package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 14/03/2019
 * DP : 계단 오르기
 * 포도주 시식과 비슷하지만 다르다.
 * 포도주 시식은 안마시면 되지만, 계단은 오르지 않는것이 안되기 때문에 0인 경우는 제외하고 생각한다.
 */
public class BOJ2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        // 계단 점수 배열.
        long[] arr = new long[n + 1];
        // dp 배열
        long[][] dp = new long[n + 1][3];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 계단이 하나일 경우, 올라가면 끝난다.
        dp[1][1] = arr[1];
        for (int i = 2; i <= n; i++) {
            // 0인 경우는 없다.
            //dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));

            // i번째 계단이 1번 연속인 경우, i-1번째 계단은 밟으면 안되고 i-2번째 계단에서 온 경우이다.
            // 그러므로 i-2번째 계단이 1번 연속인지 2번 연속인지 구하고 최대값 + 밟은 계단의 점수를 더하면 된다.
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + arr[i];

            // i번째 계단이 2번 연속인 경우이므로 i-1번째 계단은 1번 연속이어야 한다.
            dp[i][2] = dp[i - 1][1] + arr[i];
        }
        long result = Math.max(dp[n][1], dp[n][2]);
        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
