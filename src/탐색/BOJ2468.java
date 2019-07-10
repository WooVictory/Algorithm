package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 10/07/2019
 * 안전 영역.
 */
public class BOJ2468 {
    private static int n, components;
    private static int[][] a;
    private static boolean[][] visited;
    private static ArrayList<Integer> list = new ArrayList<>();
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        a = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = parse(in[j - 1]);
            }
        }

        int t = 100;

        while (t-- > 0) {
            components = 0;
            visited = new boolean[n + 1][n + 1];

            // t 이하인 지점을 물을 다 채운다.
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (a[i][j] <= t) {
                        visited[i][j] = true;
                    }
                }
            }

            /*for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {

                    System.out.print(visited[i][j] + " ");

                }
                System.out.println();
            }
            System.out.println();*/

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!visited[i][j]) {
                        //System.out.println("i: " + i + ", j: " + j);
                        components++;
                        dfs(i, j);

                    }
                }
            }
            list.add(components);
        }
        System.out.println(Collections.max(list));
    }

    private static void dfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || nx >= n + 1 || ny < 1 || ny >= n + 1)
                continue;

            if (!visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
