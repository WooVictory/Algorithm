package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/28
 */
public class boj2178 {
    private static int n, m;
    private static int[][] a, distance;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n + 1][m + 1];
        distance = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        bfs();
        System.out.println(distance[n][m]);
        print();
    }

    private static void bfs() {
        LinkedList<Square> q = new LinkedList<>();
        visit[1][1] = true;
        distance[1][1] = 1;
        q.add(new Square(1, 1));

        while (!q.isEmpty()) {
            Square s = q.remove();
            int x = s.x, y = s.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

                if (a[nx][ny] == 1 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1; // 핵심 포인트. 
                    q.add(new Square(nx, ny));
                }

            }
        }
    }

    private static void print() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}

class Square {
    int x;
    int y;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
}