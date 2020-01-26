package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/26
 * bfs
 * boolean[][] 사용.
 */
public class boj2667 {
    private static int[][] a;
    private static boolean[][] visit;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int n;
    private static int apartment = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];
        visit = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= n; j++) {
                a[i][j] = input.charAt(j - 1) - 48;
            }
        }

        find();
    }

    // 집인 곳의 위치를 찾아서 bfs 탐색 진행.
    private static void find() {
        int total = 0;
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == 1 && !visit[i][j]) { // 집이고 방문한 적이 없으면 탐색.
                    total++;
                    apartment = 0;
                    bfs(i, j);

                    result.add(apartment);
                }
            }
        }

        System.out.println(total);
        Collections.sort(result);
        for (int value : result) System.out.println(value);
    }

    private static void bfs(int x, int y) {
        LinkedList<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.remove();
            apartment++;

            // 동,서,남,북 네 방향에 대한 범위 검사.
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > n) continue;


                if(a[nx][ny] == 1 && !visit[nx][ny]){
                    visit[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}