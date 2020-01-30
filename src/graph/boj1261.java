package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 2020/01/30
 * 알고스팟.
 * distance 배열에 저장된 값을 사용해도 된다.
 * 또한, Spot 클래스의 cost 변수를 사용해도 된다.
 */
public class boj1261 {
    private static int n, m;
    private static int[][] a, distance;
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        m = toInt(in[0]); // 가로.
        n = toInt(in[1]); // 세로.

        a = new int[n + 1][m + 1];
        distance = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        bfs();
        System.out.println(distance[n][m]);
    }

    private static void bfs() {
        ArrayDeque<Spot> deque = new ArrayDeque<>();
        deque.addFirst(new Spot(1, 1, 0));
        visit[1][1] = true;

        while (!deque.isEmpty()) {
            Spot spot = deque.remove();
            int x = spot.x, y = spot.y, cost = spot.cost;

            if (x == n && y == m) {
                System.out.println(cost);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (visit[nx][ny]) continue;

                if (a[nx][ny] == 0) { // 1. 빈 방인 경우.
                    distance[nx][ny] = distance[x][y];
                    deque.addFirst(new Spot(nx, ny, cost));
                } else if (a[nx][ny] == 1) { // 2. 벽인 경우. 벽을 부수는 동작 추가.
                    a[nx][ny] = 0;
                    distance[nx][ny] = distance[x][y] + 1;
                    deque.addLast(new Spot(nx, ny, cost + 1));
                }

                visit[nx][ny] = true;
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Spot {
        int x;
        int y;
        int cost;

        Spot(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

}
