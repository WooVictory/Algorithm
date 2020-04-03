package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/03
 * 모든 순열.
 * N이 주어졌을 때, 1~N 까지의 수로 이루어진 순열을 사전순으로 출력함.
 * 따라서 N개 중에서 N개를 뽑는 순열로 생각하면 된다.
 */
public class boj10974 {
    private static int[] check;
    private static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        check = new int[n + 1];
        permutation(n, n);
    }

    private static void permutation(int n, int r) {
        if (list.size() == r) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (check[i] == 0) {
                list.add(i);
                check[i] = 1;
                permutation(n, r);
                check[i] = 0;
                list.removeLast();
            }
        }
    }

    private static void print() {
        for (int value : list) System.out.print(value + " ");
        System.out.println();
    }

}
