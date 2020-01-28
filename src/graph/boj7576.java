package graph;

import com.sun.corba.se.impl.oa.toa.TOA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/28
 */
public class boj7576 {
    private static int n, m;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[m + 1][n + 1];
        visit = new boolean[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = toInt(input[j - 1]);
            }
        }

        bfs();
        getResult();
    }

    private static void bfs() {
        LinkedList<Tomato> q = getTomato();

        while (!q.isEmpty()) {
            Tomato t = q.remove();
            int x = t.x, y = t.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > m || ny > n) continue;

                if (a[nx][ny] == 0 && !visit[nx][ny]) {
                    a[nx][ny] = a[x][y] + 1;
                    visit[nx][ny] = true;
                    q.add(new Tomato(nx, ny));
                }
            }
        }
    }

    // a[][] 배열에 걸리는 일수를 저장하고, max 찾는다.
    // -1을 하는 이유는 이미 익은 토마토가 있는 경우 때문이다.
    private static void getResult() {
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                if (a[i][j] > max) max = a[i][j];
            }
        }
        System.out.println(max - 1);
    }

    // 익은 토마토를 찾아서 먼저 큐에 넣어주는 부분이 중요한 포인트.
    private static LinkedList<Tomato> getTomato() {
        LinkedList<Tomato> q = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == 1) q.add(new Tomato(i, j));
            }
        }
        return q;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}

class Tomato {
    int x;
    int y;

    Tomato(int x, int y) {
        this.x = x;
        this.y = y;
    }
}