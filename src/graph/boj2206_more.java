package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/31
 */
public class boj2206_more {
    private static int n, m, result = Integer.MAX_VALUE;
    private static int[][] a;
    private static boolean[][][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1][2];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        bfs();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void bfs() {
        LinkedList<Spot> q = new LinkedList<>();
        q.add(new Spot(1, 1, 1, 0));

        while (!q.isEmpty()) {
            Spot spot = q.remove();
            int x = spot.x, y = spot.y, distance = spot.distance, count = spot.count;

            if (x == n && y == m) {
                result = distance;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (a[nx][ny] == 0) {
                    if (!visit[nx][ny][count]) {
                        visit[nx][ny][count] = true;
                        q.add(new Spot(nx, ny, distance + 1, count));
                    }
                } else if (a[nx][ny] == 1) {
                    if (count < 1 && !visit[nx][ny][count + 1]) {
                        visit[nx][ny][count + 1] = true;
                        q.add(new Spot(nx, ny, distance + 1, count + 1));
                    }
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Spot {
        int x;
        int y;
        int distance;
        int count;

        Spot(int x, int y, int distance, int count) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.count = count;
        }
    }
}