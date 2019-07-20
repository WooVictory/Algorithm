package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 20/07/2019
 * sw 역량 기출 문제 : 뱀.
 * review
 */
public class sw3190 {
    private static int n, k, l, count;
    private static int[][] map;
    private static int[] time;
    private static int[] dir;
    private static ArrayDeque<Pair> snake;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        snake = new ArrayDeque<>();
        n = parse(br.readLine()); // 칸의 개수.
        k = parse(br.readLine()); // 사과의 개수.

        map = new int[n + 1][n + 1];

        // 사과의 위치를 놓는다.
        while (k-- > 0) {
            String[] in = br.readLine().split(" ");
            int x = parse(in[0]);
            int y = parse(in[1]);

            map[x][y] = 1;
        }

        l = parse(br.readLine());
        time = new int[l];
        dir = new int[l];

        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(" ");
            time[i] = parse(in[0]);
            dir[i] = getDir(in[1]);
        }

        solution();
    }

    private static void solution() {
        int second = 0;
        int snakeDir = 1;
        int timeIdx = 0;
        map[1][1] = 2; // 뱀의 시작 위치는 (1,1)
        snake.add(new Pair(1, 1));

        while (true) {
            // 시간이 지나면 방향을 바꿔준다.
            if (timeIdx < l && time[timeIdx] == second) {
                snakeDir = (snakeDir + dir[timeIdx]) % 4;
                timeIdx++;
            }

            // 1. 다음에 갈 위치를 구한다. 즉, 뱀의 머리를 놓을 위치.
            int nx = snake.getFirst().x + dx[snakeDir];
            int ny = snake.getFirst().y + dy[snakeDir];

            // 벽인지 확인한다.
            if (nx <= 0 || nx > n || ny <= 0 || ny > n) {
                System.out.println(second + 1);
                break;
            }

            // 뱀 자신의 몸인지 확인한다.
            if (map[nx][ny] == 2) {
                System.out.println(second + 1);
                break;
            }

            // 2. 다음에 이동할 칸에 사과가 있다면, 사과를 먹고, 꼬리는 그대로 냅두고, 몸의 길이를 늘린다.
            if (map[nx][ny] == 1) {
                map[nx][ny] = 2; // 뱀의 몸이라는 것을 표시.
                snake.addFirst(new Pair(nx, ny));
            } else if (map[nx][ny] == 0) {
                // 3. 다음에 이동할 칸에 사과가 없다면, 꼬리를 자른다. 몸의 길이는 줄인다.
                map[nx][ny] = 2;
                snake.addFirst(new Pair(nx, ny));

                // 꼬리를 자른다.
                Pair tail = snake.removeLast();
                map[tail.x][tail.y] = 0; // 뱀의 몸을 0으로 바꿈으로써 꼬리를 자른다.
            }
            // 시간을 증가시킨다.
            second++;
        }
    }


    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    private static int getDir(String d) {
        if (d.equals("D")) {
            return 1;
        } else {
            return 3;
        }
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
