package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/13
 */
public class boj14503_2 {
    private static int n, m;
    private static int[][] a;
    private static boolean[][] visit;
    //private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    // 서, 북, 동, 남 방향으로 돌아야 함.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n][m];
        visit = new boolean[n][m];

        String[] input = br.readLine().split(" ");
        int r = toInt(input[0]);
        int c = toInt(input[1]);
        int d = toInt(input[2]);

        for (int i = 0; i < n; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = toInt(num[j]);
            }
        }

        bfs(r, c, d);
        getResult();
    }

    private static void bfs(int x, int y, int d) {
        LinkedList<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, d));
        a[x][y] = 9;
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Robot robot = q.remove();
            int currentX = robot.x;
            int currentY = robot.y;
            int currentD = robot.d;

            int nextX, nextY, nextD;
            boolean isClean = false;

            for (int i = 0; i < 4; i++) {
                currentD = (currentD + 3) % 4;
                nextX = currentX + dx[currentD];
                nextY = currentY + dy[currentD];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;

                if (!visit[nextX][nextY] && a[nextX][nextY] == 0) {
                    q.add(new Robot(nextX, nextY, currentD));
                    visit[nextX][nextY] = true;
                    a[nextX][nextY] = 9;
                    isClean = true;
                    break;
                }
            }

            // 네 방향 모두 청소가 되었을 경우,
            // 후진할 수 있으면, 후진.
            // 아니면 종료.
            if (!isClean) {
                nextD = (currentD + 2) % 4;
                nextX = currentX + dx[nextD];
                nextY = currentY + dy[nextD];

                if (a[nextX][nextY] != 1) {
                    visit[nextX][nextY] = true;
                    q.add(new Robot(nextX, nextY, currentD));
                }
            }
        }
    }

    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j]) count++;
            }
        }
        System.out.println(count);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Robot {
        int x;
        int y;
        int d;

        public Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
