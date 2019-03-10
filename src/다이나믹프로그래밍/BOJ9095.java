package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 10/03/2019
 * DP : 1,2,3 더하기
 */
public class BOJ9095 {
    private static int[] dp = new int[1000];
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            int value = Integer.parseInt(br.readLine());
            bw.write(solution9095(value) + NEW_LINE);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution9095(int n) {
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        /*
        * 마지막에 어떤 수를 더할지 초점을 맞춘다.
        * 1을 더할지, 2를 더할지, 3을 더할지 각각 나눠서 모두 합해서 dp에 저장한다.
        * dp는 정수 n을 1,2,3의 합으로 나타내는 방법의 수를 담는다.
        * */
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
