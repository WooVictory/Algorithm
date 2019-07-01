package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 01/07/2019
 * dfs : 음식물 피하기.
 */
public class BOJ1743 {
    private static final String SPACE = " ";
    private static ArrayList<Integer> list = new ArrayList<>();
    private static int n, m, k, count;
    private static int[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);

        n = parse(in[0]);
        m = parse(in[1]);
        k = parse(in[2]);

        a = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        while (k-- > 0) {
            String[] num = br.readLine().split(SPACE);

            int x = parse(num[0]);
            int y = parse(num[1]);

            a[x][y] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i][j] == 1 && !visited[i][j]) {
                    count = 0;
                    dfs(i, j);

                    list.add(count);
                }
            }
        }

        System.out.println(Collections.max(list));
    }

    private static void dfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }
        a[x][y] = 0;
        visited[x][y] = true;
        count++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 문제였음...
            // 처음에 nx>=n || ny>=m 이렇게 설정했었음.
            if (nx < 0 || nx > n || ny < 0 || ny > m)
                continue;

            if (a[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}