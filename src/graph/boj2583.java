package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/02/04
 * 영역 구하기.
 * dfs
 */
public class boj2583 {
    private static int m, n, k, count;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        m = toInt(in[0]);
        n = toInt(in[1]);
        k = toInt(in[2]); // 직사각형의 갯수.

        a = new int[m][n];
        visit = new boolean[m][n];

        // 5 7 3
        // 0 2 4 4
        // 1 1 2 5
        // 4 0 6 2
        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");

            int leftBottomX = toInt(input[0]), leftBottomY = toInt(input[1]);
            int rightTopX = toInt(input[2]), rightTopY = toInt(input[3]);

            fill(leftBottomX, leftBottomY, rightTopX, rightTopY);

        }

        print();

        int total = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0 && !visit[i][j]) {
                    ++total;
                    count = 0;
                    dfs(i, j);

                    result.add(count);
                }
            }
        }

        Collections.sort(result);
        System.out.println(total);
        for (int n : result) System.out.print(n + " ");
    }

    private static void fill(int lbx, int lby, int rtx, int rty) {
        for (int i = lby; i < rty; i++) {
            for (int j = lbx; j < rtx; j++) {
                a[i][j] = 1;
                visit[i][j] = true;
            }
        }
    }

    private static void dfs(int x, int y) {
        count++;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;

            if (!visit[nx][ny] && a[nx][ny] == 0) {
                visit[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    private static void print() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
