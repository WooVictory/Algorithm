package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/02
 * 비숍 - for 문 하나로 해결
 */
public class boj1799_3 {
    private static int n;
    private static int[][] map;
    private static int[][] colors;
    private static int[] dx = {1, 1, -1, -1};
    private static int[] dy = {1, -1, -1, 1};
    private static int[] answer = new int[2];
    private static boolean[] visit = new boolean[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n][n];
        colors = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);

                if (i % 2 == 0) {
                    if (j % 2 == 0) colors[i][j] = 1;
                } else {
                    if (j % 2 != 0) colors[i][j] = 1;
                }
            }
        }

        dfs(-1, 0, 1);
        dfs(-1, 0, 0);
        System.out.println(answer[0] + answer[1]);
    }

    private static void dfs(int v, int count, int color) {
        if (answer[color] < count) answer[color] = count;

        for (int i = v + 1; i < n * n; i++) {
            int c = i / n;
            int r = i % n;

            if (colors[c][r] != color) continue;

            if (map[c][r] == 1) {
                if (isSafe(c, r)) {
                    visit[i] = true;
                    dfs(i, count + 1, color);
                }
            }
        }

        if (v == -1) return;
        visit[v] = false;
    }

    private static boolean isSafe(int c, int r) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + r;
            int ny = dy[i] + c;
            int v = ny * n + nx;

            for (int j = 1; j <= n; j++) {
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (visit[v]) return false;

                nx += dx[i];
                ny += dy[i];
                v = ny * n + nx;
            }
        }
        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
