package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/12
 * 퇴사.
 * 삼성 기출. dp
 */
public class sw14501_other {
    private static int n, max;
    private static int[] t, p, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        t = new int[n + 1];
        p = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            String[] in = br.readLine().split(" ");
            t[i] = toInt(in[0]);
            p[i] = toInt(in[1]);

            dp[i] = p[i];
        }


        solve();
    }

    private static void solve() {
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                if (i - j >= t[j]) {
                    dp[i] = Math.max(p[i] + dp[j], dp[i]);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (i + t[i] <= n + 1) {
                max = Math.max(max, dp[i]);
            }
        }

        System.out.println(max);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
