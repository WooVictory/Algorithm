package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 10/06/2019
 * bfs : 두 대표 자연수
 * 모르겠다... 일단 포기!
 */
public class BOJ2551 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parse(br.readLine());
        int[] arr = new int[N + 1];
        int[] sum = new int[10001];

        String[] in = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = parse(in[i - 1]);
        }

        for (int i = 1; i < 10000; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i] = sum[i] + Math.abs(arr[j] - i);
                //System.out.println("sum: "+String.valueOf(sum[i]));
            }
        }

        int max = Integer.MAX_VALUE;
        for (int i=1;i<100;i++){
            max = Math.min(max, sum[i]);
        }

        System.out.println(max);

    }

    private static int parse(String str) {
        return Integer.parseInt(str);

    }
}
