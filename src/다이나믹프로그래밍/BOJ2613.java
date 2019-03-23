package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 13/03/2019
 * DP : 줄 세우기
 * DP라기 보다는 LIS 즉, 가장 긴 증가하는 부분 수열 구하는 문제와 유사하다.
 */
public class BOJ2613 {
    private static int[] arr;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // DP 배열 구하기.
        getLIS(n);

        long result = dp[0];
        for (int i = 1; i < n; i++) {
            result = Math.max(result, dp[i]);
        }
        result = n - result;
        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }

    /*
    * 아이들을 효율적으로 정렬하기 위해서는 정렬하는 최소 횟수를 구해야 한다.
    * 정렬하기 위해서 증가하는 부분 수열을 찾고(이를 LIS라 부르겠다.)
    * LIS를 제외한 수들을 옮기면 최소 횟수로 SelectSort 할 수 있다.
    * 아래는 LIS를 구하는 코드이다.
    * */
    private static void getLIS(int n) {
        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], (dp[j] + 1));
                }
            }
        }
    }
}
