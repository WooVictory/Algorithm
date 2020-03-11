package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/11
 * 테트로미노.
 * 다시 풀어보기!
 */
public class sw14500Re {
    private static int n, m, max;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    // 동서남북 방향.

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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                solveExtraBlock(i, j);
                visit[i][j] = false;
            }
        }
        System.out.println(max);
    }

    // 'ㅜ'를 제외한 나머지 블록은 dfs+백트래킹을 사용해 구할 수 있다.
    private static void dfs(int x, int y, int count, int sum) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            if (!visit[nx][ny]) {
                visit[nx][ny] = true;
                dfs(nx, ny, count + 1, sum + map[nx][ny]);
                visit[nx][ny] = false;
                // 백트래킹을 통해 이 정점이 아닌 이전으로 돌아가 다른 정점에 방문할 수 있도록 한다.
            }
        }
    }

    // 'ㅜ' 블록을 구하는 함수이다.
    private static void solveExtraBlock(int x, int y) {
        // 'ㅜ'를 회전시켰을 때, 4가지가 나오기 때문에 for 문은 4번 돌린다.
        for (int i = 0; i < 4; i++) {
            // x,y 좌표가 중심이 된다.
            int total = map[x][y];
            boolean flag = true;

            // x,y 좌표를 중심으로 3번만 이동하면 'ㅜ', 'ㅏ', 'ㅓ', 'ㅗ'를 만들 수 있다.
            for (int j = 0; j < 3; j++) {
                int nx = x + dx[(i + j) % 4];
                int ny = y + dy[(i + j) % 4];

                // 범위 안에 존재하는 경우, total 에 해당 칸의 숫자를 더하여 준다.
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    total += map[nx][ny];
                } else {
                    // 범위를 벗어나는 경우, flag 를 false 로 하여 반복문을 벗어난다.
                    // 다른 회전 모양을 탐색한다.
                    flag = false;
                    break;
                }
            }

            // 범위 안에 잘 들어가서 회전된 모양을 찾았으면, max 값과 total 을 비교하여 max 값을 갱신한다.
            if (flag) max = Math.max(max, total);
        }
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
