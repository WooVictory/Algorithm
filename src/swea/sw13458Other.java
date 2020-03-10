package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/10
 * 시험 감독.
 */
public class sw13458Other {
    private static final String EMPTY = " ";
    private static int N, B, C;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = toInt(br.readLine());
        a = new int[N + 1];

        String[] in = br.readLine().split(EMPTY);
        for (int i = 1; i <= N; i++) a[i] = toInt(in[i - 1]);

        in = br.readLine().split(EMPTY);
        B = toInt(in[0]);
        C = toInt(in[1]);

        solve();
    }

    private static void solve() {
        long count = N;
        for (int i = 1; i <= N; i++) {
            // 총감독관은 각 시험장마다 무조건 1명씩 필요하므로 빼준다.
            a[i] -= B;

            // 총감독관을 빼고 남은 시험장의 응시자수가 0보다 크다면 부감독관이 필요하다는 의미다.
            if (a[i] > 0) {
                count += a[i] / C;

                if (a[i] % C != 0) count++;
            }
        }

        System.out.println(count);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
