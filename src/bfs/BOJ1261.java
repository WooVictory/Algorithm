package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 10/06/2019
 * bfs : 알고스팟
 */
public class BOJ1261 {
    private static int N, M;
    private static int[][] arr, distance;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        M = parse(input[0]); // 가로 : 열의 개수.
        N = parse(input[1]); // 세로 : 행의 개수.

        arr = new int[N][M];
        distance = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String number = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = number.charAt(j)-'0';
            }
        }

        bfs(0, 0);
        System.out.println(distance[N - 1][M - 1]);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
    * 가중치가 0 또는 1이 될 수 있기 때문에 이를 구분에서 큐를 구성해야 한다.
    * 이를 위해서 ArrayDeque 를 사용한다.
    * 가중치가 0일 때 : 방을 의미하므로 큐의 앞에 넣어준다. (현재 큐)
    * 가중치가 1일 때 : 벽을 의미하므로 큐의 뒤에 넣어준다. (다음 큐)
    *
    * 그래서 0인 곳을 먼저 방문하도록 한다.
    * 0인 곳을 모두 방문했다면 별도의 설정이 없어도 자동으로 1인 곳을 방문한다.
    * */
    private static void bfs(int x, int y) {
        ArrayDeque<Pair> deque = new ArrayDeque<>();
        deque.add(new Pair(x, y));
        visited[x][y] = true;
        distance[x][y] = 0;

        while (!deque.isEmpty()) {
            Pair pair = deque.remove();
            int nowX = pair.x;
            int nowY = pair.y;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                // 범위에 들어오고, 방문한 적이 없으면
                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                    // 0일 경우, 방을 의마하며 가중치가 없다.
                    // 큐의 앞에 넣어준다.
                    if (arr[nx][ny] == 0) {
                        deque.addFirst(new Pair(nx, ny));
                        distance[nx][ny] = distance[nowX][nowY];
                        // 1일 경우, 벽을 의미하며 부순다. 그리고 가중치가 있다.
                        // 큐의 뒤에 넣어준다.
                    } else if (arr[nx][ny] == 1) {
                        deque.addLast(new Pair(nx, ny));
                        distance[nx][ny] = distance[nowX][nowY] + 1;
                    }

                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
