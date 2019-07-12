package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 12/07/2019
 * 0 : 북
 * 1 : 동
 * 2 : 남
 * 3 : 서
 */
public class sw14503 {
    private static final String SPACE = " ";
    private static int n, m, x, y, direction, turnCount;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    // 북,동,남,서의 왼쪽 방향을 기준으로 배열을 생성.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);

        // 초기화.
        n = parse(in[0]);
        m = parse(in[1]);

        map = new int[n][m];

        in = br.readLine().split(SPACE);
        x = parse(in[0]);
        y = parse(in[1]);
        direction = parse(in[2]);

        // 입력.
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                map[i][j] = parse(in[j]);
            }
        }
        solve();
    }

    /**
     * 0 : 청소하지 않은 공간.
     * 1 : 벽.
     * 2 : 청소를 한 공간.
     */
    private static void solve() {
        while (true) {
            // 4 방향이 모두 청소되어 있거나, 벽일 경우.
            if (turnCount == 4) {
                // 후진할 좌표를 구한다.
                int backX = x - dx[direction];
                int backY = y - dy[direction];

                // 벽이라면 종료.
                if (map[backX][backY] == 1) {
                    System.out.println(getCleanArea());
                    return;
                } else {
                    // 벽이 아니면 후진.
                    System.out.println(map[backX][backY]);
                    setRobot(backX, backY, direction, 0);
                }
            }

            // 1. 현재 위치를 청소한다.
            if (map[x][y] == 0) {
                map[x][y] = 2;
            }

            // 2. 현재 방향을 기준으로 왼쪽으로 회전할 방향과 좌표를 구한다.
            int ld = (direction + 3) % 4;
            int nx = x + dx[ld];
            int ny = y + dy[ld];

            // 3. 청소공간 있음 -> 한칸 전진하고 1번으로
            // 4. 청소공간 없음 -> 그 방향으로 회전하고 2번으로
            if (map[nx][ny] == 0) {
                setRobot(nx, ny, ld, 0);
            } else {
                setRobot(x, y, ld, turnCount + 1);
            }

        }
    }

    private static int getCleanArea() {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    result++;
                }
            }
        }
        return result;
    }

    private static void setRobot(int nx, int ny, int nd, int nextCount) {
        x = nx;
        y = ny;
        direction = nd;
        turnCount = nextCount;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
