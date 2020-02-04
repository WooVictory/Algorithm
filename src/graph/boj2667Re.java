package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/02/04
 * 단지 번호 붙이기.
 * dfs
 */
public class boj2667Re {
    private static int n, count, total;
    private static int[][] a;
    private static int[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];
        visit = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= n; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == 1 && visit[i][j] == 0) {
                    ++total;
                    count = 0;
                    dfs(i, j);

                    result.add(count);
                }
            }
        }

        Collections.sort(result);
        System.out.println(total);
        for (int a : result) System.out.println(a);

        print(n);
    }

    private static void dfs(int x, int y) {
        count++;
        visit[x][y] = total;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;

            if (visit[nx][ny] == 0 && a[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }

    }

    private static void print(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(visit[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
