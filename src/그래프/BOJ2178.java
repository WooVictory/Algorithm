package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 08/04/2019
 * 그래프 : 미로 탐색
 * 모든 가중치가 1일 때, 최단 거리 알고리즘은 BFS를 이용해 구할 수 있다.
 * 이유는 BFS가 단계별로 진행되기 때문이다. BFS는 1단계씩 진행한다.
 */
public class BOJ2178 {
    private static int[][] arr;
    private static int[][] dist;
    private static boolean[][] visit;
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        arr = new int[n + 1][m + 1]; // 입력 값을 담는 배열.
        visit = new boolean[n + 1][m + 1]; // 방문했는지 확인하는 배열.
        dist = new int[n + 1][m + 1]; // 거리를 담는 배열.

        // 2차원 배열 입력.
        for (int i = 1; i <= n; i++) {
            String num = br.readLine();
            for (int j = 1; j <= m; j++) {
                int value = num.charAt(j - 1) - '0';
                arr[i][j] = value;
            }
        }

        bfs(1, 1, n, m);
    }


    private static void bfs(int x, int y, int n, int m) {
        LinkedList<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(x, y));
        visit[x][y] = true; // 방문했음을 표시한다.
        dist[x][y] = 1; // 첫 정점은 (1,1)의 거리는 1이기 때문에 1을 저장한다.

        while (!q.isEmpty()) {
            Coordinate coordinate = q.poll();
            x = coordinate.x;
            y = coordinate.y;

            // 4방향을 검사한다.
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx <= n && 0 <= ny && ny <= m) {
                    if (arr[nx][ny] == 1 && !visit[nx][ny]) {
                        q.add(new Coordinate(nx, ny));
                        dist[nx][ny] = dist[x][y] + 1;
                        visit[nx][ny] = true;
                    }
                }
            }
        }

        System.out.println(dist[n][m]);
    }
}

// x, y 좌표
class Coordinate {
    int x;
    int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
