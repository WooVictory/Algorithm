package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 15/03/2019
 * DP : 파도반 수열
 * 완전히 규칙을 찾는 문제.
 * 그림이 힌트.
 */
public class BOJ9461 {
    private static final String NEW_LINE = "\n";
    private static final long[] dp = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        // dp[6]부터는 앞에 있는 삼각형들로 규칙을 만들어서 구할 수 있다.
        // n의 범위가 100까지 밖에 안되기 때문에 먼저 다 구해놓는다.
        // 배열의 인덱스가 0부터 시작하기 때문에 크기는 101이더라도 인덱스는 100까지 참조가능하다.
        for (int i = 6; i < dp.length; i++) {
            dp[i] = dp[i - 5] + dp[i - 1];
        }

        int test_case = Integer.parseInt(br.readLine());
        // 미리 구해놓은 배열을 test_case에 맞게 출력한다.
        while (test_case-- > 0) {
            int num = Integer.parseInt(br.readLine());
            bw.write(dp[num] + NEW_LINE);
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
