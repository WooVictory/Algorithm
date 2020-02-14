package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/14
 * 로봇 청소기.
 * bfs 탐색을 이용하는 방법.
 * 다시 풀어보기.
 * 방향을 바꾸고 dx, dy 배열을 고르는 부분이 이해가 안가지만, 일단 진행하기!
 * 다시 풀어보기!
 */
public class boj14503_3 {
    private static int n, m;
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북, 동, 남, 서의 왼쪽 방향을 dx, dy 배열로 구성.
    // 서, 북, 동, 남이 된다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        a = new int[n][m];
        visit = new boolean[n][m];

        String[] command = br.readLine().split(" ");
        int r = toInt(command[0]);
        int c = toInt(command[1]);
        int d = toInt(command[2]);

        for (int i = 0; i < n; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = toInt(num[j]);
            }
        }

        bfs(r, c, d);
        getResult();
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

    private static void bfs(int x, int y, int d) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y, d));
        a[x][y] = 9; // 해당 공간을 청소함.
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int currentX = node.x, currentY = node.y, currentD = node.d;
            int nextX, nextY, nextD;
            boolean isClean = false;

            // 왼쪽 방향을 찾아서 청소하지 않은 구역을 탐색한다.
            for (int i = 0; i < 4; i++) {
                currentD = (currentD + 3) % 4;
                nextX = currentX + dx[currentD];
                nextY = currentY + dy[currentD];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;

                // 빈 공간이라면 청소를 해야 한다.
                if (a[nextX][nextY] == 0 && !visit[nextX][nextY]) {
                    a[nextX][nextY] = 9;
                    visit[nextX][nextY] = true;
                    q.add(new Node(nextX, nextY, currentD));
                    isClean = true;
                    break;
                }
            }

            // 네 방향이 모두 청소되어 있을 경우.
            if (!isClean) {
                nextD = (currentD + 2) % 4;
                nextX = currentX + dx[nextD];
                nextY = currentY + dy[nextD];

                if (a[nextX][nextY] != 1) {
                    a[nextX][nextY] = 9;
                    visit[nextX][nextY] = true;
                    q.add(new Node(nextX, nextY, currentD));
                }
            }
        }
    }

    private static int backDirection(int d) {
        switch (d) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            default:
                return 1;
        }
    }

    // 북 : 0, 동 : 1, 남 : 2, 서 : 3
    private static int turnDirection(int d) {
        switch (d) {
            case 0:
                return 3;
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return 2;
        }
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
