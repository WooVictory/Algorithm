package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/03
 * 1,2,3 더하기.
 * 유형 : 완전 탐색 or DP
 * 1,2,3을 더하는 경우에 대해서 각각 dfs 탐색을 돌려준다.
 * 종료 조건은 total == number 가 되는 시점이다.
 * 단, total > number 일때는 return 을 걸어줘야 한다.
 * 걸지 않으면 오버 플로우 발생..!
 */
public class boj9095 {
    private static int count = 0;
    private static int[] dp = new int[12];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());
        while (t-- > 0) {
            count = 0;
            int n = toInt(br.readLine());
            /*solve(0, n);
            System.out.println(count);*/
            System.out.println(solveWithDp(n));
        }
    }

    private static void solve(int total, int n) {
        if (total == n) {
            count++;
            return;
        } else if (total > n) {
            return;
        }

        for (int i = 1; i <= 3; i++) solve(total + i, n);
    }

    private static int solveWithDp(int n) {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
