package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/10
 */
public class boj2178Re {
    private static int[][] a;
    private static boolean[][] visit;
    private static int n, m, result;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String num = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = num.charAt(j - 1) - '0';
            }
        }

        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(1, 1, 1));
        visit[1][1] = true;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x, y = node.y, cost = node.cost;

            if (x == n && y == m) {
                result = cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (visit[nx][ny]) continue;

                if (a[nx][ny] == 1) {
                    q.add(new Node(nx, ny, cost + 1));
                    visit[nx][ny] = true;
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
