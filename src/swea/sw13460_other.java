package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/05
 * 구슬 탈출2
 * dfs.
 */
public class sw13460_other {
    private static int[][] map;
    private static Position red, blue;
    private static int answer = 999;

    // 상,하,좌,우 방향에 대해서 탐색할 배열을 객체 배열로 표현한다.
    // position 으로 함으로써 통일성을 주었다.
    // 그냥 배열로 해도 되지만, 통일성으로 이렇게 하였다.
    private static Position[] dir = {
            new Position(-1, 0),
            new Position(0, 1),
            new Position(1, 0),
            new Position(0, -1)
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int m = toInt(in[1]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);

                // 빨간 구슬과 파란 구슬에 대해 객체를 초기화 해준다.
                // 벽은 1로, 빈 곳은 0으로, 구멍은 2로 바꾸어주었다.
                switch (c) {
                    case 'R':
                        red = new Position(i, j);
                        break;
                    case 'B':
                        blue = new Position(i, j);
                        break;
                    case '#':
                        map[i][j] = 1;
                        break;
                    case 'O':
                        map[i][j] = 2;
                        break;
                    default:
                        map[i][j] = 0;
                        break;

                }
            }
        }

        dfs(red, blue, 1);
        System.out.println(answer == 999 ? -1 : answer);
    }

    private static void dfs(Position red, Position blue, int count) {
        // 10번 이하로 빨간 구슬을 구멍을 통해 탈출시키지 못하면 종료한다.
        if (count > 10) {
            return;
        }

        // 상,하,좌,우 네 방향에 대해서 검사한다.
        for (int i = 0; i < 4; i++) {
            // 빨간 구슬과 파란 구슬을 그 방향에 대해서 이동할 수 있는데 까지 이동시킨다.
            // 원래 값 유지를 위해서 copy 한다. -> deepCopy
            Position nextRed = tilt(red, dir[i]);
            Position nextBlue = tilt(blue, dir[i]);

            // 빨간 구슬만 탈출하고 파란 구슬은 탈출하지 못한 경우.
            // 정답을 찾은 경우이다. 결과를 저장하고 종료한다.
            if (nextRed.isEscape() && !nextBlue.isEscape()) {
                answer = Math.min(answer, count);
                return;
            } else if (nextRed.isEscape() && nextBlue.isEscape()) {
                // 빨간 구슬과 파란 구슬 모두 탈출하는 경우.
                // 다른 방향으로 구슬을 이동시켜 본다.
                continue;
            } else if (nextBlue.isEscape()) {
                // 파란 구슬이 탈출하는 경우이다.
                // 실패이므로 돌아가서 다른 방향에 대해서 탐색한다.
                continue;
            }

            // 빨간 구슬과 파란 구슬의 위치가 같은지 검사한다.
            // 위치가 같다면 더 많이 움직인 구슬을 이동 방향의 반대 방향으로 한칸 이동시킨다.
            if (nextRed.isSame(nextBlue)) {
                if (nextRed.moveCount < nextBlue.moveCount) overlap(nextBlue, i);
                else overlap(nextRed, i);
            }

            // dfs 재귀호출을 한다. 위에서 조건에 걸리지 않고 내려왔다면
            // 다음 탐색을 위해 count + 1을 해준다.
            dfs(nextRed, nextBlue, count + 1);
        }
    }

    // dir 방향으로 기울이는 함수이다.
    private static Position tilt(Position og, Position dir) {
        // 원래 객체의 값은 유지되어야 하기 때문에 객체를 복사한다.
        Position copy = new Position(og.x, og.y);

        while (true) {
            // 다음으로 이동할 방향을 구한다.
            int nextX = copy.x + dir.x;
            int nextY = copy.y + dir.y;

            // 빈 곳인 경우. add() 함수를 호출하여 해당 방향으로 이동하여 좌표를 변경한다.
            if (map[nextX][nextY] == 0) {
                copy.add(dir);
            } else if (map[nextX][nextY] == 2) {
                // 구멍인 경우. 탈출했기 때문에 (-1,-1)로 표시한다.
                return new Position(-1, -1);
            } else if (map[nextX][nextY] == 1) {
                // 벽인 경우. 탈출한다.
                break;
            }
        }

        // 이동함으로써 변경된 copy 객체를 리턴한다.
        return copy;
    }

    /**
     * 0 : 왼쪽 방향이다. 즉, 왼쪽의 반대는 오른쪽이므로 x 좌표에서 +1을 해준다.
     * 1 : 위쪽 방향이다. 즉, 위쪽의 반대는 아래이므로 y 좌표에서 -1을 해준다.
     * 2 : 오른쪽 방향이다. 즉, 오른쪽의 반대는 왼쪽이므로 x 좌표에서 -1을 해준다.
     * 3 : 아래쪽 방향이다. 즉, 아래쪽의 반대는 위쪽이므로 y 좌표에서 +1을 해준다.
     *
     * @param position : 이동할 공 객체.
     * @param dir : 이동할 방향.
     *
     * 반대 방향으로 이동시킨다.
     */
    private static void overlap(Position position, int dir) {
        switch (dir) {
            case 0:
                position.x += 1;
                break;
            case 1:
                position.y -= 1;
                break;
            case 2:
                position.x -= 1;
                break;
            case 3:
                position.y += 1;
                break;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Position {
        int x;
        int y;
        int moveCount;
        // 구슬이 얼만큼 이동했는지를 저장한다.

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 방향으로 구슬을 이동시킨다.
        void add(Position dir) {
            this.x += dir.x;
            this.y += dir.y;
            this.moveCount++;
        }

        boolean isEscape() {
            return this.x == -1 && this.y == -1;
        }

        boolean isSame(Position other) {
            return this.x == other.x && this.y == other.y;
        }
    }
}
