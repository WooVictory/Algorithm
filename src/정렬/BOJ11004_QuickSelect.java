package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 29/03/2019
 * Quick Select 라는 방법으로 풀기.
 * 다음에 다시 풀기. 퀵 셀렉트 알고리즘으로!
 * 다음에 다시 풀기.. 잘 모르겠음...ㅜ
 */
public class BOJ11004_QuickSelect {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] arr = new int[n];
        //int[] arr = {12, 9, 3, 2, 7, 5, 10, 6};
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //Arrays.sort(arr);

        System.out.println(quick_sort(arr, 0, arr.length - 1, k - 1));

    }

    private static int quick_sort(int[] arr, int left, int right, int k) {

        if (left < right) {
            int pivot = partition(arr, left, right);

            if (pivot == k) {
                return arr[pivot];
            } else if (pivot > k) {
                return quick_sort(arr, left, pivot - 1, k);
            } else {
                return quick_sort(arr, pivot + 1, right, k);
            }
        }


        return Integer.MIN_VALUE;

        /*quick_sort(arr, left, pivot - 1, k);
        quick_sort(arr, pivot + 1, right, k);*/

    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];

        while (left < right) {
            while (arr[left] < pivot) {
                left++;
            }

            while (arr[right] > pivot) {
                right--;
            }

            if (left < right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }
        return left;
    }
}
