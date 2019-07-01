package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 01/07/2019
 * dfs : 적록색약.
 */
public class BOJ10026 {
    private static StringBuilder sb = new StringBuilder();
    private static int n, components;
    private static char[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        a = new char[n][n];
        visited = new boolean[n][n];
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
        components = 0;

        // 방문 배열 초기화.
        visited = new boolean[n][n];

        // 적록색약인 사람.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 적록색약인 사람은 초록색을 빨간색으로 인식하므로 변경.
                if (a[i][j] == 'G') {
                    a[i][j] = 'R';
                }
            }
        }

        // 두번째. 적록 색약인 사람.
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

    private static void dfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        // 처음 정점을 방문했을 때의 문자와 같은지 비교하기 위해서
        // 문자를 하나 빼서 c 라는 변수로 저장한다.
        char c = a[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            if (!visited[nx][ny]) {
                // 다음 정점도 이전 정점의 문자와 같은지 비교한다.
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