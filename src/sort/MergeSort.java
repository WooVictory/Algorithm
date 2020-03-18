package sort;

/**
 * created by victory_woo on 2020/03/12
 */
public class MergeSort {
    private static int[] sorted;

    public static void main(String[] args) {
        int[] arr = {12, 9, 3, 2, 7, 5, 10, 6};
        int[] arr2 = {254, 3, 213, 64, 75, 56, 56, 4, 324, 65, 78, 9, 5, 3410, 8, 342, 76};

        sorted = new int[arr2.length];
        mergeSort(arr2, 0, arr2.length - 1);

        for (int j : arr2) System.out.print(j + " ");
        System.out.println();
    }

    private static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(a, left, mid); // 왼쪽 부분 배열을 나눈다.
            mergeSort(a, mid + 1, right); // 오른쪽 부분 배열을 나눈다.
            merge(a, left, mid, right);
            // 나눈 부분 배열을 합친다.
            // 핵심 로직이다.
        }
    }

    /*
    * i : 왼쪽 부분 배열을 관리하는 인덱스
    * j : 오른쪽 부분 배열을 관리하는 인덱스
    * */
    private static void merge(int[] a, int left, int mid, int right) {
        int i, j, k, l;
        i = left;
        j = (mid + 1);
        k = left;

        // 왼쪽 부분 배열과 오른쪽 부분 배열을 비교하면서
        // 각각의 원소 중에서 작은 원소가 sorted 배열에 들어간다.
        // 왼쪽 혹은 오른쪽의 부분 배열 중 하나의 배열이라도 모든 원소가 sorted 배열에 들어간다면
        // 아래의 반복문은 조건을 만족하지 하지 않기 때문에 빠져 나온다.
        // sorted 배열에 들어가지 못한 부분 배열은 아래 쪽에서 채워진다.
        while (i <= mid && j <= right) {
            if (a[i] < a[j]) sorted[k++] = a[i++];
            else sorted[k++] = a[j++];
        }

        // mid < i 인 순간, i는 왼쪽 부분 배열의 인덱스를 관리하므로
        // 즉, 왼쪽 부분 배열이 sorted 에 모두 채워졌음을 의미한다.
        // 따라서 남아 있는 오른쪽 부분 배열의 값을 일괄 복사한다.
        if (mid < i) {
            for (l = j; l <= right; l++) sorted[k++] = a[l];
        } else {
            // mid > i 인 것은 오른쪽 부분 배열이 sorted 배열에 정렬된 상태로 모두 채워졌음을 의미한다.
            // 따라서, 왼쪽 부분 배열은 아직 남아 있는 상태이다.
            // 즉, 남아 있는 왼쪽 부분 배열의 값을 일괄 복사한다.
            for (l = i; l <= mid; l++) sorted[k++] = a[l];
        }

        // sorted 배열에 들어간 부분 배열을 합하여 정렬한 배열은
        // 원본 배열인 a 배열로 다시 복사한다.
        for (l = left; l <= right; l++) a[l] = sorted[l];
    }
}
