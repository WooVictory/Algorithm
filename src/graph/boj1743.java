package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/02/05
 * 음식물 피하기.
 * dfs.
 */
public class boj1743 {
    private static int n, m, max;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        int k = toInt(in[2]);

        a = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            int v1 = toInt(input[0]), v2 = toInt(input[1]);
            a[v1][v2] = 1;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i][j] != 0 && !visit[i][j]) {
                    max = 0;
                    dfs(i, j);

                    list.add(max);
                }
            }
        }
        System.out.println(Collections.max(list));
    }

    private static void dfs(int x, int y) {
        if (visit[x][y]) return;

        max++;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

            if (!visit[nx][ny] && a[nx][ny] != 0) dfs(nx, ny);
        }

    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
