package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/13
 * 주사위 굴리기.
 */
public class SW14499 {
    private static int n, m, k, x, y;
    private static int[][] map;
    private static int[] command;
    private static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];

        x = toInt(in[2]);
        y = toInt(in[3]);
        k = toInt(in[4]);
        command = new int[k];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        in = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            command[i] = toInt(in[i]);
        }

        solve();
    }

    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};

    private static void solve() {
        for (int d : command) {
            // 다음 좌표를 구한다.
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 다음 좌표가 범위를 벗어나는지 확인한다.
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 주사위를 굴린다.
            move(d);

            if (map[nx][ny] != 0) {
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            } else {
                map[nx][ny] = dice[6];
            }

            System.out.println(dice[1]);

            // 다음 좌표가 다음 반복문에서는 현재좌표가 되어야 하기 때문에 값을 할당해준다.
            x = nx;
            y = ny;
        }
    }

    private static void move(int d) {
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

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
