package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 10/04/2019
 * 완전탐색 : 다음 순열.
 */
public class BOJ10972 {
    private static int[] arr;
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        String[] input = br.readLine().split(SPACE);
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        if (next_permutation()) {
            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(SPACE);
            }
        } else {
            System.out.println(-1);
        }

        System.out.println(sb.toString());

    }

    private static boolean next_permutation() {
        int i = arr.length - 1;

        // 1. arr[i-1] < arr[i]를 만족하는 가장 큰 i를 찾는다.
        while (i > 0 && arr[i - 1] > arr[i]) {
            i--;
        }

        // 마지막 순열.
        if (i <= 0) {
            return false;
        }

        int j = arr.length - 1;

        // 2. j>=i이면서 arr[j] > arr[i-1]를 만족하는 가장 큰 j를 찾는다.
        while (arr[j] < arr[i - 1]) {
            j--;
        }


        // 3. arr[j]와 arr[i-1] 교환.
        int tmp = arr[j];
        arr[j] = arr[i - 1];
        arr[i - 1] = tmp;

        // 4. arr[i]부터 순열을 뒤집는다.
        j = arr.length - 1;

        while (i < j) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }

        return true;
    }

}
