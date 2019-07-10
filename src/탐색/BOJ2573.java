package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 10/07/2019
 * 빙산.
 */
public class BOJ2573 {
    private static int n, m, answer;
    private static int[][] a, minus;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        a = new int[n][m];
        minus = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = parse(num[j]);
            }
        }

        int temp;
        while ((temp = componentNumber()) < 2) {
            if (temp == 0) {
                answer = 0;
                break;
            }

            answer++;
            visited = new boolean[n][m];

            // 빙산이 얼마나 녹는지를 구한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] != 0) {
                        convert(i, j);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] -= minus[i][j];
                    if (a[i][j] < 0) {
                        a[i][j] = 0;
                        visited[i][j] = true;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    // 연결 요소의 개수를 구한다.
    private static int componentNumber() {
        int components = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != 0 && !visited[i][j]) {
                    components++;
                    dfs(i, j);
                }
            }
        }
        return components;
    }

    // 빙산이 녹는 과정.
    // 빙산 주변에 0인 곳을 검사해서 count 를 minus 배열에 저장한다.
    private static void convert(int x, int y) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > n || ny < 0 || ny > m)
                continue;

            if (a[nx][ny] == 0 && a[x][y] > 0) {
                count++;
            }
        }
        minus[x][y] = count;
    }

    private static void dfs(int x, int y) {
        if (visited[x][y])
            return;

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > n || ny < 0 || ny > m)
                continue;

            if (!visited[nx][ny] && a[nx][ny] != 0) {
                dfs(nx, ny);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}