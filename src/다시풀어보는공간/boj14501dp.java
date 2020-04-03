package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/02
 * 퇴사.
 * dp.
 */
public class boj14501dp {
    private static int n, max = 0;
    private static int[] t, p, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        t = new int[n + 1];
        p = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            t[i] = toInt(in[0]);
            p[i] = dp[i] = toInt(in[1]);
        }

        solve();
        System.out.println(max);
    }

    private static void solve() {
        // i : 기준일
        // j : 1 ~ (i-1)
        // 기준일을 2부터 1씩 증가하여 뒤로 가면서 기준일 앞쪽에 있는 상담을 처리하고 기준일을 넘지 않는 경우에 대해서 조사한다.
        // 그래서 dp 값을 모두 구해본다.
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i >= j + t[j]) dp[i] = Math.max(p[i] + dp[j], dp[i]);
            }
        }

        // 해당 일자에 t[i]만큼 걸리는 일을 상근이가 퇴사하는 날 전까지 할 수 있는지 조사한다.
        // 6일까지의 이익 dp[6] = 70, 7일까지의 이익 dp[7] = 245이지만,
        // 6일의 일은 4일이 걸려 퇴사하는 날 전에 끝낼 수 없다.
        // 마찬가지로 7일의 일은 2일이 걸려 퇴사하는 날 전에 끝낼 수 없다.
        // 문제에서의 조건도 퇴사하기 전에 최대 이익을 낼 수 있는 상담을 하는 것이 목표이다.
        for (int i = 1; i <= n; i++) {
            if (i + t[i] <= n + 1) {
                max = Math.max(max, dp[i]);
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(dp[i]);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}