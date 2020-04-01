package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/01
 * 로봇 청소기.
 * 다시 풀어봤지만, 그리 어렵지 않았던 문제!
 * 조건을 잘 생각하면서 풀면 된다!
 * 시뮬레이션 문제는 조건을 잘 구현하자!
 */
public class boj14503 {
    private static int n, m, count = 0;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];
        visit = new boolean[n][m];
        in = br.readLine().split(" ");
        int rX = toInt(in[0]), rY = toInt(in[1]), rD = toInt(in[2]);

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        bfs(rX, rY, rD);
        System.out.println(count + 1);
    }

    private static void bfs(int x, int y, int d) {
        LinkedList<Robot> q = new LinkedList<>();
        q.add(new Robot(x, y, d));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Robot cur = q.remove();
            int curX = cur.x, curY = cur.y, curD = cur.d;
            int nx, ny, nd;

            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                curD = (3 + curD) % 4;
                nx = curX + dx[curD];
                ny = curY + dy[curD];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 왼쪽으로 회전하고 청소하게 되면 그 지점을 현재 좌표로 삼고 처음부터 과정을 반복해야 한다.
                // 따라서 청소한 지점을 큐에 넣고, 반복문을 빠져 나와서 다시 큐에서 빼고, 해당 지점의 왼쪽 방향부터 검사하기 위해 break 한다.
                if (!visit[nx][ny] && map[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    q.add(new Robot(nx, ny, curD));
                    count++;
                    flag = false;
                    break; // 꼭 해줘야 한다. 안하면 안된다. (조건을 만족하도록 구현하기 위해)
                }
            }

            // flag 값이 바뀌지 않았다는 것은 네 방향을 다 돌면서 확인했을 때, 벽이어서 청소하지 못했을 때를 의미한다.
            // 혹은 이미 청소가 완료되었거나!
            if (flag) {
                nd = (2 + curD) % 4;
                nx = curX + dx[nd];
                ny = curY + dy[nd];

                // 후진할 방향과 좌표를 구하고 후진할 곳이 벽이 아니면 큐에 넣어준다.
                if (map[nx][ny] != 1) q.add(new Robot(nx, ny, curD));
            }
        }
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
