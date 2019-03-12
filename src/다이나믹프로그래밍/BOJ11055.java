package 다이나믹프로그래밍;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 12/03/2019
 * DP : 가장 큰 증가하는 부분 수열
 */
public class BOJ11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] d = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            // d 배열은 자기 자신을 마지막으로 하기 때문에
            // 최소 자기 자신의 값이 들어가야 한다.
            d[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    // 현재 i번 째에 해당되는 d 배열의 최대값을 구하기 위함.
                    d[i] = Math.max(d[i],(d[j] + arr[i]));
                }
            }
        }

        // 결과는 d 배열 중에서 최대 값이 저장된 걸 찾아야 한다.
        // d 배열에 마지막에 저장된 값이 최대값이 아닐 수 있음.
        // arr[i]를 마지막으로 해서 합이 가장 큰 증가하는 부분 수열이 아닐 수 있기 때문이다.
        // 이 문제의 테스트 케이스가 그런 경우이다.
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
