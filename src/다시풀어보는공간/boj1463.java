package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/04
 * 1로 만들기.
 * dp?!
 * dp[i] = i를 1로 만드는 데 걸리는 횟수.
 */
public class boj1463 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1000001];
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            // 미리 해당하는 숫자의 dp 값을 구해줘야 한다.
            // 1로 만드는데 몇번 걸리는지를 먼저 구한다.
            dp[i] = dp[i - 1] + 1;

            // 위에서 구하고 나서 i가 2나 3으로 나누어 떨어지면 최소의 경로를 가지고 있을 수 있기 때문에
            // 구했던 값과 최소로 찾을 수 있는 값을 비교하여 최소값을 찾아 저장한다.
            // dp[i] = dp[i-1]+1로 모두 구할 수 있다. 하지만, i가 2나 3으로 나누어 떨어질 때는
            // dp[i] = dp[i/2]+1 or dp[i/3]+1에서 최소값을 찾을 수 있다.
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            }

            // 2로 나누어 떨어져도 3으로 나누어 떨어지는 경우가 있을 수 있고, 또한 여기서 더 최소값을 찾을 수도 있다.
            // ex) 12, 18 이런 값들에 대해서는 반례가 있을 수 있음.
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
            }
        }

        System.out.println(dp[n]);
    }
}
