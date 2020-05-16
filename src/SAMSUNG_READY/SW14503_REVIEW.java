package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/15
 * 로봇 청소기.
 */
public class SW14503_REVIEW {
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        visit = new boolean[n][m];

        in = br.readLine().split(" ");
        int x = toInt(in[0]), y = toInt(in[1]), d = toInt(in[2]);

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        solve(x, y, d);
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static void solve(int x, int y, int d) {
        int count = 0;
        LinkedList<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, d));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            // 1. 큐에서 빼는
            Robot cur = q.remove();
            int curX = cur.x, curY = cur.y, curD = cur.d;
            int nx, ny, nd;

            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                curD = (curD + 3) % 4; // 왼쪽 방향으로 탐색한다.
                // 왼쪽으로 회전했을 때의 좌표를 구한다.
                nx = curX + dx[curD];
                ny = curY + dy[curD];

                // 구한 다음 좌표가 범위를 벗어나거나 방문한 적이 있는지 확인한다.
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visit[nx][ny]) continue;

                // 다음 좌표가 빈 공간이라면 청소할 수 있다.
                // 여기서는 왼쪽 방향에 청소하지 않은 칸이 존재하면 청소를 한 다음에
                // 회전한 방향을 유지한 채로 큐에 넣고 1번 과정으로 돌아간다.
                if (map[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    map[nx][ny] = 2; // 청소한 공간임을 표시한다.
                    q.add(new Robot(nx, ny, curD));
                    count++; // 청소한 공간의 개수를 카운트한다.

                    // 청소를 했으면 현재 좌표에서 왼쪽으로 탐색하는 걸 멈추고 빠져나온다.
                    // 그래야 큐에 넣은 다음 좌표에 대해서 다시 왼쪽을 탐색하고 이런 과정을 반복할 수 있기 때문이다.
                    flag = false;
                    break;
                }
            }

            // 네 방향 모두 이미 청소되어있거나 벽인 경우, 바라보는 방향을 유지한 채로 후진한다.
            if (flag) {
                // 후진할 방향과 좌표를 구한다.
                // 현재 바라보고 있는 방향을 기준으로 nd 를 구한다.
                nd = (curD + 2) % 4;
                nx = curX + dx[nd];
                ny = curY + dy[nd];

                // 후진하는 곳이 벽이 아니라면 후진한다.
                // 방향은 후진하는 방향 nd 가 아닌 현재 바라보고 있는 방향 curD 를 넣어준다.
                if (map[nx][ny] != 1) {
                    q.add(new Robot(nx, ny, curD));
                    visit[nx][ny] = true;
                }
            }
        }

        // 로봇이 처음 위치도 청소해야 하기 때문에 + 1을 해준다.
        System.out.println(count + 1);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Robot {
        int x;
        int y;
        int d;

        Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
