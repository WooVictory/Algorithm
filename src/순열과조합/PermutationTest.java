package 순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/03
 */
public class PermutationTest {
    private static LinkedList<Integer> list = new LinkedList<>();
    private static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int r = Integer.parseInt(in[1]);

        check = new int[n + 1];
        //perm(n, r);
        rePerm(n, r);

    }

    // 순열 구하는 함수. (순서가 중요하다.)
    private static void perm(int n, int r) {
        if (list.size() == r) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (check[i] == 0) {
                list.add(i);
                check[i] = 1;
                perm(n, r);

                check[i] = 0;
                list.removeLast();
            }
        }
    }

    // 원소의 중복을 허용한 중복 순열.
    private static void rePerm(int n, int r) {
        if (list.size() == r) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            list.add(i);
            rePerm(n, r);
            list.removeLast();
        }
    }

    private static void print() {
        for (int value : list) System.out.print(value + " ");
        System.out.println();
    }

}
