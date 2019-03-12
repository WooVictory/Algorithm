package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 11/03/2019
 * DP : 스티커
 * n번째 열에 어떤 스티커의 상태가 올 수 있는지를 보고
 * 그 때 앞에 올 수 있는 상태가 무엇인지를
 */
public class BOJ9465 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    /*
     * 열의 상태를 나타내는 배열.
     * 열에 따라서 상태는 3가지로 나뉜다.
     * */
    private static long[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = Integer.parseInt(br.readLine());
        while (test_case-- > 0) {
            int n = Integer.parseInt(br.readLine());

            // 2 x N 스티커이므로 2개의 행밖에 존재하지 않는다.
            // 열은 입력받은 N에 따라 달라지므로 배열의 크기를 아래처럼 할당한다.
            // 스티커 카드의 점수 배열
            long[][] arr;
            arr = new long[2][n + 1];

            // 2 x N 개의 스티커를 떼는데, n열의 상태에 따라 스티커의 최대 점수를 가지는 dp 배열
            dp = new long[n + 1][3];

            String[] line_zero = br.readLine().split(SPACE);
            String[] line_one = br.readLine().split(SPACE);
            for (int i = 1; i <= n; i++) {
                arr[0][i] = Integer.parseInt(line_zero[i - 1]);
                arr[1][i] = Integer.parseInt(line_one[i - 1]);
            }

            bw.write(solutionSticker(arr, n) + NEW_LINE);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static long solutionSticker(long[][] arr, int n) {
        /*
        * 각 경우에 따라서 처리해준다.
        * 0 : x x
        * 1 : o x
        * 2 : x o
        * i번째 열을 기준으로 i-1번째에 위치할 수 있는 경우를 구해서 Max 값을 넣어줍니다.
        * */
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + arr[0][i];
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + arr[1][i];
        }

        // 최종 결과는 어디에 저장되어 있을까?
        // dp[n][0], dp[n][1], dp[n][2] 에 저장되어 있다.
        // 정답은 최대 점수를 구하는 것이기 때문에 이 중에서 최대 값을 뽑아내면 된다.
        return Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
    }
}
