package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/30
 * 안전 영역.
 * bfs/dfs
 * 컴포넌트의 갯수를 구하는 문제.
 */
public class boj2468Re {
    private static int n, result = 0;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        map = new int[n][n];

        // 입력을 받으면서 높이가 가장 높은 지역의 높이를 height 값에 저장한다.
        int height = 0;
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
                if (height < map[i][j]) height = map[i][j];
            }
        }

        // 그 height 높이 값부터 1까지 반복하며 해당 지점이 물에 잠겼을 때의 안전 영역 갯수를 구한다.
        while (height-- > 0) {
            visit = new boolean[n][n];
            int components = 0;

            // visit 배열을 이용해서 해당 높이 이하인 지점을 물에 잠기게 한다.
            // height, height-1, height-2, ... 1까지 반복한다.
            // 따라서 map 배열을 변경시키는 것이 아닌 visit 배열을 가지고 값을 변경시켜 물에 잠긴 것을 표현한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] <= height) visit[i][j] = true;
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

            // 물에 잠긴 지점을 제외하고 탐색을 진행하며 안전영역 즉, 컴포넌트의 갯수를 카운팅한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        components++;
                        //bfs(i, j);
                        dfs(i, j);
                    }
                }
            }

            // 카운팅한 값 중 가장 큰 값을 저장한다.
            if (result < components) result = components;
        }
        System.out.println(result);
    }

    // bfs
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

    // dfs
    private static void dfs(int x, int y) {
        if (visit[x][y]) return;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (visit[nx][ny]) continue;

            dfs(nx, ny);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
