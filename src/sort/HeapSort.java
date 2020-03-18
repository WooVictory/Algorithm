package sort;

/**
 * created by victory_woo on 2020/03/14
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {230, 10, 60, 550, 40, 220, 20};
        heapSort(arr);

        for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
    }

    private static void heapSort(int[] arr) {
        int n = arr.length;

        // max heap 초기화.
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int p = i; // 부모 노드.
        int l = i * 2 + 1; // 왼쪽 자식 노드.
        int r = i * 2 + 2; // 오른쪽 자식 노드.

        // 왼쪽 자식 노드와 부모 노드를 비교하여 큰 값을 부모 노드로 올린다.
        if (l < n && arr[p] < arr[l]) p = l;

        // 오른쪽 자식 노드와 부모 노드를 비교하여 큰 값을 부모 노드로 올린다.
        if (r < n && arr[p] < arr[r]) p = r;

        // 부모 노드를 가리키는 p 값이 바뀌면 위치를 교환하고
        // heapify()를 호출하여 과정을 반복한다.
        if (i != p) {
            swap(arr, p, i);
            heapify(arr, n, p);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
