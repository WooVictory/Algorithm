package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/01/26
 * dfs + visit boolean 배열.
 */
public class boj2667_3 {
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int n, apartment = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= n; j++) {
                a[i][j] = input.charAt(j - 1) - 48;
            }
        }

        find();
    }

    private static void find() {
        int total = 0;
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == 1 && !visit[i][j]) {
                    total++;
                    apartment = 0;
                    dfs(i, j);

                    result.add(apartment);
                }
            }
        }

        System.out.println(total);
        Collections.sort(result);
        for (int value : result) System.out.println(value);
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;
        apartment++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || nx > n || ny < 1 || ny > n) continue;

            if (a[nx][ny] == 1 && !visit[nx][ny]) dfs(nx, ny);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

}
