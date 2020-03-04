package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/02
 * 비숍.
 */
public class boj1799_4 {
    private static int n, total = 0;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dy = {-1, -1, 1, 1};
    private static int[] dx = {-1, 1, -1, 1};
    // 좌상, 우상, 좌하, 우하
    // 좌표계 기준이어서 위로 올라가면 y가 -되고 아래로 내려가면 +
    // x가 오른쪽으로 가면 +, 왼쪽으로 가면 -

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        visit = new boolean[n][n];
        dfs(0, 0, 0, 1); // 흑색칸 탐색.
        int result = total;
        total = 0;

        visit = new boolean[n][n];
        dfs(0, 1, 0, -1); // 백색칸 탐색.
        System.out.println(result + total);
    }

    private static void dfs(int x, int y, int count, int color) {
        total = Math.max(total, count);

        if (y >= n) {
            x += 1;
            if (color == 1) y = (x % 2 == 0) ? 0 : 1;
            else y = (x % 2 == 0) ? 1 : 0;
        }

        if (x >= n) return;

        if (map[x][y] == 1) {
            if (isSafe(x, y)) {
                visit[x][y] = true;
                dfs(x, y + 2, count + 1, color);
                visit[x][y] = false;
            }
        }

        dfs(x, y + 2, count, color);
    }

    private static boolean isSafe(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            for (int j = 1; j <= n; j++) {
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

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
