package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/02
 */
public class boj3055 {

    private static int R, C, result = 0;
    private static char[][] a;
    private static int[][] distance;
    private static LinkedList<Node> q;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        R = toInt(in[0]);
        C = toInt(in[1]);

        a = new char[R + 1][C + 1];
        distance = new int[R + 1][C + 1];
        q = new LinkedList<>();

        for (int i = 1; i <= R; i++) {
            String input = br.readLine();
            for (int j = 1; j <= C; j++) {
                a[i][j] = input.charAt(j - 1);
            }
        }

        findWater();
        findHedgehog();
        bfs();

        // result 변수에 count 를 담기 때문에 굳이 반복문을 돌며 찾지 않아도 됨.
        // 하지만, 이렇게 해도 정답임.
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (a[i][j] == 'D') {
                    System.out.println(distance[i][j] == 0 ? "KAKTUS" : distance[i][j]);
                }
            }
        }

        // 이렇게 해도 정답.
        System.out.println(result == 0 ? "KAKTUS" : result);
    }

    // 먼저, 물인 곳을 찾아서 큐에 넣는다.
    private static void findWater() {
        for (int i = 1; i <= R; i++)
            for (int j = 1; j <= C; j++) if (a[i][j] == '*') q.add(new Node(i, j, 0, '*'));
    }

    // 고슴도치의 위치를 큐에 넣는다.
    private static void findHedgehog() {
        for (int i = 1; i <= R; i++)
            for (int j = 1; j <= C; j++) if (a[i][j] == 'S') q.add(new Node(i, j, 0, 'S'));
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x, y = node.y, cost = node.cost;
            char c = node.c;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > R || ny > C) continue;

                // 이전 좌표에 대한 값이 물인 경우.
                if (c == '*') {
                    if (a[nx][ny] != 'X' && a[nx][ny] != 'D' && a[nx][ny] != '*') {
                        a[nx][ny] = '*'; // 물을 채운다.
                        q.add(new Node(nx, ny, cost, '*'));
                    }
                }

                // 이전 좌표에 대한 값이 고슴도치인 경우.
                if (c == 'S') {
                    if (a[nx][ny] != 'X' && (a[nx][ny] == '.' || a[nx][ny] == 'D')) {
                        distance[nx][ny] = distance[x][y] + 1;
                        if (a[nx][ny] == 'D') {
                            result = cost + 1;
                            return;
                        }
                        a[nx][ny] = 'S'; // 고슴도치의 위치를 이동시킨다.
                        q.add(new Node(nx, ny, cost + 1, 'S'));
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
        int cost;
        char c;

        Node(int x, int y, int cost, char c) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.c = c;
        }
    }
}
