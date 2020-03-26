package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/25
 * 미로 탐색. -> bfs() 깔끔하게 풀 수 있다.
 */
public class boj2178 {
    private static int n, m;
    private static int[][] map;
    private static int[][] copy; // 실제로 (1,1) -> (N,M)까지 가는데 몇번만에 도착하는지 확인하기 위한 배열.
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        copy = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) map[i][j] = s.charAt(j) - '0';
        }

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(copy[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1));
        visit[0][0] = true;
        copy[0][0] = 1;

        while (!q.isEmpty()) {
            Node cur = q.remove();
            int x = cur.x;
            int y = cur.y;
            int count = cur.count;

            if (x == n - 1 && y == m - 1) {
                System.out.println(count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (visit[nx][ny]) continue;

                if (map[nx][ny] == 1) {
                    copy[nx][ny] = copy[x][y] + 1;
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny, count + 1));
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
        int count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
