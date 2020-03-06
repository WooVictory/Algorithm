package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 2020/03/06
 */
public class sw3190 {
    private static int n, k, l;
    private static int[][] map;
    private static int[] dir;
    private static int[] time;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static ArrayDeque<Dot> snake = new ArrayDeque<>();
    // 북동남서 순서이며, 기준은 행렬 기준으로 표현.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n + 1][n + 1];

        // 사과의 위치를 표시해준다.
        k = toInt(br.readLine());
        while (k-- > 0) {
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]);
            int y = toInt(in[1]);

            map[x][y] = 1;
        }

        // 시간과 방향을 입력받아 저장한다.
        l = toInt(br.readLine());
        time = new int[l];
        dir = new int[l];

        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(" ");
            int t = toInt(in[0]);
            int d = getDirection(in[1]);

            time[i] = t;
            dir[i] = d;
        }

        solve();
    }

    private static void solve() {
        snake.add(new Dot(1, 1));
        int second = 0, timeIndex = 0, snakeDir = 1; // 뱀의 초기 방향은 오른쪽.
        map[1][1] = 2; // 뱀의 몸을 나타낸다.

        while (true) {
            // 시간이 지났을 때, 방향을 바꾸기 위함이다.
            if (timeIndex < l && time[timeIndex] == second) {
                snakeDir = (snakeDir + dir[timeIndex]) % 4;
                timeIndex++;
            }

            // 1. 뱀의 몸 길이를 늘려 머리를 다음 칸에 놓는다.
            int nx = snake.getFirst().x + dx[snakeDir];
            int ny = snake.getFirst().y + dy[snakeDir];

            // 뱀이 벽에 부딪히면 게임이 끝난다.
            if (nx <= 0 || ny <= 0 || nx > n || ny > n) {
                System.out.println(second + 1);
                break;
            }

            // 뱀이 자신의 몸에 부딪히면 게임이 끝난다.
            if (map[nx][ny] == 2) {
                System.out.println(second + 1);
                break;
            }

            // 2. 다음 칸에 사과가 있다면 꼬리는 그대로 있고, 머리를 추가한다.(addFirst())
            if (map[nx][ny] == 1) {
                map[nx][ny] = 2;
                snake.addFirst(new Dot(nx, ny));
            }
            // 3. 다음 칸에 사과가 없다면, 머리는 옮기고 꼬리를 잘라낸다.
            else if (map[nx][ny] == 0) {
                // 즉, 몸 길이를 줄여서 꼬리를 잘라낸다. 하지만, 머리는 다음 칸으로 갔으므로 길이는 유지한다.
                map[nx][ny] = 2;
                //snake.add(new Dot(nx, ny));
                snake.addFirst(new Dot(nx, ny));

                // 꼬리를 잘라낸다. 그리고 꼬리가 있던 부분은 0으로 돌려놓는다.
                Dot tail = snake.removeLast();
                map[tail.x][tail.y] = 0;
            }

            second++;
        }

    }

    static class Dot {
        int x, y;

        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    // D : 오른쪽(1)
    // L : 왼쪽(3)
    private static int getDirection(String dir) {
        if (dir.equals("D")) return 1;
        else return 3;
    }
}
