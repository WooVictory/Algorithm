package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/14
 * 퇴사.
 * dp.
 */
public class SW14501 {
    private static int n;
    private static int[] time, pay;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        time = new int[n];
        pay = new int[n];

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            time[i] = toInt(in[0]);
            pay[i] = toInt(in[1]);
        }


        solve(0, 0);

        System.out.println(max);
    }

    private static void solve(int index, int sum) {
        if (index == n) {
            max = Math.max(max, sum);
            return;
        }

        if (index + time[index] <= n) solve(index + time[index], sum + pay[index]);
        solve(index + 1, sum);

    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
