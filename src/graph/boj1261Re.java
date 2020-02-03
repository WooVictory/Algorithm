package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 2020/02/03
 * 알고스팟.
 * 다시 풀어보기!
 * deque 사용.
 */
public class boj1261Re {
    private static int m, n;
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
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        bfs();
    }

    private static void bfs() {
        ArrayDeque<Spot> deque = new ArrayDeque<>();
        deque.addFirst(new Spot(1, 1, 0));
        visit[1][1] = true;
        // (1,1), (N,M)은 뚫려있다고 했으므로 앞 쪽에 넣어준다.

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

                // 빈 방인 경우, 바로 지나갈 수 있으니까 덱의 맨 앞에 넣어서 먼저 처리되도록 한다.
                if (a[nx][ny] == 0) {
                    deque.addFirst(new Spot(nx, ny, cost));
                } else if (a[nx][ny] == 1) {
                    // 벽인 경우, 바로 지나갈 수 없고 벽을 부수고 지나가야 하기 때문에 덱의 뒤쪽에 넣어서
                    // 빈 방이 먼저 처리되고 난 뒤에 나중에 처리되도록 한다.
                    a[nx][ny] = 0; // 벽을 부순다.
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
        int cost; // 최소 벽을 몇 개 부수었는지 저장하기 위한 변수.

        Spot(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}