package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/13
 * 연구소.
 * 삼성 기출.
 */
public class sw14502 {
    private static int n, m, max;
    private static int[][] map, copyMap;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        // 시간 복잡도를 줄이기 위해서 for 문 2개를 중첩하지 않고, 하나의 for 문으로 진행했다.
        for (int i = 0; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            // 빈칸이라면 벽을 놓는다.
            if (map[x][y] == 0) {
                map[x][y] = 1;
                dfs(i, 1);
            }
        }

        System.out.println(max);
    }

    // 1. 벽을 3개까지 놓는다.
    private static void dfs(int v, int count) {
        int x = v / m;
        int y = v % m;

        // 벽을 3개 놓았을 경우, 바이러스를 퍼트리고 안전 영역의 크기를 구한다.
        if (count == 3) {
            // 원본 배열을 복사한다.
            // 아래쪽에서 벽을 놓음으로써 원본 배열이 변경되기 때문이다.
            copyMap = new int[n][m];
            copy();

            // 바이러스인 곳을 찾아서 바이러스를 퍼트린다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copyMap[i][j] == 2) {
                        spreadVirus(i, j);
                    }
                }
            }

            getResult();
        } else {
            // 벽을 3개 놓지 못한 경우에는 v+1 부터 시작하여 다시 벽을 놓을 자리를 찾는다.
            for (int i = v + 1; i < n * m; i++) {
                int nx = i / m;
                int ny = i % m;

                // 빈칸인 경우에 벽을 놓는다.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    dfs(i, count + 1);
                }
            }
        }

        // 백트래킹.
        map[x][y] = 0;
        --count;
    }

    private static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
    }

    // 2. 바이러스를 퍼트린다.
    private static void spreadVirus(int x, int y) {
        // 바이러스를 기준으로 상하좌우 인접한 곳의 빈칸으로 바이러스를 퍼트린다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 상하좌우에 존재하는 지점이 범위에 존재하고 빈칸이라면 새로운 바이러스를 퍼트린다.
            // 기존의 바이러스와 구분한다.
            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 3; // 새로운 바이러스.
                    spreadVirus(nx, ny);
                }
            }
        }
    }

    // 3. 안전 영역의 개수를 구한다.
    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) count++;
            }
        }
        max = Math.max(max, count);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}