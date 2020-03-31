package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/31
 * 빙산.
 * bfs, dfs
 */
public class boj2573 {
    private static int n, m;
    private static int[][] map, cost;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        cost = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) map[i][j] = toInt(in[j]);
        }


        int component;
        int time = 0;
        // 1. component 갯수를 구해서 조건을 비교한다.
        while ((component = getComponent()) < 2) {
            time++; // 시간이 흐른다.
            visit = new boolean[n][m];

            // component 의 갯수가 0인 경우는 빙산이 다 녹아버려서 모두 얼음이 되었을 때를 의미한다.
            if (component == 0) {
                System.out.println(0);
                return;
            }

            // 2. 해당 정점의 빙산을 녹이는데 얼마나 걸리는지 cost 배열을 구한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0) findCost(i, j);
                }
            }

            // 3. 빙산을 녹인다. 어차피 빙산이 아닌 얼음으로 된 곳은 0이기 때문에
            // 0에서 0을 빼는 것은 동일하다.
            // 빙산을 녹였을 때, 음수가 나온다면 0을 저장해준다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] -= cost[i][j];
                    if (map[i][j] < 0) map[i][j] = 0;
                }
            }
        }
        System.out.println(time);
    }

    // 해당 정점의 빙산을 녹이기 위해 얼마나 걸리는지 확인한다.
    // 상,하,좌,우 네 방향에 인접한 정점을 검사하여 얼음이라면 녹일 수 있으므로 count 값을 증가시킨다.
    private static void findCost(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 해당 정점의 인접한 상,하,좌,우에 얼음이 있다면 증가시킨다.
            if (map[nx][ny] == 0) count++;
        }

        cost[x][y] = count;
    }

    // 컴포넌트의 갯수를 구한다.
    private static int getComponent() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 해당 정점이 얼음이 아닌 빙산이고 방문한 적이 없을 때만 탐색.
                if (map[i][j] != 0 && !visit[i][j]) {
                    count++;
                    //bfs(i, j);
                    dfs(i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(int x, int y) {
        if (visit[x][y]) return;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];


            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 방문한 적이 없고, 해당 정점이 얼음이 아닌 빙산인 경우에만 탐색.
            if (!visit[nx][ny] && map[nx][ny] != 0) {
                dfs(nx, ny);
            }
        }
    }

    // 일반적인 bfs 탐색.
    private static void bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node cur = q.remove();
            x = cur.x;
            y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 방문한 적이 없고, 해당 정점이 얼음이 아닌 빙산인 경우에만 탐색.
                if (!visit[nx][ny] && map[nx][ny] != 0) {
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
