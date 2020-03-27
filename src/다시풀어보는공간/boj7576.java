package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/27
 * 토마토 1탄.
 * bfs() 탐색 문제.
 */
public class boj7576 {
    private static int m, n;
    private static int[][] map, distance;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int result = 0;
    // 행렬 기준으로 북동남서 방향.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        m = toInt(in[0]);
        n = toInt(in[1]);

        map = new int[n][m];
        distance = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) map[i][j] = toInt(in[j]);
        }

        solve();
        System.out.println(check() ? result : -1);

        // 익은 토마토가 인접한 익지 않은 토마토를 익게 만드는데 몇일이 걸리는지 확인하기 위함.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) System.out.print(distance[i][j] + " ");
            System.out.println();
        }
    }

    // 익지 않은 토마토가 1개라도 존재하는지 확인한다.
    // 익지 않은 토마토가 1개라도 있다면 false 반환. 모두 익었다면 true 반환.
    private static boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void solve() {
        LinkedList<Tomato> q = new LinkedList<>();
        // 1. 익은 토마토를 찾아 큐에 먼저 넣는다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) q.add(new Tomato(i, j));
            }
        }

        // 2. 큐에서 익은 토마토를 꺼낸다.
        while (!q.isEmpty()) {
            Tomato tomato = q.remove();
            int x = tomato.x;
            int y = tomato.y;

            // 3. 익은 토마토의 상하좌우를 탐색하며 익지 않은 토마토를 익게하고 큐에 넣는다.
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위에서 벗어나면 다음을 검사한다.
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                // 방문한 적이 있으면 다음을 검사한다.
                if (visit[nx][ny]) continue;

                // 다음 정점이 익지 않은 토마토일 경우에만 탐색을 시작한다. 즉, 큐에 넣는다.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    distance[nx][ny] = distance[x][y] + 1;
                    q.add(new Tomato(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }

        // distance 배열을 탐색하며 일수를 계산하여 result 값에 갱신한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (result < distance[i][j]) result = distance[i][j];
            }
        }
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