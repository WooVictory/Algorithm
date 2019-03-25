package 정렬;

/**
 * created by victory_woo on 24/03/2019
 * 합병 정렬
 * 기본 개념은 분할 정복
 * 100000개의 데이터 정렬 : 26ms 걸림
 * 1000개의 역순 숫자 정렬할 때 : 1ms 걸림.
 * 백만개 역순 정렬 => 54ms 걸림.
 * 백만개 랜덤 정렬 => 179ms 걸림.
 */
public class MergeSort {
    private static int[] sorted;

    public static void main(String[] args) {
        //int[] arr = {254, 3, 213, 64, 75, 56, 4, 324, 65, 78, 9, 5, 76, 3410, 8, 342, 76};
        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000000);
        }

       /* int size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = size--;
        }*/

        /*for (int num : arr){
            System.out.println(num);
        }*/

        int n = arr.length;
        sorted = new int[n];

        long start = System.currentTimeMillis();
        MergeSort(arr, 0, n - 1);
        long end = System.currentTimeMillis();

        for (int j : arr) {
            System.out.println(j);
        }
        System.out.println("병합 정렬 정렬 시간 : " + (end - start));

    }

    private static void MergeSort(int[] arr, int left, int right) {
        int mid;

        if (left < right) {
            mid = (left + right) / 2;
            MergeSort(arr, left, mid);
            MergeSort(arr, (mid + 1), right);
            Merge(arr, left, mid, right);
        }
    }

    private static void Merge(int[] arr, int left, int mid, int right) {
        int i, j, k, l;
        i = left;
        j = (mid + 1);
        k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                sorted[k++] = arr[i++];
            } else {
                sorted[k++] = arr[j++];
            }
        }

        // 남아 있는 값들을 일괄 복사한다.
        if (mid < i) {
            for (l = j; l <= right; l++) {
                sorted[k++] = arr[l];
            }
        } else {
            for (l = i; l <= mid; l++) {
                sorted[k++] = arr[l];
            }
        }

        for (l = left; l <= right; l++) {
            arr[l] = sorted[l];
        }
    }
}

