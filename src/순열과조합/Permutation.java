package 순열과조합;

/**
 * created by victory_woo on 2020/04/03
 * 재귀 호출을 이용해 순열을 구하는 공식!
 */
public class Permutation {
    private static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static int len = arr.length;

    public static void main(String[] args) {
        perm(0, 3);
    }

    private static void perm(int depth, int limit) {
        if (depth == limit) {
            print(limit);
            return;
        }

        for (int i = depth; i < len; i++) {
            swap(i, depth);
            perm(depth + 1, limit);
            swap(i, depth);
        }
    }

    private static void print(int limit) {
        for (int i = 0; i < limit; i++) {
            if (i == limit - 1) System.out.println(arr[i]);
            else System.out.print(arr[i] + " ");
        }
    }

    private static void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


}
