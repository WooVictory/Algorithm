package 순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/03
 */
public class PermutationWithLinkedList {
    private static LinkedList<Integer> list;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int r = Integer.parseInt(in[1]);

        list = new LinkedList<>();
        check = new boolean[n + 1];
        permutation(n, r);
        rePermutation(n, r);
    }

    // n개 중 r개를 뽑는 순열.(중복 X)
    // 재귀 호출의 개념을 사용한다.
    private static void permutation(int n, int r) {
        if (list.size() == r) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                check[i] = true;
                list.add(i);
                permutation(n, r);
                check[i] = false;
                list.removeLast();
            }
        }
    }

    private static void rePermutation(int n, int r) {
        if (list.size() == r) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            list.add(i);
            rePermutation(n, r);
            list.removeLast();
        }
    }

    private static void print() {
        for (int value : list) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
