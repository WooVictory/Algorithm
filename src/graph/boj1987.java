package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/25
 */
public class boj1987 {
    private static int r, c, count = 1, max = 1;
    private static int[][] a;
    private static boolean[] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        r = toInt(in[0]);
        c = toInt(in[1]);

        a = new int[r + 1][c + 1];
        visit = new boolean[26];

        for (int i = 1; i <= r; i++) {
            String s = br.readLine();
            for (int j = 1; j <= c; j++) {
                a[i][j] = s.charAt(j - 1) - 65;
            }
        }

        dfs(1, 1);
        System.out.println(max);
    }

    private static void dfs(int x, int y) {
        int alpha = a[x][y];
        if (visit[alpha]) return;
        visit[alpha] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > r || ny > c) continue;

            int newAlpha = a[nx][ny];
            if (!visit[newAlpha]) {
                count++;
                max = Math.max(max, count);

                dfs(nx, ny);
            }
        }

        count--;
        visit[alpha] = false;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
