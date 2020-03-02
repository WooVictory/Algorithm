package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/02
 * 비숍.
 * dfs, 백트래킹
 *
 */
public class boj1799Re {
    private static int n;
    private static int[][] map;
    private static boolean[][] visit;
    private static int blackCount = 0, total = 0;
    private static int[] dx = {-1, 1, -1, 1};
    private static int[] dy = {1, 1, -1, -1};
    // 대각선 방향에 대한 dx, dy

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = toInt(in[j - 1]);
            }
        }

        visit = new boolean[n + 1][n + 1];
        blackSearch(1, 1, 0, 1);
        total += blackCount;
        blackCount = 0;
        visit = new boolean[n + 1][n + 1];
        blackSearch(1, 2, 0, 2);

        System.out.println(total+blackCount);
    }

    private static void blackSearch(int x, int y, int count, int color) {
        blackCount = Math.max(blackCount, count);

        if (y > n) {
            x += 1;
            if (color == 1) y = (x % 2 == 0) ? 2 : 1;
            else if (color == 2) y = (x % 2 == 0) ? 1 : 2;
        }

        if (x > n) return;

        if (isSafe(x, y)) {
            visit[x][y] = true;
            blackSearch(x, y + 2, count + 1, color);
            visit[x][y] = false;
        }

        blackSearch(x, y + 2, count, color);
    }

    private static boolean isSafe(int x, int y) {
        if (map[x][y] == 0) return false;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            for (int j = 1; j <= n; j++) {
                if (nx <= 0 || ny <= 0 || nx > n || ny > n) continue;

                if (visit[nx][ny]) return false;

                nx += dx[i];
                ny += dy[i];
            }
        }

        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
