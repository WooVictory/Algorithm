package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/15
 * 연산자 끼워넣기.
 */
public class SW14888 {
    private static int n;
    private static int[] numbers;
    private static LinkedList<Integer> list;
    private static boolean[] visit;
    private static ArrayList<Character> ops;
    private static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        numbers = new int[n];
        list = new LinkedList<>();

        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) numbers[i] = toInt(in[i]);

        in = br.readLine().split(" ");

        ops = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int op = toInt(in[i]);
            while (op-- > 0) {
                ops.add(getOperation(i));
            }
        }

        int size = ops.size();
        visit = new boolean[size];
        perm(size, n - 1);

        System.out.println(max);
        System.out.println(min);

    }

    private static char getOperation(int type) {
        if (type == 0) return '+';
        else if (type == 1) return '-';
        else if (type == 2) return '*';
        else return '/';
    }

    private static void perm(int c, int k) {
        if (list.size() == k) {
            check();
            return;
        }

        for (int i = 0; i < c; i++) {
            if (!visit[i]) {
                visit[i] = true;
                list.add(i);

                perm(c, k);
                visit[i] = false;
                list.removeLast();
            }
        }
    }

    private static void check() {
        int sum = numbers[0];
        for (int i = 0; i < n - 1; i++) {
            char operation = ops.get(list.get(i));
            if (operation == '+') sum += numbers[i + 1];
            else if (operation == '-') sum -= numbers[i + 1];
            else if (operation == '*') sum *= numbers[i + 1];
            else sum /= numbers[i + 1];
        }

        max = Math.max(sum, max);
        min = Math.min(sum, min);
    }

    private static int getSum(char operation, int a, int b) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
