package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 17/07/2019
 * 구슬 탈출2
 */
public class sw13460 {
    private static int n, m, rx, ry, bx, by, result;
    private static char[][] a;
    private static boolean[][][][] visited;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        a = new char[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1][n + 1][m + 1];

        // 입력.
        // 빨간 공과 파란 공의 위치를 저장한다.
        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                a[i][j] = s.charAt(j - 1);
                switch (a[i][j]) {
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
            Node node = q.remove();
            int currentRedX, currentRedY, currentBlueX, currentBlueY;
            currentRedX = node.redX;
            currentRedY = node.redY;
            currentBlueX = node.blueX;
            currentBlueY = node.blueY;

            // 10번 이하로 빨간 공을 빼내야 한다.
            if (node.count > 10) {
                result = -1;
                continue;
            }

            // 파란 공이 구멍에 도착하면 실패.O
            if (a[currentBlueX][currentBlueY] == 'O')
                continue;

            // 빨간 공이 구멍에 도착했을 때, 몇번 걸렸는지 반환.
            if (a[currentRedX][currentRedY] == 'O') {
                result = node.count;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextRedX = currentRedX, nextRedY = currentRedY;

                // 빨간 구슬을 상하좌우 끝까지 이동.
                while (true) {
                    // 다음 지점이 벽이 아니고, 구멍이 아닐 경우.
                    if (a[nextRedX][nextRedY] != '#' && a[nextRedX][nextRedY] != 'O') {
                        nextRedX += dx[i];
                        nextRedY += dy[i];
                    } else {
                        if (a[nextRedX][nextRedY] == '#') {
                            // 다음 지점이 벽일 경우, 한칸 후진한다.
                            // 위에서 이미 벽인 지점까지 이동했기 때문이다.
                            nextRedX -= dx[i];
                            nextRedY -= dy[i];
                        }
                        break;
                    }
                }

                // 파란 구슬 상하좌우 끝까지 이동.
                int nextBlueX = currentBlueX, nextBlueY = currentBlueY;

                while (true) {
                    // 다음 지점이 벽이 아니고, 구멍이 아닐 경우.
                    if (a[nextBlueX][nextBlueY] != '#' && a[nextBlueX][nextBlueY] != 'O') {
                        nextBlueX += dx[i];
                        nextBlueY += dy[i];
                    } else {
                        if (a[nextBlueX][nextBlueY] == '#') {
                            // 다음 지점이 벽일 경우, 한 칸 후진한다.
                            nextBlueX -= dx[i];
                            nextBlueY -= dy[i];
                        }
                        break;
                    }
                }

                // 빨간 구슬과 파란 구슬의 위치가 같은데
                // 구멍에 도착한게 아닐 때.
                if (nextRedX == nextBlueX && nextRedY == nextBlueY) {
                    if (a[nextRedX][nextRedY] != 'O') {
                        int redCost = Math.abs(nextRedX - currentRedX) + Math.abs(nextRedY - currentRedY);
                        int blueCost = Math.abs(nextBlueX - currentBlueX) + Math.abs(nextBlueY - currentBlueY);

                        // 빨간 구슬과 파란 구슬 중 누가 더 많이 움직였는지 계산해서
                        // 많이 움직인 구슬은 한 칸 뒤로 이동한다.
                        if (redCost > blueCost) {
                            nextRedX -= dx[i];
                            nextRedY -= dy[i];
                        } else {
                            nextBlueX -= dx[i];
                            nextBlueY -= dy[i];
                        }
                    }
                }

                if (!visited[nextRedX][nextRedY][nextBlueX][nextBlueY]) {
                    visited[nextRedX][nextRedY][nextBlueX][nextBlueY] = true;
                    q.add(new Node(nextRedX, nextRedY, nextBlueX, nextBlueY, node.count + 1));
                }
            }
        }

        result = -1;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    // 빨간 공과 파란 공 모두 이동시켜야 하므로 둘의 좌표를 관리해야 한다.
    static class Node {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;

        Node(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }
}