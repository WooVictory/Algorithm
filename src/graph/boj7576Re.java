package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/02
 * Re : 다시 풀어보는 문제임.
 */
public class boj7576Re {
    private static int n, m;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        m = toInt(in[0]);
        n = toInt(in[1]);

        a = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                a[i][j] = toInt(input[j - 1]);
            }
        }

        bfs();

        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                if (a[i][j] > result) result = a[i][j];
            }
        }

        System.out.println(result - 1);
    }

    private static void bfs() {
        LinkedList<Tomato> q = findTomato();

        while (!q.isEmpty()) {
            Tomato tomato = q.remove();
            int x = tomato.x, y = tomato.y;

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (visit[nx][ny]) continue;

                if (a[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    a[nx][ny] = a[x][y] + 1;
                    q.add(new Tomato(nx, ny));
                }
            }
        }
    }

    private static LinkedList<Tomato> findTomato() {
        LinkedList<Tomato> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i][j] == 1) {
                    q.add(new Tomato(i, j));
                }
            }
        }
        return q;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Tomato {
        int x;
        int y;

        Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}