package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/10
 * 주사위 굴리기.
 * 시뮬레이션.
 * 다시 풀어보기.
 */
public class sw14499 {
    private static int n, m, k, x, y;
    private static int[][] map;
    private static int[] dir;
    private static int[] dice = new int[7];
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    // 행렬 좌표를 기준으로 dx, dy 를 구성한다.
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        x = toInt(in[2]);
        y = toInt(in[3]);
        k = toInt(in[4]);

        map = new int[n][m];
        dir = new int[k];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }
        in = br.readLine().split(" ");
        for (int i = 0; i < k; i++) dir[i] = toInt(in[i]);
        solve();
    }

    private static void solve() {
        for (int i = 0; i < k; i++) {
            int d = dir[i];
            int nx = dx[d - 1] + x;
            int ny = dy[d - 1] + y;


            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            changeDice(d);

            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[6];
            } else {
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            x = nx;
            y = ny;

            sb.append(dice[1]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void changeDice(int d) {
        int[] copy = dice.clone();
        switch (d) {
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
