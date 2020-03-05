package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/05
 * 구슬 탈출2
 * bfs, 시뮬레이션.
 */
public class sw13460_review {
    private static char[][] map;
    private static boolean[][][][] visit;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int rx, ry;
    private static int bx, by;
    private static int result = -1;
    // 어떠한 조건에도 걸리지 않는 경우가 있을 수 있어서 초기 값을 -1로 해준다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int m = toInt(in[1]);

        // 입력을 저장하기 위한 배열.
        map = new char[n][m];

        // 빨간 구슬과 파란 구슬을 함께 방문 여부를 관리하기 위한 배열.
        visit = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);
                map[i][j] = c;

                // 빨간 구슬의 좌표를 저장한다.
                if (c == 'R') {
                    rx = i;
                    ry = j;
                }

                // 파란 구슬의 좌표를 저장한다.
                if (c == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(rx, ry, bx, by, 0));
        visit[rx][ry][bx][by] = true;

        while (!q.isEmpty()) {
            Node node = q.remove();
            int curRx = node.rx, curRy = node.ry;
            int curBx = node.bx, curBy = node.by;
            int curCount = node.count;

            // 10번 이하로 구슬을 구멍으로 빼내야 하기 때문에
            // 10번이 넘어가면 실패이다. -1 출력.
            if (curCount > 10) {
                result = -1;
                return;
            }

            // 파란 구슬이 구멍에 빠지면 실패이므로, 큐에 있는 다른 요소를 빼도록 한다.
            if (map[curBx][curBy] == 'O') continue;

            // 빨간 구슬이 구멍에 빠지면 성공이므로, 결과를 저장하고 종료한다.
            if (map[curRx][curRy] == 'O') {
                result = curCount;
                return;
            }

            // 상,하,좌,우 네 방향에 대해서 검사한다.
            // 즉, 구슬을 기울인다.
            for (int i = 0; i < 4; i++) {
                // 빨간 구슬을 먼저 기울인다.
                int nextRx = curRx, nextRy = curRy;

                // 탈출할 때까지 계속 기울여서 움직인다.
                while (true) {
                    // 벽이 아니고 구멍이 아니면 계속해서 이동시킨다.
                    if (map[nextRx][nextRy] != '#' && map[nextRx][nextRy] != 'O') {
                        nextRx += dx[i];
                        nextRy += dy[i];
                    } else {
                        // 이동하다가 벽까지 갔을 때, 벽을 만나기 때문에 한칸 뒤로 이동한다.
                        if (map[nextRx][nextRy] == '#') {
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                        break;
                    }
                }

                // 파란 구슬에 대해서 기울인다.
                int nextBx = curBx, nextBy = curBy;

                // 탈출할 때까지 계속 기울여서 움직인다.
                while (true) {
                    // 벽이 아니고 구멍이 아니면 계속해서 이동시킨다.
                    if (map[nextBx][nextBy] != '#' && map[nextBx][nextBy] != 'O') {
                        nextBx += dx[i];
                        nextBy += dy[i];
                    } else {
                        // 이동하다가 벽까지 갔을 때, 벽을 만나기 때문에 한칸 뒤로 이동한다.
                        if (map[nextBx][nextBy] == '#') {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        }
                        break;
                    }
                }

                // 구슬을 각각의 방향에 대해서 모두 이동시킨 다음에
                // 만약, 빨간 구슬과 파란 구슬의 위치가 같고 구멍이 아닌 경우에는 두 구슬이 같은 곳에 위치하는 경우이다.
                // 따라서 한 구슬의 위치를 조정해야 한다. -> 조정할 때는 더 많이 움직인 구슬을 한칸 뒤로 이동시킨다.
                if (nextRx == nextBx && nextRy == nextBy) {
                    if (map[nextRx][nextRy] != 'O') {
                        int rCost = Math.abs(nextRx - curRx) + Math.abs(nextRy - curRy);
                        int bCost = Math.abs(nextBx - curBx) + Math.abs(nextBy - curBy);

                        if (rCost < bCost) {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        } else {
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                    }
                }

                // 방문 여부를 확인한 뒤, 방문한 적이 없으면 방문 여부를 체크하고 큐에 넣는다.
                // 이때, count+1 해준다.
                if (!visit[nextRx][nextRy][nextBx][nextBy]) {
                    visit[nextRx][nextRy][nextBx][nextBy] = true;
                    q.add(new Node(nextRx, nextRy, nextBx, nextBy, curCount + 1));
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int rx, ry, bx, by, count;

        public Node(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}
