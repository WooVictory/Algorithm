package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/02/06
 * 안전 영역.
 * dfs.
 */
public class boj2468 {
    private static int limit = 100;
    private static int n;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = toInt(in[j - 1]);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (limit-- > 0) {

            int count = 0;
            visit = new boolean[n + 1][n + 1];

            // limit 이하인 곳은 물에 잠기도록 함.
            // visit 배열에 방문 여부를 표시함으로써 물에 잠기게 함.
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (a[i][j] <= limit) visit[i][j] = true;
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!visit[i][j]) {
                        count++;
                        dfs(i, j);
                    }
                }
            }

            result.add(count);
        }

        System.out.println(Collections.max(result));
    }

    private static void dfs(int x, int y) {
        if (visit[x][y]) return;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;

            if (!visit[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }


    private static void print() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
