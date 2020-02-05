package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/05
 * 유기농 배추.
 * dfs.
 * 연결 요소의 개수를 찾는 문제와 동일.
 */
public class boj1012 {
    private static int m, n, k;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            m = toInt(in[0]);
            n = toInt(in[1]);
            k = toInt(in[2]);

            a = new int[m + 1][n + 1];
            visit = new boolean[m + 1][n + 1];

            // 0 : 땅, 1 : 배추가 심어져 있음.
            // 입력 받은 즉시, 배추가 심어져 있는 곳을 채운다.
            for (int i = 0; i < k; i++) {
                String[] point = br.readLine().split(" ");
                int x = toInt(point[0]), y = toInt(point[1]);

                a[x][y] = 1;
            }

            int total = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == 1 && !visit[i][j]) {
                        total++;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(total);
        }
    }

    private static void dfs(int x, int y) {
        if (visit[x][y]) return;

        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;

            if (!visit[nx][ny] && a[nx][ny] == 1) dfs(nx, ny);
        }
    }

    // 디버깅용.
    private static void print() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
