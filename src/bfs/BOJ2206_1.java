package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 12/06/2019
 * bfs : 벽 부수고 이동하기.
 * 벽을 부수는 경우와 부수지 않는 경우를 나눠서 탐색하면 된다.
 */
public class BOJ2206_1 {
    private static int n, m, result = 0;
    private static int[][] map;
    private static boolean[][][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String number = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = number.charAt(j) - '0';
            }
        }

        bfs();

        if (result == 0)
            System.out.println(-1);
        else
            System.out.println(result);

    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));

        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x;
            int y = node.y;
            int count = node.count;
            int destroy = node.destroy;

            if (x == n - 1 && y == m - 1) {
                result = count;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    // 벽을 부순 적이 있음.
                    if (destroy == 1) {
                        // 다음 좌표가 빈 방이라면
                        if (map[nx][ny] == 0) {
                            // 벽을 부순 상태(destroy=1)에서 방문한 적이 있는지 없는지 확인한다.
                            if (!visited[nx][ny][destroy]) {
                                q.add(new Node(nx, ny, count + 1, destroy));
                                visited[nx][ny][destroy] = true;
                            }
                        }
                    } else if (destroy == 0) { // 벽을 부순 적이 없음.
                        // 다음 좌표가 벽이라면?
                        if (map[nx][ny] == 1) {
                            // 그 다음 좌표에 대해서 벽을 부수고 그 좌표로 간 적이 있는지 없는지 확인한다.
                            // 없다면 그 벽을 부수고 큐에 넣고 방문했음을 체크한다.
                            if (!visited[nx][ny][destroy + 1]) {
                                q.add(new Node(nx, ny, count + 1, destroy + 1));
                                visited[nx][ny][destroy + 1] = true;
                            }
                        } else if (map[nx][ny] == 0) { // 다음 좌표가 방이라면?
                            if (!visited[nx][ny][destroy]) {
                                q.add(new Node(nx, ny, count + 1, destroy));
                                visited[nx][ny][destroy] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Node {
        int x;
        int y;
        int count; // (n,m)까지 가는데 얼마나 걸리는지를 체크하는 변수.
        int destroy; // 벽을 부수었는지 부수지 않았는지를 나타내는 변수.

        Node(int x, int y, int count, int destroy) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.destroy = destroy;
        }

    }

}
