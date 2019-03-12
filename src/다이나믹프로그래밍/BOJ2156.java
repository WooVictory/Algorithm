package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 11/03/2019
 * DP : 포도주 시식
 * n번째 포도주가 0번 연속일 때, n-1번째가 어떤 상태인지
 * n번째 포도주가 2번 연속일 때, 앞의 상태는 어떤 상태인지
 * 2차원 , 1차원으로 둘 다 풀 수 있다.
 */
public class BOJ2156 {
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        // n번째 포도주의 양을 담는 배열 arr.
        int[] arr = new int[n + 1];
        /*
        * 포도주를 몇 번 연속으로 마셨는지 기억하는 배열 dp.
        * 0번 연속
        * 1번 연속
        * 2번 연속
        * */
        dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        bw.write(solution(arr, n) + "");

        bw.flush();
        bw.close();
        br.close();


    }

    private static long solution(int[] arr, int n) {

        /*
        * 몇 번 연속으로 포도주를 마셨는지 기억한다.
        * 0번 연속 : 앞의 포도주를 마셔도 되고 마시지 않아도 되고 2번 연속으로 마셔도 된다.
        * 그러므로 그렇게 마신 것들 중 최대 값을 리턴한다.
        * 1번 연속 : 1번 연속이면 앞의 포도주는 마시지 않아야 한다. 그리고 1번 마신 포도주의 양을 더한다.
        * 2번 연속 : 2번 연속이면 앞의 포도주는 마셔야 한다. 그리고 1번 마신 포도주의 양을 더한다.
        * */
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = dp[i - 1][0] + arr[i];
            dp[i][2] = dp[i - 1][1] + arr[i];
        }

        return Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
    }
}
