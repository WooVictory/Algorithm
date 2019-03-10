package 다이나믹프로그래밍;

import java.io.*;
import java.math.BigInteger;

/**
 * created by victory_woo on 09/03/2019
 * dp를 이용해서 풀려고 했지만, 범위가 long 이상으로 벗어나기 때문에
 * BigInteger를 이용했다.
 */
public class BOJ10826 {
    private static BigInteger[] dp = new BigInteger[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }
        bw.write(dp[n] + "");
        bw.flush();
        bw.close();
        br.close();
    }

// 처음에 범위를 생각하지 않고 풀었던 방식들!
/*    private static BigInteger solution(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }

        return dp[n];
    }

    private static int solution(int n) {

        int[] dp = new int[n + 1];
        if (n <= 1) {
            return n;
        } else {
            if (dp[n] > 0) {
                return dp[n];
            }
            dp[n] = solution(n - 1) + solution(n - 2);
            return dp[n];
        }
    }*/
}
