package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/05
 * 적록색약.
 * dfs.
 */
public class boj10026 {
    private static char[][] a;
    private static boolean[][] visit;
    private static int n, count;
    private static char now;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new char[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String in = br.readLine();
            for (int j = 1; j <= n; j++) a[i][j] = in.charAt(j - 1);
        }

        go();

        System.out.print(count + " ");

        greenToRed();
        count = 0;
        visit = new boolean[n + 1][n + 1];

        go();

        System.out.println(count);
    }

    private static void go() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!visit[i][j]) {
                    count++;
                    now = a[i][j];
                    dfs(i, j);
                }
            }
        }
    }

    private static void greenToRed() {
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                if (a[i][j] == 'G') a[i][j] = 'R';
    }

    private static void dfs(int x, int y) {
        if (visit[x][y]) return;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;

            if (!visit[nx][ny] && a[nx][ny] == now) dfs(nx, ny);
        }
    }

    private static void print() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
