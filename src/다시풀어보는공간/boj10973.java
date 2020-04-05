package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/04
 * 이전 순열.
 * 다음 순열 문제에서 부등호만 바꾸면 됨.
 */
public class boj10973 {
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());

        a = new int[n];
        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) a[i] = toInt(in[i]);

        StringBuilder sb = new StringBuilder();
        if (nextPermutation()) {
            for (int num : a) sb.append(num).append(" ");
        } else {
            System.out.println(-1);
        }
        System.out.println(sb.toString());

    }

    private static boolean nextPermutation() {
        int i = a.length - 1;

        while (i > 0 && a[i - 1] < a[i]) i--; // 여기 부등호 바꾸면 됨.

        if (i <= 0) return false;

        int j = a.length - 1;
        while (a[i - 1] < a[j]) j--; // 여기도 부등호 바꾸면 됨.

        swap(i - 1, j);

        j = a.length - 1;

        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }

        return true;

    }

    private static void swap(int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
