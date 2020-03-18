package sort;

/**
 * created by victory_woo on 2020/03/12
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {12, 9, 3, 2, 7, 5, 10, 6};
        int[] arr2 = {254, 3, 213, 64, 75, 56, 4, 324, 65, 78, 9, 5, 3410, 8, 342, 76};

        quickSort(arr2, 0, arr2.length - 1);

        printArr(arr2);

    }

    private static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void quickSort(int[] arr, int left, int right) {
        int L = left;
        int R = right;
        int pivot = arr[(left + right) / 2];

        while (L <= R) {
            while (arr[L] < pivot) L++;

            while (pivot < arr[R]) R--;

            if (L <= R) {
                if (L != R) {
                    swap(arr, L, R);
                }
                L++;
                R--;
            }
        }

        if (left < R) quickSort(arr, left, R);

        if (L < right) quickSort(arr, L, right);
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
