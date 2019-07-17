package samsung;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 17/07/2019
 * 구슬 탈출2 복습.
 */
public class sw13460_review {
    private static final String SPACE = " ";
    private static int rx, ry, bx, by, result;
    private static char[][] a;
    private static boolean[][][][] visited;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(SPACE);
        int n = parse(in[0]);
        int m = parse(in[1]);

        a = new char[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1][n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = s.charAt(j - 1);
                switch (a[i][j]) {
                    case 'R':
                        // 빨간 구슬의 위치 저장.
                        rx = i;
                        ry = j;
                        break;
                    case 'B':
                        // 파란 구슬의 위치 저장.
                        bx = i;
                        by = j;
                        break;
                }
            }
        }
        bfs();
        bw.write(String.valueOf(result));
        bw.flush();
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!q.isEmpty()) {
            Node current = q.remove();
            int currentRx = current.rx;
            int currentRy = current.ry;
            int currentBx = current.bx;
            int currentBy = current.by;
            int currentCount = current.count;

            // 10번 이하로 구슬을 탈출 시키지 못하면
            // 정답을 찾지 못했음으로 간주한다.
            if (currentCount > 10) {
                result = -1;
                return;
            }

            // 파란 구슬이 구멍에 들어간다면 실패.
            // 따라서 continue 를 통해서 올라가서 큐에서 다음 정점을 빼서 탐색을 다시 한다.
            if (a[currentBx][currentBy] == 'O') {
                continue;
            }

            // 빨간 구슬이 구멍에 들어간다면 성공.
            // count 를 result 에 담는다.
            if (a[currentRx][currentRy] == 'O') {
                result = currentCount;
                return;
            }

            // 상하좌우 4방향에 대해서 탐색한다.
            for (int i = 0; i < 4; i++) {
                int nextRx = currentRx, nextRy = currentRy;

                // 빨간 구슬에 대해 상,하,좌,우의 한 방향에 대해서
                // 벽을 만날 때까지 이동한다. 즉, 기울이기.
                while (true) {
                    // 벽이 아니고 구멍이 아니라면 이동 가능하다.
                    if (a[nextRx][nextRy] != '#' && a[nextRx][nextRy] != 'O') {
                        nextRx += dx[i];
                        nextRy += dy[i];
                    } else {
                        // 벽까지 이동하기 때문에
                        // 벽을 만난다면 한 칸 후진해야 한다.
                        if (a[nextRx][nextRy] == '#') {
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        }
                        // 한 방향으로의 이동이 끝났으므로 가까운 반복문을 빠져나온다.
                        break;
                    }
                }

                // 파란 구슬에 대해서 위와 같은 작업을 반복한다.
                int nextBx = currentBx, nextBy = currentBy;

                while (true) {
                    // 벽이 아니고 구멍이 아니라면 이동 가능하다.
                    if (a[nextBx][nextBy] != '#' && a[nextBx][nextBy] != 'O') {
                        nextBx += dx[i];
                        nextBy += dy[i];
                    } else {
                        // 마찬가지로 벽까지 이동하기 때문에
                        // 벽을 만난다면 한 칸 후진해야 한다.
                        if (a[nextBx][nextBy] == '#') {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        }
                        // 한 방향으로의 이동이 끝났으므로 가까운 반복문을 빠져나온다.
                        break;
                    }
                }

                // 빨간 구슬과 파란 구슬의 위치가 같고
                // 구멍이 아니라면, 가장 많이 이동한 구슬의 좌표를 한칸 후진한다.
                // 근데 왜 이렇게 하는 걸까...? 이유는 아직 모름...ㅜ
                if (nextRx == nextBx && nextRy == nextBy) {
                    if (a[nextRx][nextRy] != 'O') {
                        int redCost = Math.abs(nextRx - currentRx) + Math.abs(nextRy - currentRy);
                        int blueCost = Math.abs(nextBx - currentBx) + Math.abs(nextBy - currentBy);

                        // 많이 이동한 구슬이 한칸 뒤로 후진한다.
                        if (redCost > blueCost) {
                            nextRx -= dx[i];
                            nextRy -= dy[i];
                        } else {
                            nextBx -= dx[i];
                            nextBy -= dy[i];
                        }
                    }
                }

                // 방문한 경험이 있는지 확인한다.
                // 방문하지 않았다면 방문하고 큐에 넣는다.
                // 넣을 때, 구슬들이 한번 이동했으므로 count 를 증가시켜준다.
                if (!visited[nextRx][nextRy][nextBx][nextBy]) {
                    visited[nextRx][nextRy][nextBx][nextBy] = true;
                    q.add(new Node(nextRx, nextRy, nextBx, nextBy, current.count + 1));
                }
            }
        }
        // 위에서 return 으로 빠져나가지 못하면 정답을 찾은 것이라고 볼 수 없으므로
        // -1을 result 에 저장한다.
        result = -1;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    // 빨간 구슬과 파란 구슬의 좌표를 동시에 관리한다.
    // 이유는 빨간 구슬과 파란 구슬이 동시에 이동하기 때문이다.
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