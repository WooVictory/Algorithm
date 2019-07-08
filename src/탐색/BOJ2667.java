package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 08/07/2019
 * 단지 번호 붙이기.
 * bfs, dfs 둘 다 완료.
 */
public class BOJ2667 {
    private static final String NEW_LINE = "\n";
    private static ArrayList<Integer> countList = new ArrayList<>();
    private static int n, components, count;
    private static int[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        a = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1 && !visited[i][j]) {
                    components++;
                    count = 0;
                    //dfs(i, j);
                    bfs(i, j);

                    countList.add(count);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(countList);

        sb.append(components).append(NEW_LINE);
        for (int v : countList) {
            sb.append(v).append(NEW_LINE);
        }
        System.out.println(sb.toString());


        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j]+SPACE);
            }
            System.out.println();
        }*/
    }

    private static void dfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        count++;
        visited[x][y] = true;
        a[x][y] = components;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            if (!visited[nx][ny] && a[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

    private static void bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        count++;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int nowX = node.x;
            int nowY = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                if (!visited[nx][ny] && a[nx][ny] == 1) {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
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
