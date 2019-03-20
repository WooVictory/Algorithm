package 다이나믹프로그래밍;


import java.io.*;

/**
 * created by victory_woo on 20/03/2019
 * DP : 암호코드
 * 다시 풀어보기..!
 */
public class BOJ2011 {
    private static final int NUM = 48;
    private static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine().trim();
        long[] dp = new long[input.length() + 1];

        dp[0] = 1;

        for (int i = 1; i <= input.length(); i++) {

            // 위에서 처리해줬기 때문에 i-1로 시작한다.
            int now = i - 1;

            int x = input.charAt(now) - NUM;
            if (1 <= x && x <= 9) {
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }

            // i가 1일 경우, 위에만 처리하고 올라간다.
            if (i == 1) {
                continue;
            }

            x = ((input.charAt(now - 1) - NUM) * 10 + input.charAt(now) - NUM);

            if (10 <= x && x <= 26) {
                dp[i] = (dp[i - 2] + dp[i]) % MOD;
            }
        }

        bw.write(dp[input.length()] +"");

        bw.flush();
        bw.close();
        br.close();
    }
}
