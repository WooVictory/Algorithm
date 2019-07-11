package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 11/07/2019
 * 로봇 청소기.
 * bfs 를 이용한 방법.
 */
public class BOJ14503 {
    private static final String SPACE = " ";
    private static int n, m, answer;
    private static int[][] a;
    private static boolean[][] visited;
    private static Robot startRobot;
    // 북, 동, 남, 서의 각각 왼쪽 방향을 표시하기 위해 배열을 이렇게 구성한다.
    // 서, 북, 동, 남 이 된다.
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);
        n = parse(in[0]);
        m = parse(in[1]);

        // 초기화.
        a = new int[n][m];
        visited = new boolean[n][m];

        String[] num = br.readLine().split(SPACE);
        int start = parse(num[0]);
        int end = parse(num[1]);
        int direction = parse(num[2]);

        // 입력.
        for (int i = 0; i < n; i++) {
            String[] node = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                a[i][j] = parse(node[j]);
            }
        }

        startRobot = new Robot(start, end, direction);
        visited[start][end] = true;
        bfs();

        // 로봇이 위치하던 곳을 더해준 값이 답이 된다.
        System.out.println(answer + 1);
    }

    private static void bfs() {
        LinkedList<Robot> q = new LinkedList<>();
        q.add(startRobot);

        while (!q.isEmpty()) {
            Robot currentRobot = q.remove();
            int currentX = currentRobot.x; // 현재 x 좌표.
            int currentY = currentRobot.y; // 현재 y 좌표.
            int currentD = currentRobot.direction; // 현재 방향.

            boolean flag = false;
            int nextX;
            int nextY;
            int nextD;

            // 네 방향 모두 검사해줘야 한다.
            for (int i = 0; i < 4; i++) {
                currentD = (currentD + 3) % 4; // 다음으로 이동할 방향을 구한다.
                nextX = currentX + dx[currentD]; // 다음 이동할 x 좌표.
                nextY = currentY + dy[currentD]; // 다음 이동할 y 좌표.

                // 다음 이동할 방향이 범위에 맞는지 체크한다.
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;

                // 다음 위치가 방문한 적이 없고, 청소해야 하는 곳이라면 방문한다.
                if (!visited[nextX][nextY] && a[nextX][nextY] == 0) {
                    q.add(new Robot(nextX, nextY, currentD));
                    visited[nextX][nextY] = true;
                    answer++;
                    flag = true;
                    break;
                }
            }

            // 4 방향 모두 청소가 되었거나, 벽일 경우에는 후진해야 한다.
            // 후진을 하기 위해 방향을 계산해본다.
            if (!flag) {
                nextD = (currentD + 2) % 4; // 후진할 방향을 구한다.
                nextX = currentX + dx[nextD];
                nextY = currentY + dy[nextD];

   /*             System.out.println("4방향 모두 청소 혹은 벽일 경우");
                System.out.println("currentX: " + currentX);
                System.out.println("currentY: " + currentY);
                System.out.println("currentD: " + currentD);
                System.out.println("nextX: " + nextX);
                System.out.println("nextY: " + nextY);
                System.out.println("nextD: " + nextD);
                System.out.println("dx[nextX]: "+dx[nextD]);
                System.out.println("dx[nextY]: "+dy[nextD]);*/

                if (a[nextX][nextY] == 0) {
                    q.add(new Robot(nextX, nextY, currentD));
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Robot {
        int x;
        int y;
        int direction;

        Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
