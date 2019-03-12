package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 12/03/2019
 * DP : 가장 긴 감소하는 수열
 */
public class BOJ11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] d = new int[n];
        // StringTokenizer 보다 split()으로 짤라서 input 배열로 사용하는 것이
        // 104ms -> 96ms로 더 적게 걸린다.
        String[] input = br.readLine().split(" ");
        // 입력.
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(input[i]);
            arr[i] = value;
        }

        for (int i = 0; i < n; i++) {
            d[i] = 1;
            for (int j = 0; j < i; j++) {
                // 가장 긴 감소하는 부분 수열이므로 배열에 저장된 값은 앞에 배열이 더 커야 한다.
                if (arr[j] > arr[i]) {
                    // 최대값을 저장한다.
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
        }

        // d 배열 중 최대 값을 찾는다.
        long result = d[0];
        for (int i = 1; i < n; i++) {
            result = Math.max(result, d[i]);
        }

        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
