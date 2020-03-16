package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/16
 * 로봇 청소기
 * 시뮬레이션 + bfs
 */
public class sw14503Re {
    private static int n, m;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 방향 : 북동남서.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];

        in = br.readLine().split(" ");
        int x = toInt(in[0]), y = toInt(in[1]), d = toInt(in[2]);

        solve(x, y, d);
        check();
    }

    private static void solve(int x, int y, int d) {
        LinkedList<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, d));
        map[x][y] = 9;
        // 1. 현재 칸을 청소한다.

        // 큐에 있는 좌표를 꺼내 현재 칸을 청소하는 부분은 코드 상 a 쪽에서 처리가 된다.
        while (!q.isEmpty()) {
            Robot robot = q.remove();
            int currentX = robot.x, currentY = robot.y, currentD = robot.d;
            int nextX, nextY, nextD;
            boolean flag = false;

            // 2. 현재 위치에서 현재 방향을 기준으로 왼쪽부터 탐색을 진행한다.
            // 청소할 공간이 없다면 다음 방향을 찾기 위해 이 반복문을 돈다.(2-b)
            for (int i = 0; i < 4; i++) {
                // 현재 방향을 기준으로 왼쪽 방향을 찾는다.
                currentD = (currentD + 3) % 4;
                nextX = currentX + dx[currentD];
                nextY = currentY + dy[currentD];

                // 왼쪽 방향으로 회전한 칸이 범위에 맞는지 확인한다.
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;

                // a. 왼쪽 방향에 청소하지 않은 칸이 존재한다면 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
                // 코드 상에서는 이미 왼쪽 방향과 왼쪽 방향으로 회전했을 때의 좌표를 구한다. 그리고 그 방향의 칸이 청소하지 않았다면
                // 청소를 하고(1번 과정) 큐에 넣어준다. 그리고 1번부터 진행하기 위해 for 반복문을 break 로 탈출한다.
                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = 9;
                    q.add(new Robot(nextX, nextY, currentD));
                    flag = true;
                    break;
                }
            }

            // 네 방향 모두 청소되어 있거나 벽인 경우.
            // 바라보는 방향을 유지한 채로 후진을 할 수 있다.
            // 또한, 뒤쪽 방향이 벽이라 후진할 수 없는 경우에는 그냥 종료한다.
            if (!flag) {
                // 후진할 방향을 먼저 구한다.
                nextD = (currentD + 2) % 4;
                nextX = currentX + dx[nextD];
                nextY = currentY + dy[nextD];

                // 후진하는 쪽이 벽이 아니라면 후진할 수 있고, 청소를 해준다.
                // 그리고 큐에 넣어준다.
                // 벽이었다면 그대로 종료를 한다.
                if (map[nextX][nextY] != 1) {
                    map[nextX][nextY] = 9;
                    // 바라보는 방향을 유지한채로 후진하기 때문에 currentD 를 넣어준다.
                    q.add(new Robot(nextX, nextY, currentD));
                }
            }
        }
    }

    private static void check() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 9) count++;
            }
        }

        System.out.println(count);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }

    static class Robot {
        int x, y, d;

        Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
