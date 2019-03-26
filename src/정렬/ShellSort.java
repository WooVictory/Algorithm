package 정렬;

/**
 * created by victory_woo on 23/03/2019
 * 60000개 랜덤한 숫자를 쉘 정렬로 정렬한 경우 : 13 걸림.
 * 100000개 랜덤한 숫자를 쉘 정렬로 정렬한 경우 : 22ms 걸림.
 * 백만개 역순 정렬 = 72ms
 * 백만개 랜덤 정렬 => 337ms 걸림.
 */
public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {254, 3, 213, 64, 75, 56, 4, 324, 65, 78, 9, 5, 76, 3410, 8, 342, 76};
        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }

        /*int size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = size--;
        }*/

        long start = System.currentTimeMillis();

        shellSort(arr, arr.length);
        long end = System.currentTimeMillis();



        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("쉘 정렬 정렬 시간 : " + (end - start));

    }

    private static void shellSort(int[] arr, int n) { // n : size
        // gap을 2로 나눠준다.
        for (int gap = n / 2; gap > 0; gap = gap / 2) {
            if (gap % 2 == 0) { // gap이 짝수이면 +1을 해준다.
                gap++;
            }

            for (int j = 0; j < gap; j++) {
                insertionSort(arr, j, n - 1, gap);
            }
        }

    }

    private static void insertionSort(int[] arr, int first, int last, int gap) {
        for (int i = first + gap; i <= last; i = i + gap) {
            int standard = arr[i]; // 기준이 되는 수
            int index = i - gap; // 비교할 대상이 되는 수

            while (first <= index && standard < arr[index]) {
                arr[index + gap] = arr[index];
                index -= gap;
            }

            arr[index + gap] = standard;
        }
    }

 /*   private static void insertionSort(int[] arr, int first, int last, int gap) {
        int i, j, key;

        // 삽입 정렬에서도 첫 번째 원소는 정렬을 할 필요가 없으므로
        // 그 다음 인덱스 값을 key값으로 설정해줬기 때문에 초기값은
        // first + gap이다.
        for (i = first + gap; i <= last; i += gap) {
            // 각 반복별로 인덱스 i값에 gap을 더해줌으로써 부분 리스트 삽입 정렬을 구현
            key = arr[i];

            // 키 값 이전의 배열의 값들을 조사하면서 key값이 들어갈 위치를 찾는다.
            for (j = i - gap; j >= first && arr[j] > key; j -= gap) {
                // 마찬가지로 각 반복별로 1을 감소시키는 것이 아닌 gap을 감소시킴으로써
                // 부분리스트의 삽입 정렬을 구현한다.
                arr[j + gap] = arr[j];
            }
            arr[j + gap] = key;
        }


    }*/
}
