package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/30
 */
public class boj2206 {
    private static int n, m;
    private static int[][] a;
    private static boolean[][][] visit;
    private static int result = 0;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1][2];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        bfs();
        bfsWithSimple();
        System.out.println(result == 0 ? -1 : result);
    }

    // count 즉, 벽을 부순 적이 있는지 없는지로 먼저 분기를 나눠서
    // 또 그 각각에 대해서 다음 좌표가 어떤 것인지 확인하는 방법.
    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(1, 1, 1, 0));

        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x, y = node.y, distance = node.distance, count = node.count;

            if (x == n && y == m) {
                result = distance;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (count == 1) { // 벽을 부순 적이 있는 경우.
                    // 다음 좌표가 빈 방인 경우.
                    // 이때는 다음 좌표에 대해서 방을 부수고 방문한 적이 있는지 체크한다.
                    if (a[nx][ny] == 0) {
                        if (!visit[nx][ny][count]) {
                            visit[nx][ny][count] = true;
                            q.add(new Node(nx, ny, distance + 1, count));
                        }
                    }
                } else if (count == 0) { // 벽을 부순 적이 없는 경우.
                    // 다음 좌표가 빈 방인 경우.
                    // 이때는 다음 좌표에 대해서 방을 부수지 않고 방문한 적이 있는지 체크한다.
                    if (a[nx][ny] == 0) {
                        if (!visit[nx][ny][count]) {
                            visit[nx][ny][count] = true;
                            q.add(new Node(nx, ny, distance + 1, count));
                        }
                        // 다음 좌표가 벽인 경우.
                    } else if (a[nx][ny] == 1) {
                        // 이 조건은 벽을 부순 적이 없고 다음 좌표가 벽인 경우이기 때문에
                        // 벽을 부수고 이동할 수 있기 때문에 다음 좌표가 벽을 부수고 방문한 적이 있는지 체크한다.
                        if (!visit[nx][ny][count + 1]) {
                            visit[nx][ny][count + 1] = true;
                            q.add(new Node(nx, ny, distance + 1, count + 1));
                        }
                    }
                }
            }
        }
    }

    // 다음 좌표가 빈 방인지 벽인지만 확인해서 그 안에서 분기를 나누는 방법.
    private static void bfsWithSimple() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(1, 1, 1, 0));

        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x, y = node.y, distance = node.distance, count = node.count;

            if (x == n && y == m) {
                result = distance;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                // 다음 좌표가 방인 경우.
                if (a[nx][ny] == 0) {
                    if (!visit[nx][ny][count]) {
                        visit[nx][ny][count] = true;
                        q.add(new Node(nx, ny, distance + 1, count));
                    }
                } else if (a[nx][ny] == 1) { // 다음 좌표가 벽인 경우.
                    if (count < 1 && !visit[nx][ny][count + 1]) {
                        visit[nx][ny][count + 1] = true;
                        q.add(new Node(nx, ny, distance + 1, count + 1));
                    }
                }

            }
        }
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int distance;
        int count;

        public Node(int x, int y, int distance, int count) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.count = count;
        }
    }
}
