package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 2020/03/06
 */
public class sw3190Re {
    private static int n, l;
    private static int[][] map;
    private static int[] times;
    private static int[] dirs;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static ArrayDeque<Dot> snake = new ArrayDeque<>();
    // 방향은 북동남서 순서이다.
    // 행렬의 좌표를 계산하듯이 고려했다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        map = new int[n + 1][n + 1];

        int k = toInt(br.readLine());
        while (k-- > 0) {
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]), y = toInt(in[1]);

            map[x][y] = 1;
        }

        l = toInt(br.readLine()); // 시간과 방향의 갯수.
        times = new int[l];
        dirs = new int[l];
        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(" ");
            int t = toInt(in[0]), d = getDirection(in[1]);
            times[i] = t;
            dirs[i] = d;
        }
        solve();
    }

    // 2 : 뱀의 몸을 의미한다.
    private static void solve() {
        map[1][1] = 2;
        snake.add(new Dot(1, 1));

        // 뱀의 초기 방향은 오른쪽이므로 1로 설정.
        // second, timeIndex 는 모두 0부터 시작한다.
        int second = 0, timeIndex = 0, snakeDir = 1;

        while (true) {

            // 시간이 지나게 되면 방향을 바꿔줘야 한다.
            if (timeIndex < l && times[timeIndex] == second) {
                snakeDir = (snakeDir + dirs[timeIndex]) % 4;
                timeIndex++;
                // 시간이 지났기 때문에 인덱스 증가.
            }

            // 1. 뱀이 몸의 길이를 확장시켜 머리를 먼저 둔다.
            int nx = snake.getFirst().x + dx[snakeDir];
            int ny = snake.getFirst().y + dy[snakeDir];

            // 뱀이 벽에 부딪힐 경우, 결과를 출력하고 종료한다.
            if (nx <= 0 || ny <= 0 || nx > n || ny > n) {
                System.out.println(second + 1);
                break;
            }

            // 뱀이 자신의 몸에 부딪힐 경우, 결과를 출력하고 종료한다.
            if (map[nx][ny] == 2) {
                System.out.println(second + 1);
                break;
            }

            // 2. 다음 칸에 사과가 있는 경우.
            if (map[nx][ny] == 1) {
                map[nx][ny] = 2;
                snake.addFirst(new Dot(nx, ny));
            } else if (map[nx][ny] == 0) {
                // 3. 다음 칸에 사과가 없는 경우.
                map[nx][ny] = 2;
                snake.addFirst(new Dot(nx, ny));

                Dot tail = snake.removeLast();
                map[tail.x][tail.y] = 0;
            }

            second++;
        }

    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }


    // D : 오른쪽, 1.
    // L : 왼쪽, 3.
    private static int getDirection(String dir) {
        if (dir.equals("D")) return 1;
        else return 3;
    }

    static class Dot {
        int x;
        int y;

        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
