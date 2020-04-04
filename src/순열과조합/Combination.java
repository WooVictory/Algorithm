package 순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/03
 */
public class Combination {
    private static int[] comArr;
    private static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int r = Integer.parseInt(in[1]);

        // 조합 : 순서는 관심 없고 뽑은 유무만 생각한다.
        comArr = new int[r];
        //combination(n, r, 0, 1);
        reCombination(n, r, 0, 1);

    }

    private static void combination(int n, int r, int index, int target) {
        if (r == 0) {
            print();
            return;
        }

        if (target == n + 1) return;

        comArr[index] = target;
        combination(n, r - 1, index + 1, target + 1); // 뽑는 경우.
        combination(n, r, index, target + 1); // 안뽑는 경우.
    }


    private static void reCombination(int n, int r, int index, int target) {
        if (r == 0) {
            print();
            return;
        }

        if (target == n + 1) return;

        comArr[index] = target;
        reCombination(n, r - 1, index + 1, target); // 뽑는 경우.
        reCombination(n, r, index, target + 1); // 안뽑는 경우.
    }


    private static void print() {
        for (int value : comArr) System.out.print(value + " ");
        System.out.println();
    }
}
