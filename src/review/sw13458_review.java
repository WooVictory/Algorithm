package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 22/07/2019
 * SW 역량 기출 시험 감독 Review.
 */
public class sw13458_review {
    private static final String SPACE = " ";
    private static int N, B, C;
    private static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parse(br.readLine());

        map = new int[N];
        String[] in = br.readLine().split(SPACE);
        for (int i = 0; i < N; i++) {
            map[i] = parse(in[i]);
        }

        in = br.readLine().split(SPACE);
        B = parse(in[0]);
        C = parse(in[1]);

        solve();
    }

    private static void solve() {
        long result = 0;
        for (int i = 0; i < N; i++) {
            result++;
            // 1. 총감독관이 감시할 수 있는 응시자의 수인 B를 먼저 빼고 시작한다.
            map[i] -= B;

            // 2. 0보다 작다면 다음 시험장의 응시자를 검사한다.
            if (map[i] < 0)
                continue;

            // 3. 해당 시험장의 응시자 수를 부감독관이 감시할 수 있는 응시자의 수인 C로 나눠 떨어진다면
            // 해당 시험장의 응시자 수를 C로 나눈 몫 값을 더한다
            if (map[i] % C == 0) {
                result += map[i] / C;
            } else {
                // 4. 나눠 떨어지지 않는다면
                // 나눈 몫 값을 더하고, 1을 더해준다.
                result += map[i] / C;
                result++;
            }
        }
        System.out.println(result);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}