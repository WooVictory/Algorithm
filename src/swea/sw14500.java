package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/11
 */
public class sw14500 {
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visit;
    private static int max = 0;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        // 탐색할 때, (0,0) 부터 탐색을 하여 다음으로 갈 수 있는 정점을 탐색.
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 0, 0);
                calculateBlock(i, j);
            }
        }*/

        // 탐색할 때, (i,j) 정점은 이미 탐색을 하고 다음으로 갈 수 있는 정점을 탐색.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                calculateBlock(i, j);
                visit[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void dfs(int x, int y, int count, int sum) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            if (!visit[nx][ny]) {
                visit[nx][ny] = true;
                dfs(nx, ny, count + 1, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }

    }

    private static void calculateBlock(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int total = map[x][y];
            boolean flag = true;

            for (int j = 0; j < 3; j++) {
                int nx = x + dx[(i + j) % 4];
                int ny = y + dy[(i + j) % 4];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    total += map[nx][ny];
                } else {
                    flag = false;
                    break;
                }
            }

            if (flag) max = Math.max(max, total);
        }
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
