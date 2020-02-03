package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/03
 * 벽 부수고 이동하기.
 */
public class boj2206Re {
    private static int n, m, result = Integer.MAX_VALUE;
    private static int[][] a;
    private static boolean[][][] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

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
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(1, 1, 1, 0));
        visit[1][1][0] = true;

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

                // 다음 좌표가 이동할 수 있는 곳인 경우.
                if (a[nx][ny] == 0) {
                    if (!visit[nx][ny][count]) {
                        visit[nx][ny][count] = true;
                        q.add(new Node(nx, ny, distance + 1, count));
                    }
                } else if (a[nx][ny] == 1) {
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

        Node(int x, int y, int distance, int count) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.count = count;
        }
    }
}