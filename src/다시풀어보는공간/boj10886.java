package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/04/03
 * 완탐 - 집합.
 * 그대로 조건 맞춰서 구현.
 */
public class boj10886 {
    private static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            String[] in = br.readLine().split(" ");
            String command = in[0];
            int operation;

            switch (command) {
                case "add":
                    operation = toInt(in[1]);

                    if (!list.contains(operation)) list.add(operation);
                    break;
                case "remove":
                    operation = toInt(in[1]);

                    if (list.contains(operation)) {
                        int index = list.indexOf(operation);
                        list.remove(index);
                    }
                    break;
                case "check":
                    operation = toInt(in[1]);
                    if (list.contains(operation)) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "toggle":
                    operation = toInt(in[1]);
                    if (list.contains(operation)) {
                        int index = list.indexOf(operation);
                        list.remove(index);
                    } else {
                        list.add(operation);
                    }
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
            }
        }
        System.out.println(sb.toString());
    }

    private static void all() {
        empty();
        for (int i = 1; i <= 20; i++) list.add(i);
    }

    private static void empty() {
        list.clear();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
