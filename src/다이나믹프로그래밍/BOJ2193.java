package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 11/03/2019
 * DP : 이친수
 * 76ms
 * N의 범위 주의하기!
 * int인지 long인지
 * n번째 자리에 무엇이 올 것인지 결정하면서 풀면 된다.
 */
public class BOJ2193 {
    private static long[] dp = new long[91];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        bw.write(solution2193(num) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private static long solution2193(int n) {
        // 초기 값을 할당해준다.
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        // 점화식을 세워서 반복문을 돌려준다.
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }
}
