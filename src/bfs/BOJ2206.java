package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 11/06/2019
 * bfs : 벽 부수고 이동하기
 */
public class BOJ2206 {
    private static int n, m;
    private static int[][] arr, distance;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        arr = new int[n + 1][m + 1];
        distance = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
        }

        /*for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }*/

        bfs(1, 1);

        System.out.println(distance[n][m]);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static void bfs(int x, int y) {
        LinkedList<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        distance[x][y] = 0;

        while (!q.isEmpty()) {
            Point point = q.remove();
            int nowX = point.x;
            int nowY = point.y;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                // 방문한 적이 없음.
                if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {

                    //System.out.println("nx : " + nx + ", ny : " + ny);
                    // 이동할 수 있는 경우.
                    if (arr[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        distance[nx][ny] = distance[nowX][nowY];
                    } else if (arr[nx][ny] == 1) {
                        q.add(new Point(nx, ny));
                        distance[nx][ny] = distance[nowX][nowY] + 1;
                    }


                    visited[nx][ny] = true;


                    /*// 방인 경우
                    if (arr[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        System.out.println("room");
                        distance[nx][ny] = distance[nowX][nowY] + 1;
                    }

                    // 벽인 경우
                    if (arr[nx][ny] == 1) {
                        q.add(new Point(nx, ny));
                        System.out.println("wall");
                        count++;
                        arr[nx][ny] = 0; // 벽을 부수고 방으로 만든다.
                        distance[nx][ny] = distance[nowX][nowY] + 1;
                    }*/
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
