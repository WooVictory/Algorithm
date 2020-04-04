package 순열과조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/03
 */
public class CombinationTest {
    private static int[] comArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int r = Integer.parseInt(in[1]);

        comArr = new int[r];
        //combination(n, r, 0, 1);

        comArr = new int[r];
        reCombination(n, r, 0, 1);
    }

    // 순서를 신경쓰지 않고 뽑는 조합.
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

    // 중복을 허용하는 조합.
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
