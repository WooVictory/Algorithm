package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/06
 */
public class boj2468Re {
    private static int n;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];

        int max = 0;
        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = toInt(in[j - 1]);
                if (max < a[i][j]) max = a[i][j]; // 입력 받은 것 중에서 최대 높이를 찾는다.
            }
        }

        int count, result = 1;
        for (int i = 1; i <= max; i++) {
            visit = new boolean[n + 1][n + 1];
            count = 0;

            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (!visit[j][k] && a[j][k] > i) {
                        count++;
                        dfs(j, k,i);
                    }
                }
            }

            if (result < count) result = count;
        }

        System.out.println(result);
    }

    private static void dfs(int x, int y, int l) {
        if (visit[x][y]) return;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;

            if (!visit[nx][ny] && a[nx][ny]>l) {
                dfs(nx, ny,l);
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
