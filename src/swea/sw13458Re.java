package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/10
 * 시험 감독.
 */
public class sw13458Re {
    private static int n, b, c;
    private static long count;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1];
        String[] in = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) a[i] = toInt(in[i - 1]);

        in = br.readLine().split(" ");
        b = toInt(in[0]);
        c = toInt(in[1]);

        solve();
    }

    private static void solve() {
        // count 는 시험장의 갯수로 초기화한다.
        // 이유는 시험장마다 꼭 1명씩 총감독관이 필요하기 때문이다.
        // 따라서 총감독관의 수를 미리 count 에 넣어주는 것이다.
        count = n;

        // 하나의 for 문을 돌면서 각 시험장에서 총감독관의 감시 학생 수를 빼고
        // 부감독관의 감시 학생 수를 뺀다. 즉, 한 번에 하나의 시험장에 대해서 총감독관이 얼마나 필요한지 구한다.
        for (int i = 1; i <= n; i++) {
            a[i] -= b; // 시험장에서 총감독관의 감시 학생 수를 빼준다.

            // 시험장의 응시 학생 수가 0보다 크다는 것은 부감독관이 필요함을 의미한다.
            if (a[i] > 0) {
                // 부감독관은 "시험장의 응시 학생 수 / 부감독관의 감시 학생 수" 만큼 필요하다.
                count += a[i] / c;

                // 만약, 시험장의 응시 학생 수가 딱 나누어 떨어지지 않는다면
                // 부감독관을 한명 더 추가해줘야 한다.
                if (a[i] % c != 0) count++;
            }
        }
        System.out.println(count);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
