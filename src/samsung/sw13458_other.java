package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 22/07/2019
 * SW 역량 기출 시험 감독관
 * 다시 풀기.
 */
public class sw13458_other {
    private static final String SPACE = " ";
    private static int n, B, C;
    private static long result;
    private static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());
        map = new int[n];

        String[] in = br.readLine().split(SPACE);
        for (int i = 0; i < n; i++) {
            map[i] = parse(in[i]);
        }

        in = br.readLine().split(SPACE);
        B = parse(in[0]);
        C = parse(in[1]);

        solve();
        System.out.println(result);
    }

    private static void solve() {
        for (int i = 0; i < n; i++) {
            result++;
            map[i] -= B; // 1. 총감독관을 먼저 빼고 시작한다.

            // 해당 시험장의 응시생이 0보다 작다면 다음 시험장 검사.
            if(map[i]<0)
                continue;

            // 시험장의 응시생의 수가 부감독관이 감시할 수 있는 수로 딱 나눠 떨어진다면
            // 나눈 값을 넣는다.
            if (map[i] % C == 0) {
                result += map[i] / C;
            } else {
                // 나누어 떨어지지 않는다면,
                // 나눈 값을 넣고, 1을 더해준다.
                result += map[i] / C;
                result++;
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}