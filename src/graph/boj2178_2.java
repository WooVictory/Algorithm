package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/28
 */
public class boj2178_2 {
    private static int n, m;
    private static int[][] a, d;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n + 1][m + 1];
        d = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - 48;
            }
        }

        bfs();
        System.out.println(d[n][m]);
    }

    private static void bfs() {
        LinkedList<S> q = new LinkedList<>();
        visit[1][1] = true;
        d[1][1] = 1;
        q.add(new S(1, 1));

        while (!q.isEmpty()) {
            S s = q.remove();
            int x = s.x;
            int y = s.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

                if (a[nx][ny] == 1 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    d[nx][ny] = d[x][y] + 1;
                    q.add(new S(nx, ny));
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}

class S {
    int x;
    int y;

    S(int x, int y) {
        this.x = x;
        this.y = y;
    }
}