package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 10/07/2019
 * 빙산 문제 리뷰.
 */
public class BOJ2573_REVIEW {
    private static final String SPACE = " ";
    private static int n, m, answer;
    private static int[][] a, temp;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);
        n = parse(in[0]);
        m = parse(in[1]);

        // 초기화.
        a = new int[n][m];
        temp = new int[n][m];
        visited = new boolean[n][m];

        // 입력.
        for (int i = 0; i < n; i++) {
            String[] num = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                a[i][j] = parse(num[j]);
            }
        }

        // 구성 요소의 개수가 몇개인지 확인을 해준다.
        // 구성 요소의 개수가 탈출 조건이 된다.
        int limit;
        while ((limit = componentNumber()) < 2) {
            if (limit == 0) {
                answer = 0;
                break;
            }

            answer++; // 몇년 걸리는지 체크한다.

            // 여기서 초기화해주는 이유는 컴포넌트의 개수를 구하기 위함이다.
            // 아래에서 빙산이 녹음에 따라서 배열이 변경되는데, 변경된 배열에 대해 컴포넌트의
            // 개수를 구하기 위해서 visited 배열을 초기화 해줘야 한다.
            // 하지 않는다면 답을 구하지 못한다..
            visited = new boolean[n][m];

            // 빙산이 얼마나 녹을지를 계산한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] != 0) {
                        findYear(i, j);
                    }
                }
            }

            // 빙산을 녹인다.
            // 그리고 0보다 작다면 0을 넣어준다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] -= temp[i][j];
                    if (a[i][j] < 0) {
                        a[i][j] = 0;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    // 네 방향을 검사해서 빙산 주변에 바닷물을 확인한다.
    private static void findYear(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > n || ny < 0 || ny > m)
                continue;

            // 해당 빙산을 기준으로 동,서,남,북 방향을 검사해서
            // 0이면 바닷물이므로 빙산이 녹는데 영향을 준다.
            // 0인 곳의 개수를 확인한다.
            if (a[nx][ny] == 0 && a[x][y] != 0) {
                count++;
            }
        }
        temp[x][y] = count;
    }

    // 컴포넌트의 개수를 찾는 함수.
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

    // dfs 탐색.
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
