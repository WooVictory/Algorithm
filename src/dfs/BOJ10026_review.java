package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 01/07/2019
 * dfs : 적록색약 review.
 */
public class BOJ10026_review {
    private static StringBuilder sb = new StringBuilder();
    private static int n, components;
    private static char[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        // 초기화.
        a = new char[n][n];
        visited = new boolean[n][n];

        // 입력.
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        // 적록색약이 아닌 사람.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    components++;
                    dfs(i, j);
                }
            }
        }

        sb.append(components).append(" ");

        // 초기화.
        components = 0;
        visited = new boolean[n][n];

        // 초록색을 빨간색으로 변경.
        // 적록색약인 사람은 초록색을 빨간색으로 인식하기 때문이다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 'G') {
                    a[i][j] = 'R';
                }
            }
        }

        // 적록색약인 사람.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    components++;
                    dfs(i, j);
                }
            }
        }

        sb.append(components);
        System.out.println(sb.toString());
    }

    // dfs 탐색.
    private static void dfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        // 방문했음을 체크한다.
        visited[x][y] = true;

        // 처음 방문한 정점의 색상을 저장한다.
        // 왜냐하면 인접한 정점을 방문할 때, 저장한 이 색상과 같아야 다음 탐색을 할 수 있기 때문이다.
        char c = a[x][y];

        // 네 방향을 탐색한다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 체크한다.
            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            // 방문하지 않았고, 다음 정점이 이전 정점과 같다면 재귀 호출을 통해 dfs 탐색을 한다.
            if (!visited[nx][ny]) {
                if (a[nx][ny] == c) {
                    dfs(nx, ny);
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}