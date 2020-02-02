package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/30
 * visit 배열은 벽을 부순 횟수를 저장하는 배열이다.
 * visit 배열이 내가 사용하던 distance 배열과 유사하다.
 * 하지만, 답이 안나온다... 내일 다시 풀어보기!
 */
public class boj2206_2 {
    private static int n, m;
    private static int[][] a, visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n + 1][m + 1];
        visit = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        result = Integer.MAX_VALUE;
        init();
        bfs();

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);

    }

    private static void init() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                visit[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static void bfs() {
        LinkedList<Spot> q = new LinkedList<>();
        q.add(new Spot(1, 1, 1, 0));
        visit[1][1] = 1;

        while (!q.isEmpty()) {
            Spot spot = q.remove();
            int x = spot.x, y = spot.y, distance = spot.distance, count = spot.count;

            if (x == n && y == m) {
                result = count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (visit[nx][ny] <= count) continue;

                // 빈 방일 경우.
                if (a[nx][ny] == 0) {
                    visit[nx][ny] = count;
                    q.add(new Spot(nx, ny, distance + 1, count));
                } else { // 벽일 경우.
                    if (count == 0) {
                        visit[nx][ny] = count + 1;
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

        public Spot(int x, int y, int distance, int count) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.count = count;
        }
    }
}
