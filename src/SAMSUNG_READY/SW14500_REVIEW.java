package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/13
 * 테트로미노.
 */
public class SW14500_REVIEW {
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visit;
    private static int max = 0;

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
                draw(i, j, 0, 0);
                drawOtherBlock(i, j);
            }
        }

        System.out.println(max);
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    // 'ㅗ'를 제외한 도형을 한번에 그릴 수 있다.
    // dfs + 백트래킹을 활용.
    private static void draw(int x, int y, int count, int sum) {
        // 4개의 도형을 구성하면 최대값을 찾아 갱신한다.
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
                draw(nx, ny, count + 1, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }

    // 'ㅗ' 모양을 그리기 위해서 + 모양을 생각해본다.
    // 날개가 4개 있고, 여기서 방향대로 돌아가면서 1개씩 뺀다면 'ㅗ'를 회전한 모양까지 구할 수 있다.
    private static void drawOtherBlock(int x, int y) {
        int wing = 4; // 4개의 날개가 존재.
        int min = Integer.MAX_VALUE; // 최소값을 찾는다.
        int sum = map[x][y]; // sum 은 시작 지점의 값을 누적한 상태로 시작.

        for (int i = 0; i < 4; i++) {
            // 날개를 놓을 수 있는 다음 좌표를 구한다.
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 만약, 날개의 갯수가 2개 이하라면 'ㅗ' 모양을 채울 수 없으므로 종료한다.
            if (wing <= 2) return;

            // 만약, 날개의 다음 좌표가 범위를 벗어난다면 wing 개수를 감소시킨다.
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                wing--;
                continue;
            }

            // 최소값을 찾는다.
            min = Math.min(min, map[nx][ny]);
            // 날개의 값을 누적한다.
            sum += map[nx][ny];
        }

        // 끝이 아닌 이상 sum 에는 날개가 4인 + 모양을 한 도형의 숫자 값의 합이 들어있다.
        // 'ㅗ' 모양을 놓았을 때 합의 최대값을 구해야 하므로 가장 작은 값이었던 min 을 sum 에서 빼준다.리
        // 그러면 'ㅗ' 모양의 합을 구할 수 있다.
        if (wing == 4) sum -= min;

        // max 값을 갱신한다.
        max = Math.max(max, sum);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
