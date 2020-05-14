package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/14
 * 연구소.
 */
public class SW14502 {
    private static int n, m;
    private static int[][] map, copy;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        for (int i = 0; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            // 빈 공간인 경우, 벽을 놓는다.
            // 그리고 3개까지 놓아본다.
            if (map[x][y] == 0) {
                map[x][y] = 1;
                setWall(i, 1);
            }
        }

        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit = new boolean[n][m];
                copy = copyArray(map);
                find(i, j, 0);
            }
        }*/

        System.out.println(max);
    }

    private static void setWall(int v, int count) {
        int x = v / m;
        int y = v % m;

        if (count == 3) {
            copy = copyArray(map);
            spreadVirus();
            check();
        } else {
            for (int i = v + 1; i < n * m; i++) {
                int nx = i / m;
                int ny = i % m;

                // 빈칸일 경우, 벽을 놓는다.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    setWall(i, count + 1);
                }
            }
        }

        // 백트래킹을 통해 다시 원래대로 돌려준다.
        map[x][y] = 0;
        --count;
    }

    // 바이러스를 퍼트린다.
    private static void spreadVirus() {
        LinkedList<Node> q = new LinkedList<>();

        // 바이러스를 찾아서 큐에 넣어준다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 2) q.add(new Node(i, j));
            }
        }


        // 큐에서 꺼내면서 바이러스를 퍼트린다.
        while (!q.isEmpty()) {
            Node cur = q.remove();
            int x = cur.x;
            int y = cur.y;


            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (copy[nx][ny] == 0) {
                    copy[nx][ny] = 3;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    private static void check() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 0) count++;
            }
        }

        max = Math.max(count, max);
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

   /* private static void find(int x, int y, int count) {
        if (count == 3) {
            copy = copyArray(map);

            spread();
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (copy[nx][ny] == 2 || copy[nx][ny] == 1) continue;

            if (!visit[nx][ny]) {
                visit[nx][ny] = true;
                copy[nx][ny] = 1;

                find(nx, ny, count + 1);
                visit[nx][ny] = false;
                copy[nx][ny] = 0;
            }
        }
    }*/

    private static int[][] copyArray(int[][] og) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            if (m >= 0) System.arraycopy(og[i], 0, copy[i], 0, m);
        }
        return copy;
    }

    /*private static void spread() {
        LinkedList<Node> q = new LinkedList<>();
        boolean[][] check = new boolean[n][m];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 2) {
                    check[i][j] = true;
                    q.add(new Node(i, j));
                }
            }
        }
        print();

        while (!q.isEmpty()) {
            Node cur = q.remove();
            int x = cur.x, y = cur.y;

            if (x == n - 1 && y == n - 1) {
                max = Math.max(max, count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (copy[nx][ny] == 1) continue;

                if (!check[nx][ny] && copy[nx][ny] == 0) {
                    check[nx][ny] = true;
                    copy[nx][ny] = 2;
                    q.add(new Node(nx, ny));
                }
            }
        }

    }*/

    private static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(copy[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
