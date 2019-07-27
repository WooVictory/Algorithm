package myrealtrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 27/07/2019
 * 친적 집과의 최소 거리 구하기.
 * 완전 탐색.
 */
public class MariDistance {
    private static int min = Integer.MAX_VALUE;
    private static final String SPACE = " ";
    private static int n;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(SPACE);
        n = parse(in[0]);
        a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = parse(in[i + 1]);
        }

        // 기준을 하나씩 잡으면서
        // 친척 집과의 거리 총합을 구해 최소값을 찾는다.
        // 간단한 완전 탐색.
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int standard = a[i];
            for (int j = 0; j < n; j++) {
                sum += Math.abs(standard - a[j]);
            }
            min = Math.min(min, sum);
        }
        System.out.println("결과 => " + min);

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}