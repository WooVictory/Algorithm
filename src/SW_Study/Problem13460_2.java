package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/13
 * 구슬 탈출2
 * 삼성 기출.
 * bfs + 시뮬레이션.
 */
public class Problem13460_2 {
    private static char[][] map;
    private static boolean[][][][] visit;
    private static int result = -1;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int rx, ry, bx, by;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]), m = toInt(in[1]);

        map = new char[n][m];
        visit = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);
                map[i][j] = c;

                if (c == 'R') {
                    rx = i;
                    ry = j;
                }

                if (c == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        solve();
        System.out.println(result);
    }

    private static void solve() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(rx, ry, bx, by, 0));
        visit[rx][ry][bx][by] = true;

        while (!q.isEmpty()) {
            Node cur = q.remove();
            int curRx = cur.rx, curRy = cur.ry;
            int curBx = cur.bx, curBy = cur.by;
            int count = cur.count;

            // 1. 10회 이하로 구슬을 탈출시키지 못한다면 -1을 반환.
            if (count > 10) {
                result = -1;
                return;
            }

            // 2. 파란 구슬이 구멍에 빠지면, 위로 올라가 큐에서 다른 구슬을 꺼낸다.
            if (map[curBx][curBy] == 'O') continue;

            // 3. 빨간 구슬이 구멍에 빠지면, 결과를 반환한다.
            if (map[curRx][curRy] == 'O') {
                result = count;
                return;
            }

            // 네 방향으로 구슬을 기울여 이동시킨다.
            for (int i = 0; i < 4; i++) {
                int nextRx = curRx, nextRy = curRy;

                while (true) {
                    if (map[nextRx][nextRy] != '#' && map[nextRx][nextRy] != 'O') {
                        nextRx += dx[i];
                        nextRy += dy[i];
                    } else {
                        if (map[nextRx][nextRy] == '#') {
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                        break;
                    }
                }

                int nextBx = curBx, nextBy = curBy;
                while (true) {
                    if (map[nextBx][nextBy] != '#' && map[nextBx][nextBy] != 'O') {
                        nextBx += dx[i];
                        nextBy += dy[i];
                    } else {
                        if (map[nextBx][nextBy] == '#') {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        }
                        break;
                    }
                }

                if (nextRx == nextBx && nextRy == nextBy) {
                    if (map[nextRx][nextRy] != 'O') {
                        int redCost = getCost(curRx, curRy, nextRx, nextRy);
                        int blueCost = getCost(curBx, curBy, nextBx, nextBy);

                        if (redCost < blueCost) {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        } else {
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                    }
                }

                if (!visit[nextRx][nextRy][nextBx][nextBy]) {
                    visit[nextRx][nextRy][nextBx][nextBy] = true;
                    q.add(new Node(nextRx, nextRy, nextBx, nextBy, count + 1));
                }
            }
        }
    }

    private static int getCost(int cx, int cy, int nx, int ny) {
        return Math.abs(cx - nx) + Math.abs(cy - ny);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int rx;
        int ry;
        int bx;
        int by;
        int count;

        Node(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}
