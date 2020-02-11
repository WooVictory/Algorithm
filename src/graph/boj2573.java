package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/11
 * 빙산.
 * bfs.
 * 다시 풀어보기!
 */
public class boj2573 {
    private static int n, m, answer;
    private static int[][] a;
    private static int[][] cost;
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n + 1][m + 1];
        cost = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                a[i][j] = toInt(num[j - 1]);
            }
        }

        int count;
        while ((count = getComponent()) < 2) {
            // 컴포넌트의 갯수가 0이라는 것은 모든 빙산이 끊어지기 전에 이미 빙산이 모두 녹아버린 상태이다.
            if (count == 0) {
                answer = 0;
                break;
            }

            // count 를 증가시키는 것이 아니라 answer 를 증가시켜야 한다.
            // 빙산을 녹이는데 얼마나 걸리는지 계산.
            answer++;
            visit = new boolean[n + 1][m + 1];

            // 빙산을 녹이는 데 얼마나 걸리는지 계산한다.
            // 해당 정점이 빙산일 때, 동,서,남,북 네 방향을 계산하여 바다인 정점의 갯수를 cost 에 저장한다.
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (a[i][j] != 0) {
                        calculateCost(i, j);
                    }
                }
            }

            // 빙산을 녹이는 과정.
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    a[i][j] -= cost[i][j];
                    if (a[i][j] < 0) a[i][j] = 0;
                }
            }
        }

        System.out.println(answer);
    }

    // 연결 요소의 갯수를 구한다.
    // 여기서 bfs 탐색이 이루어진다.
    private static int getComponent() {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i][j] != 0 && !visit[i][j]) {
                    count++;
                    bfs(i, j);
                }
            }
        }
        return count;
    }

    // 해당 정점에 대한 빙산을 녹이는 데 얼마나 걸리는지 cost 배열에 저장한다.
    private static void calculateCost(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

            if (a[nx][ny] == 0) count++;
        }

        cost[x][y] = count;
    }

    // 일반적인 bfs 탐색 과정이다.
    private static void bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.remove();
            x = node.x;
            y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (visit[nx][ny]) continue;

                if (a[nx][ny] != 0) {
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
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

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}