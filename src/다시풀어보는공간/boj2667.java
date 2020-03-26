package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/26
 * 단지번호 붙이기
 * 컴포넌트의 갯수를 구하는 문제.
 */
public class boj2667 {
    private static int n, components, count;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) map[i][j] = s.charAt(j) - '0';
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    if (!visit[i][j]) {
                        components++; // 연결된 아파트 단지의 갯수를 나타낸다.
                        count = 0; // 각 아파트 단지마다 count 를 초기화한다.
                        //bfs(i, j);
                        dfs(i, j);

                        list.add(count);
                    }
                }
            }
        }

        System.out.println(components);
        Collections.sort(list);
        for (int a : list) System.out.println(a);

        // 단지 번호를 붙인 아파트 단지의 구성을 보기 위함.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // bfs
    private static void bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visit[x][y] = true;
        map[x][y] = components;
        count++;

        while (!q.isEmpty()) {
            Node cur = q.remove();
            x = cur.x;
            y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visit[nx][ny]) continue;

                if (map[nx][ny] == 1) {
                    count++; // 연결된 아파트 단지라면 count 값을 증가시킨다.
                    visit[nx][ny] = true;
                    map[nx][ny] = components;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    // dfs
    private static void dfs(int x, int y) {
        if (visit[x][y]) return;
        visit[x][y] = true;
        count++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

            if (map[nx][ny] == 1) {
                map[nx][ny] = components;
                dfs(nx, ny);
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
