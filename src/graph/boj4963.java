package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/28
 */
public class boj4963 {
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
    private static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    private static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = br.readLine().split(" ");
            w = toInt(input[0]);
            h = toInt(input[1]);

            if (w == 0 && h == 0) break;

            a = new int[h + 1][w + 1];
            visit = new boolean[h + 1][w + 1];
            int total = 0;

            for (int i = 1; i <= h; i++) {
                String[] num = br.readLine().split(" ");
                for (int j = 1; j <= w; j++) {
                    a[i][j] = toInt(num[j - 1]);
                }
            }

            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (a[i][j] == 1 && !visit[i][j]) {
                        total++;
                        //bfs(i, j);
                        dfs(i, j);
                    }
                }
            }

            System.out.println(total);
        }
    }

    private static void print() {
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs(int x, int y) {
        LinkedList<Rec> q = new LinkedList<>();
        q.add(new Rec(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Rec r = q.remove();

            for (int i = 0; i < 8; i++) {
                int nx = r.x + dx[i];
                int ny = r.y + dy[i];

                if (0 < nx && nx <= h && 0 < ny && ny <= w) {
                    if (a[nx][ny] == 1 && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        q.add(new Rec(nx, ny));
                    }
                }
            }
        }
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];


            if (0 < nx && nx <= h && 0 < ny && ny <= w) {
                if (a[nx][ny] == 1 && !visit[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}


class Rec {
    int x;
    int y;

    Rec(int x, int y) {
        this.x = x;
        this.y = y;
    }
}