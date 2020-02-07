package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/07
 */
public class boj9376 {
    private static final int MAX = 105;
    private static char[][] a;
    private static int h, w;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");

            // 영역을 조금 확장한다.
            // 상,하,좌,우로 각각 1칸씩 추가해주기 위해서 +2를 한다.
            h = toInt(in[0]) + 2;
            w = toInt(in[1]) + 2;

            Node helper = new Node(0, 0); // 상근이.
            Node prison1 = null, prison2 = null; // 죄수 2명.

            a = new char[MAX][MAX];

            for (int i = 1; i < h - 1; i++) {
                String input = "." + br.readLine() + ".";
                for (int j = 0; j < w; j++) {
                    char ch = input.charAt(j);
                    switch (ch) {
                        case '.':
                        case '*':
                        case '#':
                            a[i][j] = ch;
                            break;
                        case '$':
                            a[i][j] = ch;
                            if (prison1 == null) prison1 = new Node(i, j);
                            else prison2 = new Node(i, j);
                            break;
                    }
                }
            }

            // 위에서 확장하지 못했던 영역을 마저 확장해준다.
            // 이렇게 따로 하는 이유는 위에서 같이 하게 되면, 죄수의 위치를 정확하게 놓지 못할 수 있기 때문이다.
            for (int i = 0; i < w; i++) a[0][i] = a[h - 1][i] = '.';

            int[][] distance1 = bfs(helper), distance2 = bfs(prison1), distance3 = bfs(prison2);
            int min = h * w, cost;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (a[i][j] == '*') continue;

                    cost = distance1[i][j] + distance2[i][j] + distance3[i][j];
                    if (a[i][j] == '#') cost -= 2;

                    min = Math.min(min, cost);
                }
            }
            System.out.println(min);
        }

    }

    private static int[][] bfs(Node node) {
        int[][] distance = new int[h][w];
        // 각 행에 속한 모든 열들을 -1로 초기화.
        for (int i = 0; i < h; i++) Arrays.fill(distance[i], -1);

        LinkedList<Node> q = new LinkedList<>();
        q.add(node);
        distance[node.x][node.y] = 0;

        while (!q.isEmpty()) {
            Node now = q.remove();
            int x = now.x, y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;

                if (a[nx][ny] == '*') continue;

                // 다음 정점이 문일 경우.
                if (a[nx][ny] == '#') {
                    if (distance[nx][ny] == -1 || distance[nx][ny] > distance[x][y] + 1) {
                        distance[nx][ny] = distance[x][y] + 1;
                        q.add(new Node(nx, ny));
                    }
                    // 다음 정점이 빈 곳이거나 죄수자의 위치일 경우.
                } else if (a[nx][ny] == '.' || a[nx][ny] == '$') {
                    if (distance[nx][ny] == -1 || distance[nx][ny] > distance[x][y]) {
                        distance[nx][ny] = distance[x][y];
                        q.add(new Node(nx, ny));
                    }
                }
            }
        }
        return distance;
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