package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/12
 * 시험 감독.
 * 완전 탐색(구현)
 * 자료형의 범위.
 */
public class SW13458 {
    private static int n, b, c;
    private static long count;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        a = new int[n];

        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = toInt(in[i]);
        }

        in = br.readLine().split(" ");
        b = toInt(in[0]);
        c = toInt(in[1]);


        solution2();
        System.out.println(count);
    }

    private static void solution() {
        for (int i = 0; i < a.length; i++) {
            a[i] -= b;
            count++;

            while (a[i] > 0) {
                int r = a[i] / c;
                if (r == 0) {
                    count++;
                    break;
                }
                count = count + r;

                a[i] = a[i] % c;
                if (a[i] == 1) {
                    count++;
                    break;
                }
            }
        }
    }

    // 조금 더 개선된 방법.
    private static void solution2() {
        for (int i = 0; i < a.length; i++) {
            a[i] -= b;
            count++;

            if (a[i] > 0) {
                count += a[i] / c;

                if (a[i] % c != 0) count++;
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
