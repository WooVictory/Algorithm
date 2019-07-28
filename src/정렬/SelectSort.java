package 정렬;

/**
 * created by victory_woo on 20/03/2019
 * 60000개 랜덤한 숫자를 선택 정렬로 정렬한 경우 : 6012 걸림
 */
public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {254, 3, 213, 64, 75, 56, 4, 324, 65, 78, 9, 5, 76, 3410, 8, 342, 76};

        //int[] arr = new int[60000];
        int[] arr = {7, 6, 5, 4, 1};

        /*for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 60000);
        }*/

        long start_time = System.currentTimeMillis();
        // SelectSort
        selectionSort(arr);
        long end_time = System.currentTimeMillis();
        System.out.println("선택 정렬 실행 시간 : " + (end_time - start_time));


    }

    private static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
                //printMap(arr);
            }
        }

    }

    private static void printMap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


}
