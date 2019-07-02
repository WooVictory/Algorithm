package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 01/07/2019
 * bfs 로 풀어보기. - 적록색약.
 */
public class BOJ10026_bfs {
    private static StringBuilder sb = new StringBuilder();
    private static int n, components;
    private static char[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        // 초기화.
        a = new char[n][n];
        visited = new boolean[n][n];

        // 입력.
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    components++;
                    bfs(i, j);
                }
            }
        }

        sb.append(components).append(" ");
        components = 0;
        visited = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 'G') {
                    a[i][j] = 'R';
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    components++;
                    bfs(i, j);
                }
            }
        }

        sb.append(components);
        System.out.println(sb.toString());
    }

    private static void bfs(int x, int y) {
        LinkedList<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;

        char c = a[x][y];

        while (!q.isEmpty()) {
            Point p = q.remove();
            int nowX = p.x;
            int nowY = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                if (!visited[nx][ny]) {
                    if (a[nx][ny] == c) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}