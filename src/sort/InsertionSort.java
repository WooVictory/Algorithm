package sort;

/**
 * created by victory_woo on 2020/03/09
 */
public class InsertionSort {
    public static void main(String[] args) {

        int[] arr = {7, 6, 2, 4, 3, 9, 1};
        sort(arr);

    }

    private static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int standard = arr[i]; // 기준이 되는 수.
            int index = i - 1; // 기준이 되는 수 왼쪽의 원소들을 탐색하기 위한 인덱스.

            // index 가 0보다 크거나 같고, 왼쪽에 있는 원소들 중 standard 즉, 기준이 되는 수보다 큰 수가 있으면
            // 오른쪽으로 한 칸씩 이동시켜 준다.
            // 왼쪽에 있는 수가 기준이 되는 수보다 작은 경우에는 while 문을 타지 않고 밖으로 빠져 나간다.
            // 이때는 기준이 되는 수가 빈 자리에 들어가면 되기 때문이다.
            while ((0 <= index) && standard < arr[index]) {
                arr[index + 1] = arr[index]; // 왼쪽에 있는 수가 기준이 되는 수보다 큰 경우, 오른쪽으로 이동한다.
                index--;
            }
            arr[index + 1] = standard;

            print(arr, i);
        }
    }

    private static void print(int[] arr, int step) {
        System.out.print(step + "단계 : ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }
}
