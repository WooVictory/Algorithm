package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/19
 * 톱니바퀴.
 */
public class SW14891 {
    private static LinkedList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = (LinkedList<Integer>[]) new LinkedList[5];

        for (int i = 1; i <= 4; i++) {
            list[i] = new LinkedList<>();
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                list[i].add(s.charAt(j) - '0');
            }
        }

        int k = toInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] in = br.readLine().split(" ");
            int num = toInt(in[0]), dir = toInt(in[1]);

            leftCheck(num - 1, -dir);
            rightCheck(num + 1, -dir);
            rotate(num, dir);
        }

        int result = 0;
        for (int i = 1; i <= 4; i++) {
            if (list[i].get(0) != 0) {
                result += Math.pow(2, i - 1);
            }
        }

        System.out.println(result);
    }

    private static void leftCheck(int num, int dir) {
        if (!(1 <= num && num <= 4)) return;

        if (check(num, num + 1)) {
            leftCheck(num - 1, -dir);
            rotate(num, dir);
        }
    }

    private static void rightCheck(int num, int dir) {
        if (!(1 <= num && num <= 4)) return;

        if (check(num, num - 1)) {
            rightCheck(num + 1, -dir);
            rotate(num, dir);
        }
    }

    private static boolean check(int a, int b) {
        if (a < b) return !list[a].get(2).equals(list[b].get(6));
        else return !list[b].get(2).equals(list[a].get(6));
    }

    private static void rotate(int num, int dir) {
        if (dir == 1) list[num].addFirst(list[num].pollLast());
        else list[num].addLast(list[num].pollFirst());
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
