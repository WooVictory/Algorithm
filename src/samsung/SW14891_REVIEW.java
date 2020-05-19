package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/19
 * 톱니바퀴.
 * 리뷰.
 */
public class SW14891_REVIEW {
    private static LinkedList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = (LinkedList<Integer>[]) new LinkedList[5];

        // 1. 4개의 톱니바퀴를 저장한다.
        for (int i = 1; i <= 4; i++) {
            list[i] = new LinkedList<>();
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                list[i].add(s.charAt(j) - '0');
            }
        }

        // 2. K번 만큼 톱니바퀴를 회전시킨다.
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
            if (list[i].get(0) == 1) {
                result += Math.pow(2, (i - 1));
            }
        }
        System.out.println(result);
    }

    // 인접한 왼쪽 톱니바퀴를 확인한다.
    private static void leftCheck(int num, int dir) {
        // 범위를 벗어나면 종료.
        if (!(1 <= num && num <= 4)) return;

        // 결국, 오른쪽 톱니바퀴의 인접한 왼쪽을 확인하기 위해 호출한 것이기 때문에
        // 오른쪽 톱니바퀴와 맞닿은 부분을 확인한다.
        // 서로 다른 극이라면 톱니바퀴를 회전할 수 있으며, 또한 이 톱니바퀴의 인접한 왼쪽 톱니바퀴도 확인해야 한다.(재귀호출 사용)
        // 그리고 이 톱니바퀴를 회전한다.
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

    // 시계 방향인지 반시계 방향인지에 따라 톱니바퀴를 회전시켜준다.
    private static void rotate(int num, int dir) {
        if (dir == 1) list[num].addFirst(list[num].pollLast());
        else list[num].addLast(list[num].pollFirst());
    }

    // 서로 맞닿은 극이 다른지 확인한다.
    // 다르면 -> true, 같으면 -> false.
    private static boolean check(int a, int b) {
        if (a < b) return !list[a].get(2).equals(list[b].get(6));
        else return !list[b].get(2).equals(list[a].get(6));
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
