package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 13/03/2019
 * DP : 상자 넣기
 * 일종의 LIS 문제
 * 최대, 최소 값을 찾을 때 이진 탐색을 이용하면 시간을 더 줄일 수 있다.
 */
public class BOJ1965 {
    private static int[] dp;
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        dp = new int[n];
        String[] input = br.readLine().split(SPACE);

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            getBoxLIS(arr, i);
        }

        long result = dp[0];

        for (int i = 1; i < n; i++) {
            result = Math.max(result, dp[i]);
        }
        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();

    }

    private static void getBoxLIS(int[] arr, int i) {
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                dp[i] = dp[j] + 1;
            }
        }

    }
}
