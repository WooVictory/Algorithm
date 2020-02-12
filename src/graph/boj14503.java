package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/12
 * 로봇 청소기.
 * bfs.
 * 다시 풀어보기!
 */
public class boj14503 {
    private static int n, m;
    private static int r, c, d;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n][m];
        visit = new boolean[n][m];

        String[] cmd = br.readLine().split(" ");
        r = toInt(cmd[0]);
        c = toInt(cmd[1]);
        d = toInt(cmd[2]);

        for (int i = 0; i < n; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = toInt(num[j]);
            }
        }

        bfs(r, c, d);
        getResult();
    }

    // 청소를 진행한다.
    private static void bfs(int x, int y, int d) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y, d));
        a[x][y] = 9;// 청소한 곳을 9로 표시한다.
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int currentX = node.x, currentY = node.y, currentD = node.d;
            boolean isClean = false;
            int nextD, nextX, nextY;

            for (int i = 0; i < 4; i++) {
                currentD = (currentD + 3) % 4;
                nextX = currentX + dx[currentD];
                nextY = currentY + dy[currentD];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;

                // 다음 위치가 아직 청소되지 않은 곳이면 탐색한다.
                if (!visit[nextX][nextY] && a[nextX][nextY] == 0) {
                    q.add(new Node(nextX, nextY, currentD));
                    visit[nextX][nextY] = true;
                    a[nextX][nextY] = 9;
                    cost++;
                    isClean = true;
                    break;
                    // 한번에 한 방향 즉, 왼쪽으로 돌았을 경우만 탐색하면 되기 때문에 4번의 경우를 다 돌 필요는 없다.
                    // 즉, 한 곳을 청소했으면, 왼쪽 방향이 청소되었는지 확인해야 하기 때문에 다른 방향을 보지 않는다.
                }
            }

            // 4 방향 모두 청소되었거나 벽일 경우에는 후진해야 한다.
            if (!isClean) {
                nextD = (currentD + 2) % 4;
                nextX = currentX + dx[nextD];
                nextY = currentY + dy[nextD];

                // 후진할 위치가 벽이 아니라면 이동.
                // 그렇지 않다면 그대로 종료.
                if (a[nextX][nextY] != 1) {
                    a[nextX][nextY] = 9;
                    visit[nextX][nextY]=true;
                    q.add(new Node(nextX, nextY, currentD));
                }
            }
        }
    }

    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j]) count++;
            }
        }
        System.out.println(count);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
