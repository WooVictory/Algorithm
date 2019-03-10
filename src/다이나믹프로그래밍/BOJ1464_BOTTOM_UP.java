package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 10/03/2019
 * DP : 1로 만들기 - Bottom up
 * 96ms
 * Top down 보다는 Bottom up이 훨씬 시간 복잡도가 낮다.
 * 아무래도 재귀 호출보다 반복문을 도는게 시간이 훨씬 적게 걸린다.
 */
public class BOJ1464_BOTTOM_UP {
    private static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        bw.write(go(num) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int go(int n) {
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
            }

            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
            }
        }

        return dp[n];
    }
}
