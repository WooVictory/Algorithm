package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/13
 * 연구소.
 * 삼성 기출.
 * dfs.
 */
public class sw14502Re {
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

        // 시간 복잡도를 줄이기 위해 하나의 for 문을 사용한다.
        // 기존처럼 2중 for 문 사용시 정확한 결과가 나오지 않는다.
        for (int i = 0; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            // 빈칸인 경우, 벽을 놓는다. dfs()를 호출한다.
            // 빈칸인 모든 경우에 대하여 벽을 3개까지 놓아보는 것이다.
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

        // 3개의 벽을 놓았을 경우.
        if (count == 3) {
            copyMap = new int[n][m];
            copy();

            // 바이러스가 존재하는 곳을 찾아 바이러스를 퍼트린다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copyMap[i][j] == 2) spreadVirus(i, j);
                }
            }

            getResult();
        } else {
            // 3개의 벽을 세우지 못한 경우, 다음 정점부터 다시 탐색한다.
            for (int i = v + 1; i < n * m; i++) {
                int nx = i / m;
                int ny = i % m;

                // 빈칸인 경우, 벽을 세우고 dfs()를 호출한다.
                // 재귀호출을 이용해 벽을 세운다.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    dfs(i, count + 1);
                }
            }
        }
        // 백트래킹.
        // dfs() 탐색을 하고 돌아와 백트래킹을 함으로써
        // 방문했던 지점을 다시 방문할 수 있도록 초기화 한다.
        map[x][y] = 0;
        --count;
    }

    // 원본 배열을 copyMap 으로 복사한다.
    // 복사하는 이유는 원본 배열이 바뀌기 때문이다.
    // 벽을 세운다거나 바이러스를 퍼트린다거나!
    private static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) copyMap[i][j] = map[i][j];
        }
    }

    // 2. 바이러스를 퍼트린다. dfs
    private static void spreadVirus(int x, int y) {
        // 바이러스는 인접한 상하좌우 방향으로 전파될 수 있기 때문에 4방향을 탐색한다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 조건을 만족하며 벽이 아닌 빈칸인 경우, 바이러스를 퍼트린다.
            // 기존의 바이러스와 구분짓기 위해 3으로 퍼트린 바이러스를 표시한다.
            // 바이러스를 퍼트리는 부분도 재귀 호출을 사용한다.
            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 3;
                    spreadVirus(nx, ny);
                }
            }
        }
    }

    // 3. 안전 영역의 크기를 구한다. 최대값을 구한다.
    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) count++;
            }
        }

        max = Math.max(max, count);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
