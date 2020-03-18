package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/18
 * 톱니바퀴.
 * 삼성 기출.
 * 시뮬레이션
 * 다시 풀어보기!
 */
public class sw14891 {
    private static LinkedList<Integer>[] list = (LinkedList<Integer>[]) new LinkedList[5];
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            list[i] = new LinkedList<>();
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                int value = s.charAt(j) - '0';
                list[i].add(value);
            }
        }

        k = toInt(br.readLine());
        while (k-- > 0) {
            String[] in = br.readLine().split(" ");
            int n = toInt(in[0]);
            int dir = toInt(in[1]);

            left(n - 1, -dir); // 왼쪽으로 인접한 톱니바퀴를 확인한다.
            right(n + 1, -dir); // 오른쪽으로 인접한 톱니바퀴를 확인한다.
            rotate(n, dir); // 입력받은 번호의 톱니바퀴는 무조건 회전시킨다.
        }

        int result = 0;
        for (int i = 1; i <= 4; i++) {
            if (list[i].get(0) != 0) {
                result += Math.pow(2, i - 1);
            }
        }

        System.out.println(result);
    }

    // 왼쪽으로 인접한 톱니바퀴를 확인한다.
    // 이 톱니바퀴는 또, 자신의 왼쪽에 인접한 톱니바퀴를 확인해야 하기 때문에 재귀 호출을 이용한다.
    private static void left(int n, int d) {
        // 범위를 벗어나면 종료.
        if (!(1 <= n && n <= 4)) return;

        // 이 톱니바퀴가 인접한 왼쪽 톱니바퀴와 비교하여 회전할 수 있는지 확인한다.
        // 맞닿은 부분이 다르면 회전이 가능하다. 이때의 회전할 수 있으면 입력 받은 방향의 반대 방향으로 회전한다.
        // 회전이 가능하면 인접한 부분을 다시 확인하면서 인접한 부분은 지금의 반대 방향으로 회전해야 하기 때문에 반대 방향을 넘긴다.
        // 반면 지금의 자기 자신 톱니바퀴는 해당 방향으로 회전을 하기 때문에 방향 그래도 넘긴다.


        // 이 톱니바퀴가 인접한 톱니바퀴와 비교해 회전할 수 있는지 확인한다.
        // 여기서 check(n, n+1)인 이유는 다음과 같다.
        // n = 3일 때, 이미 위의 for 반복문에서 왼쪽인 2를 확인하기 위해 left 를 호출했다.
        // 3의 입장에서는 왼쪽에 위치한 2번과 맞닿은 부분을 확인해 회전할 수 있는지 확인해야 한다.
        // 따라서 check(n, n+1)을 통해서 현재 톱니바퀴와 이를 불렀던 톱니바퀴를 확인해 회전할 수 있는지 확인해야 한다.
        // 회전할 수 있다면 여기서의 왼쪽에 인접한 톱니바퀴에 대해 확인하기 위해 left(n-1, -d)를 호출한다.
        if (check(n, n + 1)) {
            left(n - 1, -d);
            rotate(n, d);
        }
    }

    // 오른쪽은 left()의 반대로 생각하면 된다.
    // 인접한 오른쪽 톱니바퀴를 확인하기 위해 재귀 호출을 이용한다.
    private static void right(int n, int d) {
        if (!(1 <= n && n <= 4)) return;

        if (check(n, n - 1)) {
            right(n + 1, -d);
            rotate(n, d);
        }
    }

    // d = 1 -> 시계 방향.
    // d = -1 -> 반시계 방향.
    // poll, remove 상관없음.
    // poll : empty 상황에서 null, remove : empty 상황에서 예외.
    private static void rotate(int n, int d) {
        if (d == 1) list[n].addFirst(list[n].pollLast());
        else list[n].addLast(list[n].pollFirst());
    }

    // 회전이 가능한지 확인한다. 맞닿은 부분이 같으면 회전할 수 없고, 맞닿은 부분이 다르면 회전할 수 있다.
    // 작은 톱니바퀴의 오른쪽인 get(2)와 큰 톱니바퀴의 왼쪽인 get(6)을 비교한다.
    private static boolean check(int a, int b) {
        if (a > b) return list[b].get(2) != list[a].get(6);
        else return list[a].get(2) != list[b].get(6);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
