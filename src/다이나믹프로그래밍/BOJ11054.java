package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 13/03/2019
 * DP : 가장 긴 바이토닉 수열
 */
public class BOJ11054 {
    private static int[] dp_lis;
    private static int[] dp_reverse_list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        dp_lis = new int[n];
        dp_reverse_list = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(input[i]);
            arr[i] = value;
        }

        getDpLis(arr, n);
        getDpReverseLis(arr, n);

        bw.write((solution(n) - 1) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void getDpLis(int[] arr, int n) {
        // 가장 긴 증가하는 부분 수열
        for (int i = 0; i < n; i++) {
            dp_lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp_lis[i] < dp_lis[j] + 1) {
                    // 최대값 구하는 식.
                    dp_lis[i] = dp_lis[j] + 1;
                }
            }
        }
    }

    private static void getDpReverseLis(int[] arr, int n) {
        // 가장 긴 감소하는 부분 수열
        // 뒤에서부터 시작하면서 증가하는 부분 수열 구하는 문제와 똑같이 푼다.
        // 뒤에서부터 시작하니까 수열을 뒤집은 것과 같다.
        for (int i = n - 1; i >= 0; i--) {
            dp_reverse_list[i] = 1;

            for (int j = 0; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp_reverse_list[i] = Math.max(dp_reverse_list[i], dp_reverse_list[j] + 1);
                }
            }
        }
    }

    private static long solution(int n) {
        for (int i = 0; i < n; i++) {
            dp_lis[i] += dp_reverse_list[i];
        }
        long result = dp_lis[0];

        for (int i = 1; i < n; i++) {
            result = Math.max(result, dp_lis[i]);
        }

        return result;
    }

}
