package 정렬;

/**
 * created by victory_woo on 27/03/2019
 * 퀵 정렬
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {254, 3, 213, 64, 75, 56, 4, 324, 65, 78, 9, 5, 3410, 8, 342, 76};
        //int[] arr = {69, 10, 30, 2, 16, 8, 31, 22};
        //int[] arr = {12, 9, 3, 2, 7, 5, 10, 6};

        quickSort(arr, 0, arr.length - 1);

        System.out.println("승우 정렬된 후 배열");
        printArr(arr);

    }

    private static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    private static void quickSort(int[] arr, int left, int right) {
        int L = left;
        int R = right;
        int temp;
        int pivot = arr[(left + right) / 2]; // 배열의 중앙 인덱스 값을 피봇으로 설정.

        // 아래의 while문을 통하여 pivot 기준으로
        // 왼쪽은 피봇보다 작은 값 나열.
        // 오른쪽은 피봇보다 큰 값 나열

        while (L <= R) {
            // left부터 증가하며 pivot 이상의 값을 찾는다.
            while (arr[L] < pivot) {
                L++;
            }
            // right부터 감소하며 pivot 이하의 값을 찾는다.
            while (arr[R] > pivot) {
                R--;
            }

            // 현재 L이 R보다 작거나 같고 같지 않은 경우에만
            // 교환해준다.
            if (L <= R) {
                if (L != R) {
                    temp = arr[L];
                    arr[L] = arr[R];
                    arr[R] = temp;
                }
                L++;
                R--;

            }

            /*if (L < R) {
                temp = arr[L];
                arr[L] = arr[R];
                arr[R] = temp;
                L++;
                R--;
            }*/
        }


        if (left < R) {
            quickSort(arr, left, R);
        }

        if (L < right) {
            quickSort(arr, L, right);
        }
    }
}
