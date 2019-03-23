package 정렬;

/**
 * created by victory_woo on 20/03/2019
 * 60000개 랜덤한 숫자를 선택 정렬로 정렬한 경우 : 6590 걸림
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[60000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 60000);
        }

        long start_time = System.currentTimeMillis();
        // 버블 정렬
        bubbleSort(arr);
        long end_time = System.currentTimeMillis();
        System.out.println("버블 정렬 실행 시간 : " + (end_time - start_time));
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
