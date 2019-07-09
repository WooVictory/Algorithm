package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 09/07/2019
 * 2차원 배열을 이용한 토마토 문제.
 */
public class BOJ7576 {
    private static int n, m;
    private static int[][] a, distance;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        m = parse(in[0]);
        n = parse(in[1]);

        a = new int[n][m];
        visited = new boolean[n][m];
        distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = parse(num[j]);
            }
        }

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(distance[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();

        // 익은 토마토의 좌표만 큐에 저장한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1) {
                    q.add(new Node(i, j));
                }
            }
        }

        // 익은 토마토에 대해서만 탐색을 진행한다.
        while (!q.isEmpty()) {
            Node node = q.remove();
            int nowX = node.x;
            int nowY = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                // 방문하지 않았거나 익지 않은 토마토일 경우만 큐에 추가.
                if (!visited[nx][ny] && a[nx][ny] == 0) {
                    q.add(new Node(nx, ny));
                    a[nx][ny] = 1; // 토마토가 익었음을 표시한다.
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[nowX][nowY] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0) { // 익지 않은 토마토가 존재할 경우.
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, distance[i][j]);
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

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}