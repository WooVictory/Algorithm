package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/18
 * 톱니바퀴.
 * 삼성 기출.
 * 시뮬레이션.
 */
public class sw14891Re {
    // LinkedList 타입의 배열. 4개의 톱니바퀴를 관리한다.
    private static LinkedList<Integer>[] list = (LinkedList<Integer>[]) new LinkedList[5];

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

        int k = toInt(br.readLine());
        while (k-- > 0) {
            String[] in = br.readLine().split(" ");
            int n = toInt(in[0]);
            int d = toInt(in[1]);

            left(n - 1, -d); // n번 톱니바퀴의 왼쪽을 확인한다.
            right(n + 1, -d); // n번 톱니바퀴의 오른쪽을 확인한다.
            rotate(n, d); // 입력받은 톱니바퀴 번호는 무조건 d 방향으로 회전한다.
        }

        int result = 0;
        for (int i = 1; i <= 4; i++) {
            if (list[i].get(0) != 0) {
                result += Math.pow(2, i - 1);
            }
        }
        System.out.println(result);
    }

    // 왼쪽에 대해서 확인한다.
    // 이 함수를 호출하면 정점은 n이 되고, 함수를 호출한 쪽의 정점은 n+1이 된다.
    // 따라서 check(n, n+1)을 통해서 맞닿은 면이 회전이 가능한지 확인하는 것이 맞다.
    // 회전이 가능하다면 그 정점을 기준으로 또 인접한 왼쪽 톱니바퀴를 확인한다.(재귀 호출을 통해서)
    // 인접한 정점을 확인하고 회전이 가능하다면 반대 방향으로 회전해야 하기 때문에 -d를 넘긴다.
    // 해당 정점은 그 방향 그대로 회전하기 때문에 rotate()에는 d를 넘긴다.
    private static void left(int n, int d) {
        if (!(1 <= n && n <= 4)) return;

        if (check(n, n + 1)) {
            left(n - 1, -d);
            rotate(n, d);
        }
    }

    // 오른쪽에 대해서 확인한다.
    // 과정은 left()와 동일하며, 방향만 반대 쪽일 뿐이다.
    private static void right(int n, int d) {
        if (!(1 <= n && n <= 4)) return;

        if (check(n, n - 1)) {
            right(n + 1, -d);
            rotate(n, d);
        }
    }

    // 회전할 수 있는지 여부를 반환한다.
    // 맞닿은 부분이 같으면 회전할 수 없고, 다르다면 회전할 수 있다.
    // 작은 톱니바퀴의 오른쪽(get(2))와 큰 톱니바퀴의 왼쪽(get(6))을 비교한다.
    private static boolean check(int a, int b) {
        if (a > b) {
            return list[b].get(2) != list[a].get(6);
        } else {
            return list[a].get(2) != list[b].get(6);
        }
    }

    // 회전한다.
    // d = 1 이면 시계 방향이므로 맨 뒤쪽의 요소가 맨 앞으로 온다.
    // d = -1 이면 반시계 방향이므로 맨 앞쪽의 요소가 맨 뒤쪽으로 간다.
    private static void rotate(int n, int d) {
        if (d == 1) {
            // 시계 방향인 경우.
            list[n].addFirst(list[n].removeLast());
        } else {
            // 반시계 방향인 경우.
            list[n].addLast(list[n].removeFirst());
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
