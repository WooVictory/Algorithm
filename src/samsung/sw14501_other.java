package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 31/07/2019
 * SW 역량 기출 퇴사.
 * DP 로 풀어보기.
 */
public class sw14501_other {
    private static int n;
    private static int[] time;
    private static int[] money;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = parse(br.readLine());

        time = new int[n + 1];
        money = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            time[i] = parse(in[0]);
            money[i] = parse(in[1]);

            dp[i] = money[i];
        }

        solve();
    }

    /*
     * dp 로 풀기 위해서 점화식을 세운다.
     * dp[n] : n 일까지 얻는 이익.
     * i : 기준일
     * j : 1 ~ j-1일
     * dp[i] = max(money[i] + dp[j], dp[i])
     * i가 5라고 가정한다면
     * dp[5] = max(money[5] + dp[1], dp[5])
     * 5일의 이익 + 1일까지의 이익과 5일까지의 이익 중 큰 값을 비교해서 dp 배열을 갱신한다.
     * 이처럼 갱신된 dp 배열의 값을 이용해 계속 갱신해나간다.
     *
     * */
    private static void solve() {
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // 기준일인 i 일까지의 이익을 얻기 위해서 i일과 j일의 차이가
                // 기간 내에 상담을 수행할 수 있는지 확인한다.
                if (i - j >= time[j]) {
                    dp[i] = Math.max(money[i] + dp[j], dp[i]);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            // 이 부분은 백준이가 N+1일에 퇴사를 한다.
            // N=7이라면 8일에 퇴사를 하는 것이다. 그런데 6일의 기간이 4일, 7일의 기간이 2일 걸린다면
            // 최대값을 구할 때, 6일과 7일에 있는 상담을 하면 안되므로 이 부분을 제거하기 위해서 조건을 검사한다.
            // 정리하면, 당일에 수행하는 상담이 N일 보다 작아서 수행할 수 있는 경우에만 최대값을 구하겠다는 것이다.
            if (i + time[i] <= n + 1) {
                max = Math.max(max, dp[i]);
            }
        }

        System.out.println(max);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
