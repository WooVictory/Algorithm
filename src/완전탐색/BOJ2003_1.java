package 완전탐색;

import java.io.*;

/**
 * created by victory_woo on 28/05/2019
 * 완탐 : 수들의 합2
 * 다시 풀어보기.
 * 이 방식에서 예외 상황 처리가 살짝 이해가 안간다...ㅜ
 */
public class BOJ2003_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        int n = parse(input[0]);
        int m = parse(input[1]);

        int[] arr = new int[n + 1];
        String[] num = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = parse(num[i]);
        }

        // sum : 구간의 합.
        int start = 0, end = 0, sum = arr[0], count = 0;

        while (start <= end && end < n) {

            if (sum < m) {
                end++;
                sum += arr[end];
            } else if (sum == m) {
                count++;
                end++;
                sum += arr[end];
            } else if (sum > m) {
                sum -= arr[start];
                start++;
                // 예외 상황 처리해야 함.
                if (start > end && start < n) {
                    end = start;
                    sum = arr[start];
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
