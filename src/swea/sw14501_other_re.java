package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/12
 * 퇴사.
 * 삼성 기출.
 * dp.
 */
public class sw14501_other_re {
    private static int n, max;
    private static int[] t, p, dp;
    // dp[n] = n일 까지의 이익.

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
        // i : 기준일
        // j : 1 ~ (i-1)일
        // i-j >= t[j] 가 의미하는 바는 t[j] 시간의 상담을 하고 난 후
        // 즉, t[j]의 시간이 지난 후, 상담을 할 수 있는지 확인한다.
        // j일에 t[j]만큼 시간이 걸리는 상담을 한 후, i일에 해당하는 상담을 할 수 있는지 확인한다. i >= j+t[j]
        // 이 과정을 통해서 dp 배열을 갱신한다.
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                if (i - j >= t[j]) {
                    dp[i] = Math.max(p[i] + dp[j], dp[i]);
                }
            }
        }

        // 위의 과정이 끝나면 dp 배열에는 각각 n일 까지의 이익이 갱신되어 들어간다.
        // i일에 t[i]일이 걸리는 상담을 할 수 있다면 max 값을 갱신한다.
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
