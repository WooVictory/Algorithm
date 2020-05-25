package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/25
 * 톱니바퀴 다시 풀기.
 */
public class SW14891_REVIEW2 {
    private static LinkedList<Integer>[] list = new LinkedList[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력
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
            int num = toInt(in[0]);
            int dir = toInt(in[1]);

            // 인접한 왼쪽과 오른쪽 톱니바퀴를 확인한다.
            // 인접한 톱니바퀴는 현재 방향과 반대 방향으로 회전하므로 반대 방향을 인자로 넘겨준다.
            left(num - 1, -dir);
            right(num + 1, -dir);
            // 현재 톱니바퀴는 무조건 회전한다.
            rotate(num, dir);
        }

        // 12시 방향이 S극인 톱니바퀴만 확인하면 점수를 계산한다.
        int result = 0;
        for (int i = 1; i <= 4; i++) {
            if (list[i].get(0) == 1) {
                result += Math.pow(2, i - 1);
            }
        }
        System.out.println(result);
    }

    // 인접한 왼쪽 톱니바퀴를 확인한다.
    private static void left(int n, int dir) {
        if (n <= 0) return; // 범위를 벗어나면 종료!

        // 인접한 왼쪽과 현재 톱니바퀴의 맞닿은 부분이 서로 다른 극인지 확인한다.
        // 다른 극이어야 회전이 가능하다. -> 이 톱니바퀴의 인접한 왼쪽도 회전할 수 있는지 확인한다.
        // 회전 가능하면 이 톱니바퀴는 무조건 회전한다.
        if (check(n, n + 1)) {
            // 인접한 왼쪽 톱니바퀴는 반대 방향으로 회전하므로 반대 방향을 인자로 넘겨준다.
            left(n - 1, -dir);
            rotate(n, dir);
        }
    }

    // 인접한 오른쪽 톱니바퀴를 확인한다.
    private static void right(int n, int dir) {
        if (n >= 5) return; // 범위를 벗어나면 종료!

        // 인접한 오른쪽과 현재(n-1) 톱니바퀴의 맞닿은 부분이 서로 다른 극인지 확인한다.
        // 다른 극이어야 회전이 가능하다 -> 이 톱니바퀴의 인접한 오른쪽도 회전할 수 있는지 확인한다.
        // 회전 가능하면 이 톱니바퀴는 무조건 회전한다.
        if (check(n, n - 1)) {
            // 인접한 오른쪽 톱니바퀴는 반대 방향으로 회전하므로 반대 방향을 인자로 넘겨준다.
            right(n + 1, -dir);
            rotate(n, dir);
        }
    }

    // 서로 맞닿은 극이 달라야 회전이 가능하다.
    private static boolean check(int a, int b) {
        if (a < b) return !list[a].get(2).equals(list[b].get(6));
        else return !list[b].get(2).equals(list[a].get(6));
    }

    // LinkedList 를 이용하여 회전시킨다.
    private static void rotate(int n, int dir) {
        if (dir == 1) list[n].addFirst(list[n].removeLast());
        else list[n].addLast(list[n].removeFirst());
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
