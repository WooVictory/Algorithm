package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 14/03/2019
 * DP : 3xn 타일 채우기
 * n이 홀수인 경우는 채울 수 없음.
 * 조금 어려움.
 */
public class BOJ2133 {
    private static long[] dp = new long[31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        dp[0] = 1;
        dp[2] = 3;

        // 홀수의 경우는 채울 수 없다. 3 x n 이므로.
        // dp[2]는 3개 밖에 없다.
        for (int i = 4; i <= n; i += 2) {
            dp[i] = 3 * dp[i - 2];

            // 4부터 2씩 증가할 때마다 2x1 타일을 2개 추가하면 채우는 방법의 수도 +2가 된다.
            // 길이가 2씩 늘어나면서 경우의 수는 2배씩 증가한다.
            for (int j = 4; j <= i; j += 2) {
                dp[i] += 2 * dp[i-j];
            }
        }

        bw.write(dp[n] + "");

        bw.flush();
        bw.close();
        br.close();


    }
}
