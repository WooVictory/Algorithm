package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/13
 * 테트로미노.
 */
public class SW14500 {
    private static int n, m;
    private static int[][] map;
    private static int max = 0;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

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

        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }*/

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                draw(i, j, 0, 0);
                drawOtherBlock(i, j);
            }
        }

        System.out.println(max);
    }

    private static void drawOtherBlock(int row, int col) {
        int sum = map[row][col];
        int min = Integer.MAX_VALUE;
        int wing = 4;

        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (wing <= 2) return;

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                wing--;
                continue;
            }

            min = Math.min(min, map[nx][ny]);
            sum += map[nx][ny];
        }

        if (wing == 4) sum -= min;

        max = Math.max(max, sum);
    }

    private static void draw(int row, int col, int count, int sum) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (!visit[nx][ny]) {
                visit[nx][ny] = true;
                draw(nx, ny, count + 1, sum + map[nx][ny]);
                visit[nx][ny] = false;
            }
        }
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

}
