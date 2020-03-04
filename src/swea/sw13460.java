package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/04
 * 구슬 탈출2
 * 삼성 기출
 */
public class sw13460 {
    private static int result = 0;
    private static char[][] map;
    private static boolean[][][][] visit;
    private static int rx, ry, bx, by;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int m = toInt(in[1]);

        map = new char[n][m];
        visit = new boolean[n][m][n][m];
        // 빨간 구슬과 파란 구슬의 위치를 visit 4차원 배열로 관리한다.


        // 입력을 받으면서 빨간 구슬과 파란 구슬의 위치를 저장한다.
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
            int curCnt = node.count;

            // 10번 이하로 찾아야 하기 때문에 10번을 넘으면 result 에 -1을 저장하고 빠져나온다.
            if (curCnt > 10) {
                result = -1;
                return;
            }

            // 파란 구슬이 구멍인 O에 빠지면 실패.
            if (map[curBx][curBy] == 'O') continue;

            // 빨간 구슬이 구멍인 O에 빠지면 count 를 출력한다.
            if (map[curRx][curRy] == 'O') {
                result = curCnt;
                return;
            }

            // 상,하,좌,우 네 방향에 대해서 탐색한다.
            for (int i = 0; i < 4; i++) {
                // 빨간 구슬을 먼저 굴린다.
                int nextRx = curRx, nextRy = curRy;

                while (true) {
                    // 벽이 아니고 구멍이 아니면 상하좌우의 한 방향으로 끝까지 이동시킨다.
                    if (map[nextRx][nextRy] != '#' && map[nextRx][nextRy] != 'O') {
                        nextRx = nextRx + dx[i];
                        nextRy = nextRy + dy[i];
                    } else {
                        // 다 이동하고 나서 벽이라면 벽까지 이동한 것이므로 한칸 뒤로 이동한다.
                        if (map[nextRx][nextRy] == '#') {
                            nextRx = nextRx - dx[i];
                            nextRy = nextRy - dy[i];
                        }
                        break;
                    }
                }

                int nextBx = curBx, nextBy = curBy;

                while (true) {
                    if (map[nextBx][nextBy] != '#' && map[nextBx][nextBy] != 'O') {
                        nextBx = nextBx + dx[i];
                        nextBy = nextBy + dy[i];
                    } else {
                        if (map[nextBx][nextBy] == '#') {
                            nextBx = nextBx - dx[i];
                            nextBy = nextBy - dy[i];
                        }
                        break;
                    }
                }


                // 두 구슬의 위치가 같은데 구멍은 아닐 때.
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

                if (!visit[nextRx][nextRy][nextBx][nextBy]) {
                    visit[nextRx][nextRy][nextBx][nextBy] = true;
                    q.add(new Node(nextRx, nextRy, nextBx, nextBy, curCnt + 1));
                }
            }
        }
        result = -1;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    // Node 클래스는 빨간 구슬과 파란 구슬의 위치, 그리고 count 값을 관리한다.
    static class Node {
        int rx, ry, bx, by, count;

        Node(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}
