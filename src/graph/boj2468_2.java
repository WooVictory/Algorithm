package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/12
 */
public class boj2468_2 {
    private static int n, max;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        a = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = toInt(in[j - 1]);
                if (max < a[i][j]) max = a[i][j];
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (max-- > 0) {
            int count = 0;
            visit = new boolean[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (a[i][j] <= max) visit[i][j] = true;
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!visit[i][j]) {
                        count++;
                        //dfs(i, j);
                        bfs(i, j);
                    }
                }
            }
            result.add(count);
        }
        System.out.println(Collections.max(result));
    }

    private static void bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node now = q.remove();
            x = now.x;
            y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > n) continue;

                if (!visit[nx][ny]) {
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    private static void dfs(int x, int y) {
        if (visit[x][y]) return;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;

            if (!visit[nx][ny]) dfs(nx, ny);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
