package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/04
 * 다음 순열.
 * 유형 : 완전탐색.
 */
public class boj10972 {
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());

        a = new int[n];
        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) a[i] = toInt(in[i]);

        StringBuilder sb = new StringBuilder();
        if (nextPermutation()) for (int num : a) sb.append(num).append(" ");
        else System.out.println(-1);

        System.out.println(sb.toString());
    }

    // 다음 순열.
    private static boolean nextPermutation() {
        int i = a.length - 1;

        // i가 0보다 크고 i - 1 > i인 동안 i를 감소시키며
        // 조건을 만족하는 i값을 찾는다.
        while (i > 0 && a[i - 1] > a[i]) i--;

        // 이 경우는 다음 순열이 없는 경우.
        if (i <= 0) return false;


        int j = a.length - 1;
        // i - 1 > j인 동안 j를 감소시키며, 조건을 만족하는 j 값을 찾는다.
        while (a[i - 1] > a[j]) j--;

        // i - 1과 j의 위치를 바꿔준다.
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
