package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/15
 * 로봇 청소기.
 */
public class SW14503 {
    private static int n, m, count = 0;
    private static int[][] map;
    private static boolean[][] visit;
    private static int x, y, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        visit = new boolean[n][m];

        in = br.readLine().split(" ");
        x = toInt(in[0]);
        y = toInt(in[1]);
        d = toInt(in[2]);

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        solve();
        System.out.println(count+1);
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북동남서 방향.

    private static void solve() {
        LinkedList<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, d));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Robot cur = q.remove();
            int curX = cur.x, curY = cur.y, curD = cur.d;
            int nextX = curX, nextY = curY, nextD;

            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                curD = (curD + 3) % 4;
                nextX = curX + dx[curD];
                nextY = curY + dy[curD];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
                if (visit[nextX][nextY]) continue;

                if (map[nextX][nextY] == 0) {
                    visit[nextX][nextY] = true;
                    map[nextX][nextY] = 3;
                    q.add(new Robot(nextX, nextY, curD));
                    count++;
                    flag = false;
                    break;
                }
            }

            if (flag) {
                nextD = (curD + 2) % 4;
                nextX = curX + dx[nextD];
                nextY = curY + dy[nextD];

                if (map[nextX][nextY] != 1) {
                    q.add(new Robot(nextX, nextY, curD));
                    visit[nextX][nextY] = true;
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Robot {
        int x;
        int y;
        int d;

        Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
