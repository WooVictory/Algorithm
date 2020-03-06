package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/06
 */
public class sw13460_other2 {
    private static int answer = 999;
    private static int[][] map;
    private static Position red, blue;
    private static Position[] direction = {
            new Position(0, 1),
            new Position(0, -1),
            new Position(-1, 0),
            new Position(1, 0)
    };
    // 상,하,좌,우 순서.

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
                switch (c) {
                    // 'R', 'B'에 대해서 red, blue 객체를 초기화한다.
                    case 'R':
                        red = new Position(i, j);
                        break;
                    case 'B':
                        blue = new Position(i, j);
                        break;
                    case '#':
                        // 벽은 1로 표시.
                        map[i][j] = 1;
                        break;
                    case 'O':
                        // 구멍은 2로 표시.
                        map[i][j] = 2;
                        break;
                    default:
                        // 빈 곳은 0으로 표시.
                        map[i][j] = 0;
                        break;
                }
            }
        }

        dfs(red, blue, 1);
        System.out.println(answer == 999 ? -1 : answer);
    }

    private static void dfs(Position red, Position blue, int count) {
        // 최대 10번 탐색하기 때문에 10번이 넘어가면 종료한다.
        if (count > 10) {
            return;
        }

        // 네 방향에 대해서 탐색한다.
        for (int i = 0; i < 4; i++) {
            // tilt()를 통해서 해당 방향으로 빨간 구슬과 파란 구슬을 이동시킨다.(굴린다.)
            // 한 방향에 대해서 이동시킬 수 있는데까지 이동시킨다.
            Position nextRed = tilt(red, direction[i]);
            Position nextBlue = tilt(blue, direction[i]);

            // 빨간 구슬은 구멍에 빠지고 파란 구슬은 구멍에 빠지지 않은 경우는 정답을 찾은 경우이다.
            // answer 에 횟수인 count 와 비교해 저장하고 종료한다.
            if (nextRed.isEscape() && !nextBlue.isEscape()) {
                // 정답을 찾은 경우.
                answer = Math.min(answer, count);
                return;
            } else if (nextRed.isEscape() && nextBlue.isEscape()) {
                // 두 구슬이 모두 탈출하면 실패이기 때문에 다른 방향에 대해서 탐색한다.
                continue;
            } else if (nextBlue.isEscape()) {
                // 파란 구슬만 구멍에 빠져 탈출해도 실패이므로 다른 방향에 대해서 탐색한다.
                continue;
            }

            // 빨간 구슬과 파란 구슬이 같은 위치에 존재하는 경우이다.
            // 즉 두 구슬이 같은 칸에 위치할 수 없기 때문에 더 많이 움직인 구슬을 이동 방향의 반대 방향으로 한칸 움직인다.
            if (nextRed.isSame(nextBlue)) {
                if (nextRed.moveCount < nextBlue.moveCount) {
                    overlap(nextBlue, i);
                } else {
                    overlap(nextRed, i);
                }
            }

            // nextRed, nextBlue 그리고 count+1에 대해 다음 dfs 탐색을 진행한다.
            // 위에를 거쳐왔기 때문에 한번 이동했으므로 count 를 증가시켜준다.
            dfs(nextRed, nextBlue, count + 1);
        }

    }

    // dir 방향으로 구슬을 이동시킨다.
    private static Position tilt(Position position, Position direction) {
        // 원래 객체의 값을 유지되어야 하기 때문에 복사한다.
        // deepCopy
        Position copy = new Position(position.x, position.y);

        while (true) {
            int nextX = copy.x + direction.x;
            int nextY = copy.y + direction.y;

            // 빈 곳이면 구슬의 좌표를 add()를 통해 dir 방향 쪽으로 움직여 준다.
            if (map[nextX][nextY] == 0) {
                copy.add(direction);
            } else if (map[nextX][nextY] == 1) { // 벽을 만나는 경우에는 탈출.
                break;
            } else if (map[nextX][nextY] == 2) { // 구멍에 빠지는 경우에는 (-1,-1) 객체를 반환한다.
                return new Position(-1, -1);
            }
        }

        // 이동된 구슬 객체를 반환한다.
        return copy;
    }

    // 0 : 상
    // 1 : 하
    // 2 : 좌
    // 3 : 우
    // 각 방향의 반대 방향으로 한칸 이동시킨다.
    // position : 이동할 공 객체
    // dir : 이동할 방향.
    private static void overlap(Position position, int dir) {
        switch (dir) {
            case 0:
                position.y -= 1;
                break;
            case 1:
                position.y += 1;
                break;
            case 2:
                position.x += 1;
                break;
            case 3:
                position.x -= 1;
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
        // 구슬이 얼마나 움직였는지를 저장하는 변수.

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // dir 의 x,y 만큼 객체를 이동시킨다.
        // 이동할 때마다 moveCount 를 증가시킨다.
        void add(Position dir) {
            this.x += dir.x;
            this.y += dir.y;
            this.moveCount++;
        }

        // 두 구슬이 같은 위치에 있는지 여부를 반환한다.
        boolean isSame(Position other) {
            return this.x == other.x && this.y == other.y;
        }

        // 구멍에 빠져서 탈출했는지 여부를 반환한다.
        boolean isEscape() {
            return this.x == -1 && this.y == -1;
        }
    }
}
