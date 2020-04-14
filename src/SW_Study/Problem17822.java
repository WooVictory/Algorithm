package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/14
 * 원판 돌리기
 * 삼성 기출.
 */
public class Problem17822 {
    private static int n, m, t, sum = 0;
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        t = toInt(in[2]);

        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            in = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = toInt(in[j - 1]);
            }
        }

        int x, d, k;
        for (int i = 0; i < t; i++) {
            visit = new boolean[n + 1][m + 1];
            in = br.readLine().split(" ");
            x = toInt(in[0]); // x의 배수여야 함.
            d = toInt(in[1]); // 0 : 시계, 1 : 반시계.
            k = toInt(in[2]); // 몇 칸 회전 할 지.

            solve(x, d, k);
        }
    }

    private static void solve(int x, int d, int k) {
        int[][] copy = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
        }

        for (int i = 1; i <= n; i++) {
            if (i % x != 0) continue;
            for (int j = 1; j <= m; j++) {
                int nextDir;
                // 시계 방향인 경우.
                if (d == 0) {
                    nextDir = (j + k) % m;
                    if (nextDir == 0) nextDir = 4;
                    copy[i][nextDir] = map[i][j];
                }

                if (d == 1) {
                    nextDir = (m + (j - k)) % m;
                    if (nextDir == 0) nextDir = 4;
                    copy[i][nextDir] = map[i][j];
                }
            }
        }


        map = copy;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!visit[i][j]) {
                    //System.out.println("In -> x : " + i + ", y : " + j);
                    getResult(i, j);
                }
            }
        }

        print(map);


        int total = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) total += map[i][j];
        }

//        System.out.println(total);
//        System.out.println(sum);

        System.out.println(total - sum);

    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    // bfs.
    private static void getResult(int x, int y) {
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

                if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

                if (!visit[nx][ny] && map[nx][ny] == map[x][y]) {
                    //System.out.println("nx : " + nx + ", ny : " + ny);
                    visit[nx][ny] = true;
                    map[x][y] = map[nx][ny] = 0;
                    sum += map[nx][ny];
                    sum += map[x][y];
                    q.add(new Node(nx, ny));
                }
            }
        }
    }


    //    4 4 1
    //    1 1 2 3
    //    5 2 4 2
    //    3 1 3 5
    //    2 1 3 2
    //    2 1 1
    private static void print(int[][] copy) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(copy[i][j] + " ");
            }
            System.out.println();
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
