package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/13
 * 주사위 굴리기.
 */
public class SW14499_REVIEW {
    private static int n, m, x, y;
    private static int[][] map;
    private static int[] dice = new int[7];
    private static int[] dirs;
    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};
    // 동서북남의 방향.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        x = toInt(in[2]);
        y = toInt(in[3]);

        int k = toInt(in[4]);
        dirs = new int[k];
        map = new int[n][m];

        // 1. 입력을 받는다.
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        in = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            dirs[i] = toInt(in[i]);
        }

        solve();
    }

    private static void solve() {

        // 2. (x,y)부터 명령을 하나씩 수행하면서 주사위를 명령 방향으로 굴린다.
        StringBuilder sb = new StringBuilder();
        for (int dir : dirs) {
            // 다음으로 이동할 좌표를 구한다.
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위를 벗어나면 건너뛴다.
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 주사위를 굴린다.
            move(dir);

            // 칸에 있는 값이 0인 경우, 주사위 바닥 -> 칸 복사
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[6];
            } else {
                // 0이 아닌 경우, 칸 -> 주사위 바닥 복사.
                // 칸은 0이 됨.
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            // 주사위 윗면을 출력.
            sb.append(dice[1]).append("\n");
            // 다음 좌표인 nx,ny는 다음 턴에서 현재 좌표로 사용되기 때문에 갱신한다.
            x = nx;
            y = ny;
        }

        System.out.println(sb.toString());
    }

    // 주사위를 1차원 배열로 관리하여 굴리는 방향에 따라 값을 바꿔준다.
    // deep copy 사용.
    private static void move(int dir) {
        int[] copy = dice.clone();
        switch (dir) {
            case 1: // 동쪽.
                dice[1] = copy[4];
                dice[3] = copy[1];
                dice[4] = copy[6];
                dice[6] = copy[3];
                break;
            case 2: // 서쪽.
                dice[1] = copy[3];
                dice[3] = copy[6];
                dice[4] = copy[1];
                dice[6] = copy[4];
                break;
            case 3: // 북쪽.
                dice[1] = copy[5];
                dice[2] = copy[1];
                dice[5] = copy[6];
                dice[6] = copy[2];
                break;
            case 4: // 남쪽.
                dice[1] = copy[2];
                dice[2] = copy[6];
                dice[5] = copy[1];
                dice[6] = copy[5];
                break;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
