package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 10/04/2019
 * 완전탐색 : 이전 순열.
 * 다음 순열의 반대 버전.
 */
public class BOJ10973 {
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        a = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(input[i]);
        }

        if (before_permutation()) {
            for (int i = 0; i < n; i++) {
                sb.append(a[i]).append(" ");
            }
        } else {
            System.out.println(-1);
        }

        System.out.println(sb.toString());
    }

    private static boolean before_permutation() {
        int i = a.length - 1;

        // 다음 순열을 구하는 방법에서 1번을 반대로 해주면 된다.
        while (i > 0 && a[i - 1] < a[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        // 다음 순열을 구하는 방법에서 2번을 반대로 해주면 된다.
        int j = a.length - 1;

        while (a[j] > a[i - 1]) {
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
            j--;
            i++;
        }

        return true;
    }
}
