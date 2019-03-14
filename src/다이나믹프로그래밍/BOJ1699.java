package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 14/03/2019
 * DP : 제곱수의 합
 * 입출력이 많지 않으면 BufferedWriter 보다는 System.out.println 이 나음.
 * 시간이 적게 걸림.
 */
public class BOJ1699 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // N까지 입력받아서 dp 배열에 넣는다.
            dp[i] = i;
            // 1,2,3 더하기 문제와 비슷하다.
            // N을 만들 때 i번째에 어떤 수가 올 수 있는지 생각해보고
            // i번째를 제외한 (dp[n - i^2] + 1)의 최소값을 찾는다.
            // 조건 : j^2 <= N(즉, i)
            for (int j = 1; j * j <= i; j++) {
                if (dp[i - (j * j)] + 1 < dp[i]) {
                    dp[i] = dp[i - (j * j)] + 1;
                }
            }
        }
        bw.write(dp[n] + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
