package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/13
 * 구슬 탈출2.
 * 삼성 기출.'
 * bfs + 시뮬레이션.
 * 초기 값을 -1로 줘야 한다.
 */
public class Problem13460 {
    private static int n, m, result = -1;
    private static char[][] map;
    private static boolean[][][][] visit;
    private static int rx, ry, bx, by;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new char[n][m];
        visit = new boolean[n][m][n][m];


        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (s.charAt(j) == 'R') {
                    rx = i;
                    ry = j;
                }

                if (s.charAt(j) == 'B') {
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
            int currentRedX = current.rx, currentRedY = current.ry;
            int currentBlueX = current.bx, currentBlueY = current.by;
            int currentCount = current.moveCount;

            // 예외 처리.

            // 1. 10번 이하로 움직이지 못하고 그 횟수를 넘어갔기 때문에 -1을 반환.
            if (currentCount > 10) {
                result = -1;
                return;
            }

            // 2. 파란 구슬이 구멍에 빠지면 -1을 반환.
            if (map[currentBlueX][currentBlueY] == 'O') {
                continue;
            }

            // 3. 빨간 구슬이 구멍에 빠지면 성공.
            if (map[currentRedX][currentRedY] == 'O') {
                result = currentCount;
                return;
            }

            // 네 방향에 대해서 구슬을 한 방향으로 이동할 수 없을 때까지 굴린다.
            // 빨간 구슬을 먼저 굴린 다음, 파란 구슬을 굴린다.
            // 예제 테케 5번과 같은 경우를 처리하기 위해서 빨간 구슬을 먼저 처리한다.
            for (int i = 0; i < 4; i++) {
                int nextRedX = currentRedX, nextRedY = currentRedY;

                // 이동할 수 있는 방향으로 이동할 수 없을 때까지 굴린다.
                // 이동할 수 없다는 것은 벽을 만날 때까지 의미한다.
                while (true) {
                    if (map[nextRedX][nextRedY] != '#' && map[nextRedX][nextRedY] != 'O') {
                        nextRedX += dx[i];
                        nextRedY += dy[i];
                    } else {
                        // 한칸을 더 이동하기 때문에 벽까지 가게 된다. 따라서 한칸 뒤로 이동해야 한다.
                        if (map[nextRedX][nextRedY] == '#') {
                            nextRedX -= dx[i];
                            nextRedY -= dy[i];
                        }
                        break;
                    }
                }

                int nextBlueX = currentBlueX, nextBlueY = currentBlueY;
                while (true) {
                    if (map[nextBlueX][nextBlueY] != '#' && map[nextBlueX][nextBlueY] != 'O') {
                        nextBlueX += dx[i];
                        nextBlueY += dy[i];
                    } else {
                        // 한칸을 더 이동하기 때문에 벽까지 가게 된다. 따라서 한칸 뒤로 이동해야 한다.
                        if (map[nextBlueX][nextBlueY] == '#') {
                            nextBlueX -= dx[i];
                            nextBlueY -= dy[i];
                        }
                        break;
                    }
                }

                // 구슬의 위치가 같고, 구멍이 아닌 경우에 더 많이 이동한 구슬을 한칸 뒤로 빼준다.
                // 예제 테케 7번과 같은 경우를 처리하기 위함이다.
                if (nextRedX == nextBlueX && nextRedY == nextBlueY) {
                    if (map[nextRedX][nextRedY] != 'O') {
                        int redCost = getDiff(currentRedX, currentRedY, nextRedX, nextRedY);
                        int blueCost = getDiff(currentBlueX, currentBlueY, nextBlueX, nextBlueY);

                        if (redCost > blueCost) {
                            nextRedX -= dx[i];
                            nextRedY -= dy[i];
                        } else {
                            nextBlueX -= dx[i];
                            nextBlueY -= dy[i];
                        }
                    }
                }

                if (!visit[nextRedX][nextRedY][nextBlueX][nextBlueY]) {
                    visit[nextRedX][nextRedY][nextBlueX][nextBlueY] = true;
                    q.add(new Node(nextRedX, nextRedY, nextBlueX, nextBlueY, currentCount + 1));
                }
            }
        }
    }

    private static int getDiff(int curRedX, int curRedy, int nextRedX, int nextRedY) {
        return Math.abs(curRedX - nextRedX) + Math.abs(curRedy - nextRedY);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int rx;
        int ry;
        int bx;
        int by;
        int moveCount;

        public Node(int rx, int ry, int bx, int by, int moveCount) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.moveCount = moveCount;
        }
    }
}
