package dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 02/07/2019
 * dfs : 안전 영역.
 */
public class BOJ2468 {
    private static ArrayList<Integer> list = new ArrayList<>();
    private static int n, components;
    private static int[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = parse(br.readLine());

        a = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                a[i][j] = parse(in[j]);
            }
        }

        // 높이의 범위가 1~100이기 때문에
        // 반복문을 돌며 제한된 높이를 결정한다.
        int x = 100;
        while (x-- > 0) {

            components = 0;
            visited = new boolean[n][n];

            // 높이가 x 이하인 지점을 먼저 표시하기.
            // a 배열은 건드리지 않고 visited 배열만을 수정함으로써 방문했음을 표시한다.
            // 이로 인해서 물이 찼음을 나타낸다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        if (a[i][j] <= x) {
                            visited[i][j] = true;
                        }
                    }
                }
            }

            // 안전 영역에 대한 dfs 탐색을 진행한다.
            // 즉, 물이 차지 않은 영역에 대해서 탐색을 한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        components++;
                        dfs(i, j);
                    }
                }
            }
            // 안전 영역에 대한 개수를 리스트에 저장한다.
            list.add(components);
        }

        bw.write(Collections.max(list) + "");
        bw.flush();
    }

    private static void dfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            if (!visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}