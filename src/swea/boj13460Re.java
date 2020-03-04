package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/04
 * 구슬 탈출2
 * bfs, 시뮬레이션
 * 다시 풀어보기!
 */
public class boj13460Re {
    private static int result = -1;
    private static int rx, ry, bx, by;
    private static char[][] map;
    private static boolean[][][][] visit;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int m = toInt(in[1]);

        // 문자를 입력받을 배열.
        map = new char[n][m];
        // 빨간 구슬과 파란 구슬을 visit 배열 하나로 함께 관리한다.
        visit = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);
                map[i][j] = c;

                // 빨간 구슬의 위치를 저장한다.
                if (c == 'R') {
                    rx = i;
                    ry = j;
                }

                // 파란 구슬의 위치를 저장한다.
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
            Node current = q.remove();
            int curRx = current.rx, curRy = current.ry;
            int curBx = current.bx, curBy = current.by;
            int curCnt = current.count;

            // 10번 이하로 구슬이 구멍을 지나서 탈출해야 한다.
            if (curCnt > 10) {
                result = -1;
                return;
            }

            // 파란 구슬이 구멍에 빠지면 실패.
            if (map[curBx][curBy] == 'O') continue;

            // 빨간 구슬이 구멍에 빠지면 결과를 저장하고 종료한다.
            if (map[curRx][curRy] == 'O') {
                result = curCnt;
                return;
            }

            // 상,하,좌,우 네 방향에 대해서 구슬을 기울인다.
            for (int i = 0; i < 4; i++) {
                // 빨간 구슬부터 먼저 기울인다.
                int nextRx = curRx, nextRy = curRy;

                while (true) {
                    // 벽이 아니고 구멍이 아닌 경우, 해당 방향으로 구슬을 기울인다.
                    if (map[nextRx][nextRy] != '#' && map[nextRx][nextRy] != 'O') {
                        nextRx += dx[i];
                        nextRy += dy[i];
                    } else {
                        // 위치가 벽일 경우, 한 칸 뒤로 이동한다.
                        if (map[nextRx][nextRy] == '#') {
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                        break;
                    }
                }

                // 파란 구슬을 기울인다.
                int nextBx = curBx, nextBy = curBy;

                while (true) {
                    // 벽이 아니고 구멍이 아닌 경우, 해당 방향으로 구슬을 기울여 이동시킨다.
                    if (map[nextBx][nextBy] != '#' && map[nextBx][nextBy] != 'O') {
                        nextBx += dx[i];
                        nextBy += dy[i];
                    } else {
                        // 위치가 벽일 경우, 한칸 뒤로 이동한다.
                        if (map[nextBx][nextBy] == '#') {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        }
                        break;
                    }
                }

                // 빨간 구슬의 위치와 파란 구슬의 위치가 같은 경우.
                // 즉 빨간 구슬과 파란 구슬이 동일한 칸에 위치한 경우.
                // 하지만, 동일한 칸에 위치할 수 없기 때문에 위치 조정이 필요하다.
                if (nextRx == nextBx && nextRy == nextBy) {
                    // 빨간 구슬과 파란 구슬이 같은 위치에 있으면서 구멍이 아닌 경우.
                    if (map[nextRx][nextRy] != 'O') {
                        // 더 많이 이동한 구슬을 한칸 뒤로 이동시켜 위치를 조정한다.

                        int rCost = Math.abs(nextRx - curRx) + Math.abs(nextRy - curRy);
                        int bCost = Math.abs(nextBx - curBx) + Math.abs(nextBy - curBy);

                        if (rCost < bCost) {
                            nextBx = nextBx - dx[i];
                            nextBy = nextBy - dy[i];
                        } else {
                            nextRx = nextRx - dx[i];
                            nextRy = nextRy - dy[i];
                        }
                    }
                }

                // 방문한 적이 있는지 확인하고 없을 경우, 방문 여부를 체크하고 큐에 다음 정점을 넣어준다.
                // 이때, count 를 증가시킨다.
                if (!visit[nextRx][nextRy][nextBx][nextBy]) {
                    visit[nextRx][nextRy][nextBx][nextBy] = true;
                    q.add(new Node(nextRx, nextRy, nextBx, nextBy, curCnt + 1));
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