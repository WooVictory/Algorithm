package review;

import java.io.*;

/**
 * created by victory_woo on 24/07/2019
 * SW 역량 기출 테트로미노.
 */
public class sw14500_review {
    private static final String SPACE = " ";
    private static final int LIMIT = 4;
    private static int n, m, max;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    // 동남서북

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(SPACE);
        n = parse(in[0]);
        m = parse(in[1]);

        // 초기화.
        map = new int[n][m];
        visited = new boolean[n][m];

        // 입력.
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                map[i][j] = parse(in[j]);
            }
        }

        // 모든 정점에 대하여 놓을 수 있는 도형을 구해본다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 0, 0); // 'ㅜ'를 제외한 4 도형 탐색.
                anotherBlockFind(i, j); // 'ㅜ' 도형에 대한 탐색.
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
    }

    private static void dfs(int x, int y, int count, int sum) {
        // 테트로미노 도형이 4개이므로 4개까지 놓을 수 있다.
        if (count == LIMIT) {
            max = Math.max(max, sum);
            return;
        }

        // 동남서북 방향으로 가면서 탐색을 하면서 도형을 그려본다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위에 맞는지 검사한다.
            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, count + 1, sum + map[nx][ny]);
                    // 다시 돌아와서 다른 방향으로 탐색할 수 있기 때문에 false 로 돌려놔야 한다.
                    visited[nx][ny] = false;
                }
            }
        }
    }

    // 'ㅜ' 도형에 대해서 4 방향 탐색을 한다.
    private static void anotherBlockFind(int x, int y) {
        // ㅓ,ㅏ,ㅗ,ㅜ 로 도형의 개수가 4개가 나올 수 있으므로
        // 4번 돈다.
        for (int i = 0; i < 4; i++) {
            int result = map[x][y]; // 처음 정점을 저장한다.
            boolean flag = true;

            // 현재 정점을 제외하고 나머지 세 화살표 방향에 대해서 검사하므로
            // 3번 돈다.
            for (int j = 0; j < 3; j++) {
                int nx = x + dx[(i + j) % 4];
                int ny = y + dy[(i + j) % 4];

                // 범위를 검사한다.
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    result += map[nx][ny];
                } else {
                    // 범위에 맞지 않으면 검사하지 않는다.
                    flag = false;
                    break;
                }
            }

            // max 값을 갱신한다.
            if (flag)
                max = Math.max(max, result);
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
