package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * created by victory_woo on 2020/01/30
 * 우선 순위 큐를 활용해 다익스트라 알고리즘 방법으로 품.
 */
public class boj1261_2 {
    private static int n, m;
    private static int[][] a, distance;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        m = toInt(in[0]);
        n = toInt(in[1]);

        a = new int[n + 1][m + 1];
        distance = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = input.charAt(j - 1) - '0';
            }
        }

        bfs();
    }

    private static void bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 1, 0));
        init();

        distance[1][1] = 0;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x, y = node.y;

            if (x == n && y == m) {
                System.out.println(node.cost);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                // 이동할 다음 정점에 대해 범위를 검사한다.
                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                // 방문 여부를 확인한다.
                if (visit[nx][ny]) continue;

                if (distance[nx][ny] > distance[x][y] + a[nx][ny]) {
                    distance[nx][ny] = distance[x][y] + a[nx][ny];
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny, distance[nx][ny]));
                }
            }

        }
    }

    // distance 배열 초기화 함수. 가장 큰 값으로 초기화 한다.
    private static void init() {
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                distance[i][j] = Integer.MAX_VALUE;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost; // 벽을 부순 비용. 즉, 벽을 얼마나 부수었는지 저장.

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        // 우선 순위 큐를 사용해 저장한다.
        // 우선 순위를 위해 comparable 활용.
        @Override
        public int compareTo(Node o) {
            return this.cost < o.cost ? -1 : 1;
        }
    }
}
