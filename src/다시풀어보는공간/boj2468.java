package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/30
 * 안전 영역.
 */
public class boj2468 {
    private static int n;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        map = new int[n][n];

        int height = 0;
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);

                if (height < map[i][j]) height = map[i][j];
            }
        }

        System.out.println(height);
        int max = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (height-- > 0) {
            visit = new boolean[n][n];
            int components = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] <= height) {
                        visit[i][j] = true;
                    }
                }
            }

            System.out.println(height + " 이하인 지점은 물에 잠긴다.");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j]) System.out.print(0 + " ");
                    else System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        components++;
                        bfs(i, j);
                    }
                }
            }

            max = Math.max(max, components);
            result.add(components);
        }

        System.out.println(max);
        System.out.println(Collections.max(result));
    }

    private static void bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node cur = q.remove();
            x = cur.x;
            y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (!visit[nx][ny]) {
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny));
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
