package samsung;

import java.io.*;

/**
 * created by victory_woo on 23/07/2019
 * SW 역량 기출 테트로미노.
 */
public class sw14500 {
    private static final String SPACE = " ";
    private static int n, m, max = 0;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북동남서.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] in = br.readLine().split(SPACE);

        n = parse(in[0]);
        m = parse(in[1]);

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                map[i][j] = parse(in[j]);
            }
        }

        // 각 점에 대해서 DFS 탐색을 진행한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 0, 0); // 'ㅜ'를 제외한 경우에 대해서 탐색한다.
                anotherBlock(i, j); // 'ㅜ'인 경우는 따로 구한다.
            }
        }
        bw.write(String.valueOf(max));
        bw.flush();
    }

    // ㅏ,ㅜ,ㅓ,ㅗ 네 가지를 탐색하기 위해 첫 번째 for 문은 4번 돈다.
    // 그 다음 현재 점을 기준으로 나머지 3칸을 계산하기 때문에 다음 for 문은 3번 돈다.
    private static void anotherBlock(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int total = map[x][y];
            boolean flag = true;

            // 이 계산법이 특이함..
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

    private static void dfs(int x, int y, int count, int sum) {
        // 테트로미노가 도형 4개를 가지고 칸에 놓여있는 값의 합 중 최대값을 구하기 때문에
        // count == 4이면 return 하고 돌아간다.
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        // 'ㅜ'를 제외한 4 도형에 대해서 탐색을 한다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위에 맞는지 검사한다.
            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, count + 1, sum + map[nx][ny]);
                visited[nx][ny] = false; // 돌아와서 다른 정점 가야 하기 때문에 백트래킹.
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}