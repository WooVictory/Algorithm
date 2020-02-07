package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/07
 */
public class boj3055_3 {
    private static int R, C;
    private static char[][] a;
    private static boolean[][] visit;
    private static LinkedList<Spot> q = new LinkedList<>();
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        R = toInt(in[0]);
        C = toInt(in[1]);

        a = new char[R + 1][C + 1];
        visit = new boolean[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            String value = br.readLine();
            for (int j = 1; j <= C; j++) {
                a[i][j] = value.charAt(j - 1);
            }
        }

        bfs();
        System.out.println(result == Integer.MAX_VALUE ? "KAKTUS" : result);
        print();
    }

    private static void print(){
        for (int i=1;i<=R;i++){
            for (int j=1;j<=C;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void bfs() {
        findWater();
        findHedgehog();

        while (!q.isEmpty()) {
            Spot spot = q.remove();
            char c = spot.c;
            int x = spot.x, y = spot.y, cost = spot.cost;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > R || ny > C) continue;
                if (visit[nx][ny]) continue;

                // 큐에서 꺼낸 현재 정점이 비어있는 곳일 경우.
                if (c == '*') {
                    if (a[nx][ny] != 'X' && a[nx][ny] != '*' && a[nx][ny] != 'D') {
                        a[nx][ny] = '*';
                        visit[nx][ny] = true;
                        q.add(new Spot(nx, ny, c, cost));
                    }
                }

                if (c == 'S') {
                    if ((a[nx][ny] == '.' || a[nx][ny] == 'D') && a[nx][ny] != 'X' && a[nx][ny] != '*') {
                        visit[nx][ny] = true;
                        if (a[nx][ny] == 'D') {
                            result = cost + 1;
                            return;
                        }
                        a[nx][ny] = 'S';
                        q.add(new Spot(nx, ny, c, cost + 1));
                    }
                }
            }
        }
    }

    private static void findWater() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (a[i][j] == '*') q.add(new Spot(i, j, '*', 0));
            }
        }
    }

    private static void findHedgehog() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (a[i][j] == 'S') q.add(new Spot(i, j, 'S', 0));
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Spot {
        int x;
        int y;
        char c;
        int cost;

        public Spot(int x, int y, char c, int cost) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.cost = cost;
        }
    }
}
