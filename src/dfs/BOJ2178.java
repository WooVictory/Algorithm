package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 05/07/2019
 * 미로탐색.
 * bfs : 1단계씩 탐색하기 때문에 최단 거리를 구할 때 용이하다.
 * distance 배열을 사용해서 거리를 저장하면 된다.
 */
public class BOJ2178 {
    private static int n, m;
    private static int[][] a, distance;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        a = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        distance = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = s.charAt(j - 1) - '0';
            }
        }

        bfs(1, 1);
    }

    private static void bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        distance[x][y] = 1;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int nowX = node.x;
            int nowY = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || nx > n || ny < 0 || ny > m) continue;

                if (!visited[nx][ny] && a[nx][ny] == 1) {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[nowX][nowY] + 1;
                }
            }
        }
        System.out.println(distance[n][m]);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
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