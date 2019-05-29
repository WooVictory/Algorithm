package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 29/05/2019
 * 완탐 : 알고스팟
 */
public class BOJ1261 {
    private static final int ASCII = 48;
    private static int N, M;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[][] distance;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = parse(input[0]); // 가로(열의 수)
        N = parse(input[1]); // 세로(행의 수)

        arr = new int[N][M]; // N행 M열
        visited = new boolean[N][M];
        distance = new int[N][M];

        for (int i = 0; i < N; i++) {
            String num = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = num.charAt(j) - ASCII;
            }
        }

        distance[0][0] = 0;
        bfs(0, 0);
        System.out.println(distance[N - 1][M - 1]);
    }

    private static void bfs(int x, int y) {
        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(x, y));
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            Point point = deque.remove();
            int nowX = point.x;
            int nowY = point.y;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
                    // 방문하지 않았다면.
                    if (!visited[nextX][nextY]) {
                        // 벽을 부수지 않는 경우. 즉, 0인 경우.
                        // 덱의 앞에 넣는다.
                        if (arr[nextX][nextY] == 0) {
                            distance[nextX][nextY] = distance[nowX][nowY];
                            deque.addFirst(new Point(nextX, nextY));
                        } else {
                            distance[nextX][nextY] = distance[nowX][nowY] + 1;
                            deque.addLast(new Point(nextX, nextY));
                        }

                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
