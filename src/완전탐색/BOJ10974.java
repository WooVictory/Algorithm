package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 10/04/2019
 * 완전 탐색 : 모든 순열
 */
public class BOJ10974 {
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        int n = Integer.parseInt(br.readLine());
        a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }

        do {
            sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(a[i]).append(" ");
            }
            sb.append("\n");
            System.out.print(sb.toString());
        } while (all_permutation());
    }

    private static boolean all_permutation() {
        int i = a.length - 1;

        while (i > 0 && a[i - 1] > a[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length - 1;
        while (a[j] < a[i - 1]) {
            j--;
        }

        int tmp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = tmp;

        j = a.length - 1;

        while (i < j) {
            tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i += 1;
            j -= 1;
        }

        return true;
    }
}
