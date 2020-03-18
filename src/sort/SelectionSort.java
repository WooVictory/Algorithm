package sort;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/03/06
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {7, 6, 2, 4, 3, 9, 1};

        long startTime = System.currentTimeMillis();
        sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int standard = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[standard]) standard = j;
            }
            int temp = arr[standard];
            arr[standard] = arr[i];
            arr[i] = temp;

            System.out.print((i + 1) + "단계 : ");
            print(arr);
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
