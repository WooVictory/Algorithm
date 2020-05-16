package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/15
 * 연산자 끼워넣기.
 * 0 : +
 * 1 : -
 * 2 : *
 * 3 : /
 */
public class SW14888_REVIEW {
    private static int n;
    private static int[] numbers;
    private static boolean[] visit;
    private static ArrayList<Integer> operations;
    private static LinkedList<Integer> list;
    private static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        numbers = new int[n];
        operations = new ArrayList<>();
        list = new LinkedList<>();

        String[] in = br.readLine().split(" ");
        for (int i = 0; i < n; i++) numbers[i] = toInt(in[i]);

        // 어떤 한 연산자의 갯수가 1 이상이라면 쪼개서 1개씩 저장한다.
        in = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            int operation = toInt(in[i]);
            while (operation-- > 0) operations.add(i);
        }

        int size = operations.size();
        visit = new boolean[size];
        // 순열을 통해 가능한 연산자의 순서를 모두 구한다.
        perm(size, n - 1);

        System.out.println(max);
        System.out.println(min);
    }

    // 순열.
    private static void perm(int r, int k) {
        if (k == list.size()) {
            // k개의 연산자의 순서를 구했으면 계산한다.
            check();
            return;
        }

        for (int i = 0; i < r; i++) {
            if (!visit[i]) {
                visit[i] = true;
                list.add(i);
                perm(r, k);

                visit[i] = false;
                list.removeLast();
            }
        }
    }

    private static void check() {
        print();

        // 연산자를 꺼내서 앞에서부터 차례대로 연산을 진행한다.
        // list.get(i)를 통해서 순열을 통해 만든 연산자의 순서에서 index 값을 이용해 하나씩 꺼낸다.
        // index 를 사용하여 연산자를 꺼낸다.
        // 그에 따라서 알맞는 연산을 수행한다.
        int sum = numbers[0];
        for (int i = 0; i < n - 1; i++) {
            int index = list.get(i);
            int op = operations.get(index);

            if (op == 0) sum += numbers[i + 1];
            else if (op == 1) sum -= numbers[i + 1];
            else if (op == 2) sum *= numbers[i + 1];
            else sum /= numbers[i + 1];
        }

        // 구한 sum 과 비교하여 최대, 최소값을 갱신한다.
        if (max < sum) max = sum;
        if (sum < min) min = sum;
    }

    private static void print() {
        for (int i : list) System.out.print(operations.get(i) + " ");
        System.out.println();
        System.out.println();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
