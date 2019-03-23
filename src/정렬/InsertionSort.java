package 정렬;

/**
 * created by victory_woo on 20/03/2019
 * 60000개 랜덤한 숫자를 선택 정렬로 정렬한 경우 : 382 걸림
 */
public class InsertionSort {
    public static void main(String[] args) {
        //int[] arr = {254, 3, 213, 64, 75, 56, 4, 324, 65, 78, 9, 5, 76, 3410, 8, 342, 76};
        int[] arr = new int[60000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 60000);
        }

        long start = System.currentTimeMillis();

        insertionSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("삽입 정렬 정렬 시간 : "+(end - start));

/*        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }*/

    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int standard = arr[i]; // 기준이 되는 수
            int index = i - 1; // 비교할 대상

            // index가 0보다 커야 하고 기준이 되는 수가 앞의 수들보다 작을 경우에
            // 반복문으로 들어가서 비교 대상을 한 칸씩 오른쪽으로 이동 시킴.
            // standard < arr[index] 여기에서 standard가 작은 경우
            // 비교 대상인 왼쪽 수가 더 작다는 말이므로 그냥 빈 자리에 들어가면 됨.
            // 그래서 while문 안 들어가고 빠져 나온다.
            while (0 <= index && standard < arr[index]) {
                arr[index + 1] = arr[index]; // 비교 대상이 큰 경우 오른쪽으로 한 칸 이동.
                index--;
            }

            arr[index+1] = standard;
        }
    }
}
