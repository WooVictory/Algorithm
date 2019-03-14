package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 14/03/2019
 * DP : 연속합
 * 주의할 점 : 음수도 선택을 해야 하는가?
 * 음수를 선택할 경우에도 최대값이 될 수 있다.
 * 주의해서 가장 긴 증가 부분 수열 문제처럼 풀면 된다.
 */
public class BOJ1912 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];

        String[] input = br.readLine().split(SPACE);
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        // 하나의 for 문으로 해결 할 수 있다.
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];

            // i == 0인 경우 dp[0]에 값만 넣고 아래에서 dp[i-1] 즉 이전 배열을 확인해야 하기 때문에 넘어간다.
            if (i == 0) {
                continue;
            }

            /*
            *  dp[i] 에 arr[i]가 들어있기 때문에
            *  dp[i] : 새로운 연속합을 시작하는 경우로 생각할 수 있다.
            *  dp[i-1]+arr[i] : arr[i-1]로 끝나는 연속합에 포함된 경우로 생각할 수 있다. 이전 연속합 + 자기 자신의 값
            *  최대값을 구하면 된다.
            * */
            if (dp[i] < dp[i - 1] + arr[i]) { // 포함하는 경우
                dp[i] = dp[i - 1] + arr[i];
            }else { // 새롭게 시작하는 경
                dp[i] = arr[i];
            }

        }

        long result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }

        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();

    }
}
