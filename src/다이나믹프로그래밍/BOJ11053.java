package 다이나믹프로그래밍;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 12/03/2019
 * DP : 가장 긴 증가하는 부분 수열
 */
public class BOJ11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            // 자기 자신을 마지막으로 하는 가장 긴 증가하는 부분 수열이므로
            // 자기 자신이 포함되기 때문에 최소 길이는 1이다.
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                // 조건을 만족하는 값들 중 최대값을 넣는다.
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 정답은 dp[] 배열 중에서 최대값이 저장된 걸 찾아야 한다.
        // dp 배열의 마지막에 최대 값이 저장되어 있긴 하지만, 컴파일 에러가 뜬다.
        // 아마도 최대 값이 무조건 마지막에 저장되는 건 아닌 경우가 있는 것 같다.
        long result = dp[0];
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }

        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();

    }
}
