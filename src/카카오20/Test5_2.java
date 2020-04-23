package 카카오20;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/23
 * 카카오 20 기출.
 * 블록 이동하기.
 * bfs() 탐색을 이용한 극악의 난이도.
 */
public class Test5_2 {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    // 우,하,좌,상 방향.
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int[] rdx = {-1, 1, 1, -1};
    private static int[] rdy = {1, 1, -1, -1};
    private static int n;

    public static int solution(int[][] board) {
        return bfs(board);
    }

    private static int bfs(int[][] board) {
        n = board.length;

        LinkedList<Robot> q = new LinkedList<>();
        boolean[][][] visit = new boolean[n][n][4]; // 방향에 따른 (x,y) 좌표의 방문 여부를 저장한다.
        q.add(new Robot(0, 0, 0, 0)); // 로봇의 처음 위치를 큐에 넣는다. 방향은 오른쪽이다.
        visit[0][0][0] = true;

        // (x,y)를 로봇의 왼쪽 부분이라고 가정.
        // 방문 여부를 체크하고 큐에 넣는 것은 로봇의 왼쪽!
        int x, y, dir, time, ox, oy; // 로봇의 한 부분인 (x,y) 좌표와 다른 한 부분인 (ox,oy)
        int nx, ny, ndir, nox, noy; // 다음 좌표.
        int rx, ry; // 대각선 방향을 관리하기 위함.

        while (!q.isEmpty()) {
            Robot robot = q.remove();
            x = robot.x;
            y = robot.y;
            dir = robot.dir;
            time = robot.time;
            ox = robot.getOtherX();
            oy = robot.getOtherY();

            // 로봇의 어느 부분이라도 도착지점에 도착하면 걸린 시간을 반환하며 종료한다.
            if (isFinish(x, y) || isFinish(ox, oy)) return time;

            // 1. 로봇이 이동한다.
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                nox = ox + dx[i];
                noy = oy + dy[i];

                // 1. 범위를 벗어난다면 건너뛴다.
                if (!isRange(nx, ny) || !isRange(nox, noy)) continue;
                // 2. 벽인지 확인한다.
                if (board[nx][ny] == 1 || board[nox][noy] == 1) continue;
                // 3. 방문한 적이 있는지 확인한다.
                if (visit[nx][ny][dir]) continue;

                visit[nx][ny][dir] = true;
                q.add(new Robot(nx, ny, dir, time + 1));
            }

            // 2. 로봇을 회전시킨다.
            // (x,y)를 기준으로 회전한다.
            for (int i = 1; i < 4; i += 2) {
                ndir = (dir + i) % 4;
                nox = x + dx[ndir];
                noy = y + dy[ndir];

                // 시계 방향이라면, 대각선은 다음 방향의 인덱스로 구한다.
                // 반 시계 방향이라면, 대각선은 현재 방향의 인덱스로 구한다.
                int tempDir = (i == 1) ? ndir : dir;
                rx = x + rdx[tempDir];
                ry = y + rdy[tempDir];

                // 조건을 검사한다.
                if (!isRange(nox, noy) || !isRange(rx, ry)) continue;
                if (board[nox][noy] == 1 || board[rx][ry] == 1) continue;
                if (visit[x][y][ndir]) continue;

                // 조건 검사를 모두 통과하면 방문 여부를 체크하고 큐에 넣는다.
                // 로봇의 (x,y) 좌표의 ndir 방향을 가진 채로 큐에 넣는다.
                // dir 방향만 알면 로봇의 다른 부분의 좌표를 구할 수 있기 때문!
                visit[x][y][ndir] = true;
                q.add(new Robot(x, y, ndir, time + 1));
            }

            dir = (dir + 2) % 4; // 방향을 반대로 뒤집는다.
            // (ox,oy)를 기준으로 90도 회전한다.
            for (int i = 1; i < 4; i += 2) {
                ndir = (dir + i) % 4;
                nx = ox + dx[ndir];
                ny = oy + dy[ndir];

                int tempDir = (i == 1) ? ndir : dir;
                rx = ox + rdx[tempDir];
                ry = oy + rdy[tempDir];

                // (ox,oy)를 기준으로 회전하게 되면 사실상, 로봇의 왼쪽 부분이었던 (x,y)가 이동하게 된다.
                // 하나의 예로 보자면, (x,y)를 나타내는 nx,ny는 (-1,0)이 되고, ox,oy는 (0,1)이 된다.
                // 우리는 로봇의 왼쪽 부분만 저장하기로 했고, 여기서는 왼쪽 부분이 위쪽 부분이 되었다. 따라서 위쪽 부분을 저장할 것이다.
                // 따라서 로봇의 위쪽에서 아래쪽으로 바라볼 수 있도록 하기 위해 방향을 반대로 바꾼다.

                // dir = (dir + 2) % 4;를 통해 방향을 반대로 뒤집고 시계 방향으로 회전하면 위로 올라가게 된다.
                // 따라서 ndir = (ndir + 2) % 4를 통해서 위쪽에서 아래로 바라보도록 방향을 설정한다.
                // 이를 통해서 로봇의 위쪽만 알고 있어도 아래쪽은 방향을 통해서 구할 수 있다.
                // 결국, 로봇의 왼쪽과 위쪽만 큐에 저장하고 방문 여부를 확인한다. 어차피 방향을 통해서 로봇의 다른 부분을 구할 수 있기 때문이다.
                ndir = (ndir + 2) % 4;
                if (!isRange(nx, ny) || !isRange(rx, ry)) continue;
                if (board[nx][ny] == 1 || board[rx][ry] == 1) continue;
                if (visit[nx][ny][ndir]) continue;

                visit[nx][ny][ndir] = true;
                q.add(new Robot(nx, ny, ndir, time + 1));
            }
        }
        return -1;
    }

    // 종료 여부를 확인한다.
    private static boolean isFinish(int x, int y) {
        return (x == n - 1) && (y == n - 1);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class Robot {
        int x;
        int y;
        int dir; // 방향.
        int time;

        Robot(int x, int y, int dir, int time) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
        }

        // 방향을 통해서 로봇의 다른 부분의 좌표를 구한다.
        int getOtherX() {
            return x + dx[dir];
        }

        int getOtherY() {
            return y + dy[dir];
        }
    }
}
