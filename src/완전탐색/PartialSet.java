package 완전탐색;

/**
 * created by victory_woo on 24/05/2019
 * 부분 집합 구하기 - 재귀 함수 이용.
 */
public class PartialSet {
    private static int n = 3;
    private static boolean[] visited = new boolean[n];

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        getSet(arr, 0);
    }

    private static void getSet(int[] arr, int index) {
        if (index == n) {
            print(arr);
            return;
        }

        visited[index] = false;
        getSet(arr, index + 1);

        visited[index] = true;
        getSet(arr, index + 1);

    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(visited[i]){
                System.out.print(arr[i]+" ");
            }
        }
        System.out.println();
    }
}
