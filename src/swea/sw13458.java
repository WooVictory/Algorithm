package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/10
 * 시험 감독.
 */
public class sw13458 {
    private static final String EMPTY = " ";
    private static int[] a;
    private static int n, B, C;
    private static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        a = new int[n + 1];

        String[] in = br.readLine().split(EMPTY);
        for (int i = 1; i <= n; i++) a[i] = toInt(in[i - 1]);

        in = br.readLine().split(EMPTY);
        B = toInt(in[0]);
        C = toInt(in[1]);

        for (int i = 1; i <= n; i++) {
            if (a[i] - B >= 0) {
                a[i] -= B;
                count++;
            }
        }

        solve();
        System.out.println(count);
    }

    private static void solve() {
        // 총 감독관이 감시할 수 있는 응시자의 수인 B 를 먼저 초반에 빼준다.
        while (true) {
            if (isZero()) return;

            for (int i = 1; i <= n; i++) {
                if (a[i] == 0) continue;

                if (a[i] > 0) {
                    a[i] -= C;
                    count++;
                }

                if (a[i] < 0) a[i] = 0;
            }
        }
    }


    private static boolean isZero() {
        boolean isZero = true;
        for (int i = 1; i <= n; i++) {
            if (a[i] != 0) {
                isZero = false;
            }
        }
        return isZero;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
