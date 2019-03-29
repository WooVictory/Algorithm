package 정렬;

import java.io.*;

/**
 * created by victory_woo on 29/03/2019
 * 정렬 : 수 정렬하기 3
 * 이 방식은 시간이 좀 오래 걸림.
 * 다른 풀이 보니까 정렬이 아닌 것 같음.
 * 시간 : 2600ms 정도 걸림.
 */
public class BOJ10989 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        //Arrays.sort(arr);
        quickSort(arr, 0, arr.length - 1);
        for (int number : arr) {
            bw.write(number + NEW_LINE);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void quickSort(int[] arr, int left, int right) {
        int L = left;
        int R = right;
        int pivot = arr[(left + right) / 2]; // 중간 원소를 피봇으로 지정.

        while (L <= R) {
            while (arr[L] < pivot) {
                L++;
            }

            while (arr[R] > pivot) {
                R--;
            }

            if (L <= R) {
                if (L != R) {
                    int tmp = arr[L];
                    arr[L] = arr[R];
                    arr[R] = tmp;
                }
                L++;
                R--;
            }
        }

        if (left < R) {
            quickSort(arr, left, R);
        }

        if (L < right) {
            quickSort(arr, L, right);
        }
    }
}
