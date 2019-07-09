package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 09/07/2019
 * 토마토.
 */
public class BOJ7569 {
    private static int m, n, h;
    private static int[][][] a, distance;
    private static boolean[][][] visited;
    // 여섯 방향을 검사하기 위한 배열.
    private static int[] dx = {1, -1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, 1, -1, 0, 0};
    private static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        m = parse(in[0]);
        n = parse(in[1]);
        h = parse(in[2]);

        a = new int[h][n][m];
        visited = new boolean[h][n][m];
        distance = new int[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                String[] num = br.readLine().split(" ");
                for (int k = 0; k < m; k++) {
                    a[i][j][k] = parse(num[k]);
                }
            }
        }

        // 입력 확인 용도.
        /*for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    System.out.print(a[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }*/

        bfs();
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();

        // 익은 토마토를 큐에 넣는다.
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (a[i][j][k] == 1) {
                        q.add(new Node(i, j, k));
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x;
            int y = node.y;
            int z = node.z;

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= n || nz < 0 || nz >= m)
                    continue;

                if (!visited[nx][ny][nz] && a[nx][ny][nz] == 0) {
                    q.add(new Node(nx, ny, nz));
                    a[nx][ny][nz] = 1;
                    visited[nx][ny][nz] = true;
                    distance[nx][ny][nz] = distance[x][y][z] + 1;
                }
            }

        }
        int max = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (a[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, distance[i][j][k]);
                }
            }
        }
        System.out.println(max);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Node {
        int x;
        int y;
        int z;

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
