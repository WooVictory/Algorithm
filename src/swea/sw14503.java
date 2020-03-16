package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/16
 * 로봇 청소기.
 * 시뮬레이션 + bfs
 * 다시 풀어보기!
 */
public class sw14503 {
    private static int n, m;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북동남서 방향.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];

        in = br.readLine().split(" ");
        int x = toInt(in[0]), y = toInt(in[1]), d = toInt(in[2]);

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        solve(x, y, d);
        check();
    }

    private static void solve(int x, int y, int d) {
        LinkedList<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, d));
        map[x][y] = 9;

        while (!q.isEmpty()) {
            Robot r = q.remove();
            int currentX = r.x, currentY = r.y, currentD = r.d;
            int nextX, nextY, nextD; // nextD : 후진할 방향에 대해서 값을 갖는다.
            boolean flag = false;

            // 1번과 2번 과정이 있다.
            for (int i = 0; i < 4; i++) {
                currentD = (currentD + 3) % 4;
                nextX = currentX + dx[currentD];
                nextY = currentY + dy[currentD];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;

                if (map[nextX][nextY] == 0) {
                    q.add(new Robot(nextX, nextY, currentD));
                    map[nextX][nextY] = 9;
                    flag = true;
                    break;
                }
            }

            // 네 방향 모두 청소되어 있거나 벽인 경우에는 후진해야 한다.
            if (!flag) {
                nextD = (currentD + 2) % 4;
                nextX = currentX + dx[nextD];
                nextY = currentY + dy[nextD];

                // 벽이 아니면 후진한다.
                // 벽이 아니라면 종료한다.
                if (map[nextX][nextY] != 1) {
                    // 바라보는 방향을 유지한 채로 한칸 후진하기 때문에 방향은 현재 방향을 유지한다.
                    q.add(new Robot(nextX, nextY, currentD));
                    map[nextX][nextY] = 9;
                }
            }
        }
    }

    private static void check() {
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (map[i][j] == 9) count++;


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
