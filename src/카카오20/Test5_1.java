package 카카오20;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/23
 * 카카오 20 기출.
 * 블록 이동하기.
 */
public class Test5_1 {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    // 종료 조건.

    public static int solution(int[][] board) {
        return bfs(board);
    }

    private static int n;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    // 우,하,좌,상.
    private static int[] rdx = {-1, 1, 1, -1};
    private static int[] rdy = {1, 1, -1, -1};
    // 대각선 방향.

    private static int bfs(int[][] board) {
        n = board.length;
        LinkedList<Robot> q = new LinkedList<>();
        boolean[][][] visit = new boolean[n][n][4]; // 해당 좌표에서 방향에 상,하,좌,우 방향에 따른 방문여부를 구분하기 위함.
        q.add(new Robot(0, 0, 0, 0));
        visit[0][0][0] = true;

        int x, y, dir, time, ox, oy;
        int nx, ny, ndir, nox, noy;
        int rx, ry; // 대각선 방향.

        while (!q.isEmpty()) {
            Robot robot = q.remove();
            x = robot.x;
            y = robot.y;
            dir = robot.dir;
            time = robot.time;
            ox = robot.getOtherX();
            oy = robot.getOtherY();
            // 로봇의 한쪽을 나타내기 위한 좌표. 방향을 이용해서 표시한다.

            // (x,y) 혹은 (ox,oy)가 도착 지점에 도착하면 time 을 반환하면서 종료한다.
            if (isFinish(x, y) || isFinish(ox, oy)) return time;

            // 로봇의 이동을 처리한다.
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                nox = ox + dx[i];
                noy = oy + dy[i];

                // 1. 범위를 벗어나는지 확인.
                if (!isRange(nx, ny) || !isRange(nox, noy)) continue;
                // 2. 벽인지 확인.
                if (board[nx][ny] == 1 || board[nox][noy] == 1) continue;
                // 3. 방문한 적이 있는지 확인.
                if (visit[nx][ny][dir]) continue;

                // 방문 여부를 체크하고 큐에 넣는다.
                // 이때는, (nx,ny)만 넣어주면 된다. 이유는 dir 방향을 통해서 (ox,oy) 값을 얻을 수 있기 때문이다.
                visit[nx][ny][dir] = true;
                q.add(new Robot(nx, ny, dir, time + 1));
            }

            // 로봇의 회전을 처리한다.
            // (x,y)를 기준으로 90도 회전한다.
            // x,y를 기준으로 ox,oy 값이 변하게 되는 것이므로 nox, noy 를 사용한다.
            for (int i = 1; i < 4; i += 2) {
                ndir = (dir + i) % 4;
                nox = x + dx[ndir];
                noy = y + dy[ndir];

                // 대각선 방향을 구하기 위해서 시계 방향인 경우에는 다음 방향으로 대각선 방향을 구한다.
                // 반 시계 방향인 경우에는 현재 방향으로 대각선 방향을 구한다.
                int tempDir = (i == 1) ? ndir : dir;

                rx = x + rdx[tempDir];
                ry = y + rdy[tempDir];

                if (!isRange(nox, noy) || !isRange(rx, ry)) continue;
                if (board[nox][noy] == 1 || board[rx][ry] == 1) continue;
                if (visit[x][y][ndir]) continue;

                // 현재 좌표를 기준으로 nox, noy 값을 구한 것이기 때문에 현재 좌표인 (x,y)에서 ndir 이라는 방향 값만 함께 저장한다.
                // ndir 방향을 알면 nox, noy 값을 구할 수 있다.
                visit[x][y][ndir] = true;
                q.add(new Robot(x, y, ndir, time + 1));
            }

            dir = (dir + 2) % 4; // 반대 방향으로 뒤집는다.
            // (ox,oy)를 기준으로 90도 회전한다.
            for (int i = 1; i < 4; i += 2) {
                ndir = (dir + i) % 4;
                nx = ox + dx[ndir];
                ny = oy + dy[ndir];

                int tempDir = (i == 1) ? ndir : dir;

                rx = ox + rdx[tempDir];
                ry = oy + rdy[tempDir];

                // 다시 방향을 원래대로 돌려놓는다.
                // 왜 이런 처리를 하는거지..??
                ndir = (ndir + 2) % 4;
                if (!isRange(nx, ny) || !isRange(rx, ry)) continue;
                if (board[nx][ny] == 1 || board[rx][ry] == 1) continue;
                if (visit[nx][ny][ndir]) continue;
                // 큐에 저장할 값은 원래의 x,y 값에 대한 좌표 정보만 저장할 것이기 때문.
                // 방향을 원래대로 돌려놓는 이유는 결국 로봇이 회전할 수 있는 네 방향은 모두 1초 안에 이동할 수 있는 것이기 때문이다.
                // 구현을 할 때는 (x,y)를 기준으로 시계 방향, 반 시계 방향으로 돌리는 것을 따로
                // (ox,oy)를 기준으로 시계 방향, 반 시계 방향으로 돌리는 것을 따로 처리해서 1초 안에 해결할 수 있도록 한 것일 뿐.
                // 실제로는 나눠서 동작하는 것이 아닌 한 번에 동작할 수 있는 것이다. 따라서 원래의 방향으로 돌려 놓는 것이 맞다.

                visit[nx][ny][ndir] = true;
                q.add(new Robot(nx, ny, ndir, time + 1));
            }
        }

        return -1;
    }

    // 종료 조건을 확인한다.
    private static boolean isFinish(int x, int y) {
        return (x == n - 1) && (y == n - 1);
    }

    // 범위를 확인한다.
    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class Robot {
        int x;
        int y;
        int dir; // 방향.
        int time; // 걸린 시간.

        Robot(int x, int y, int dir, int time) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
        }

        // 갖고 있는 방향으로 로봇의 다른 부분 좌표를 구한다.
        int getOtherX() {
            return x + dx[dir];
        }

        int getOtherY() {
            return y + dy[dir];
        }
    }
}
