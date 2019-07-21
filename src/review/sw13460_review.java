package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 21/07/2019
 * SW 역량 기출 구슬 탈출2 review.
 */
public class sw13460_review {
    private static int n, m, result;
    private static int rx, ry, bx, by;
    private static char[][] map;
    private static boolean[][][][] visited;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = parse(in[0]);
        m = parse(in[1]);

        map = new char[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                switch (map[i][j]) {
                    case 'R':
                        rx = i;
                        ry = j;
                        break;
                    case 'B':
                        bx = i;
                        by = j;
                        break;
                }
            }
        }

        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!q.isEmpty()) {
            Node current = q.remove();
            int redX, redY, blueX, blueY;
            redX = current.rx;
            redY = current.ry;
            blueX = current.bx;
            blueY = current.by;


            // 10회 이하로 정답을 찾지 못하면 종료.
            if (current.count > 10) {
                result = -1;
                return;
            }

            // 파란 구슬 구멍에 도착하면 실패. 다시 탐색해야 함.
            if (map[blueX][blueY] == 'O') {
                continue;
            }

            // 빨간 구슬이 구멍에 도착하면 성공. 정답 저장 후 출력.
            if (map[redX][redY] == 'O') {
                result = current.count;
                return;
            }

            for (int i = 0; i < 4; i++) {
                // 빨간 구슬에 대해 상하좌우 끝까지 이동.
                int nextRedX = redX, nextRedY = redY;

                while (true) {
                    // 벽이 아니고 구멍이 아니면 이동하려는 방향으로 계속 이동한다.
                    if (map[nextRedX][nextRedY] != '#' && map[nextRedX][nextRedY] != 'O') {
                        nextRedX += dx[i];
                        nextRedY += dy[i];
                    } else {
                        if (map[nextRedX][nextRedY] == '#') {
                            // 그리고 벽을 만났을 때는 벽까지 이동한 것이므로 벽 이전으로 한칸 후진한다.
                            nextRedX -= dx[i];
                            nextRedY -= dy[i];
                        }
                        break;
                    }
                }

                int nextBlueX = blueX, nextBlueY = blueY;

                while (true) {
                    if (map[nextBlueX][nextBlueY] != '#' && map[nextBlueX][nextBlueY] != 'O') {
                        nextBlueX += dx[i];
                        nextBlueY += dy[i];
                    } else {
                        if (map[nextBlueX][nextBlueY] == '#') {
                            nextBlueX -= dx[i];
                            nextBlueY -= dy[i];
                        }
                        break;
                    }
                }

                // 빨간 구슬과 파란 구슬의 위치가 같고 구멍은 아닐 때,
                // 더 많이 이동한 구슬의 위치를 조정한다.
                if (nextRedX == nextBlueX && nextRedY == nextBlueY) {
                    if (map[nextRedX][nextRedY] != 'O') {
                        int redCost = Math.abs(nextRedX - redX) + Math.abs(nextRedY - redY);
                        int blueCost = Math.abs(nextBlueX - blueX) + Math.abs(nextBlueY - blueY);

                        if (redCost > blueCost) {
                            nextRedX -= dx[i];
                            nextRedY -= dy[i];
                        } else {
                            nextBlueX -= dx[i];
                            nextBlueY -= dy[i];
                        }
                    }
                }

                // 위에서 구한 빨간 구슬과 파란 구슬의 다음 정점에 대해 방문한 적이 없다면
                // 큐에 넣고 방문했음을 표시한다.
                if (!visited[nextRedX][nextRedY][nextBlueX][nextBlueY]) {
                    visited[nextRedX][nextRedY][nextBlueX][nextBlueY] = true;
                    q.add(new Node(nextRedX, nextRedY, nextBlueX, nextBlueY, current.count + 1));
                }
            }
        }
        result = -1;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
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