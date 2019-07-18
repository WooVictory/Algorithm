package samsung;

import java.io.*;

/**
 * created by victory_woo on 18/07/2019
 * 구슬 탈출2
 * DFS 사용.
 */
public class sw13460_other {
    private static final String SPACE = " ";
    private static int answer = 999;
    private static int[][] map;
    private static Position red, blue;

    // 상하좌우 탐색방향.
    // 대신, 상하좌우 순서는 아님.
    private static Position[] direction = {
            new Position(-1, 0),
            new Position(0, 1),
            new Position(1, 0),
            new Position(0, -1)
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(SPACE);
        int n = parse(in[0]);
        int m = parse(in[1]);

        // 초기화.
        map = new int[n + 1][m + 1];

        // 입력.
        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                String box = Character.toString(s.charAt(j - 1));
                switch (box) {
                    case "#":
                        map[i][j] = 1;
                        break;
                    case "R":
                        red = new Position(i, j);
                        break;
                    case "B":
                        blue = new Position(i, j);
                        break;
                    case "O":
                        map[i][j] = 2;
                        break;
                }
            }
        }

        dfs(red, blue, 1);
        if (answer == 999) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();

    }

    private static void dfs(Position red, Position blue, int count) {
        // 10번만에 못찾으면 실패.
        if (count > 10)
            return;

        for (int i = 0; i < 4; i++) {
            // 빨간 구슬과 파란 구슬 모두 이동.
            // 원래 값 유지를 위해서 copy 한다. -> deepCopy
            Position nextRed = tilting(red, direction[i]);
            Position nextBlue = tilting(blue, direction[i]);

            // 탈출 처리 부분.
            // 빨간 구슬만 탈출하고, 파란 구슬은 탈출하지 못한 경우
            // 정답을 찾았다.
            if (nextRed.isEscape() && !nextBlue.isEscape()) {
                answer = Math.min(answer, count);
                return;
            } else if (nextRed.isEscape() && nextBlue.isEscape()) {
                // 빨간 구슬, 파란 구슬이 동시에 탈출하면 실패다.
                // 다시 탐색해서 찾아야 한다.
                continue;
            } else if (nextBlue.isEscape()) {
                // 파란 구슬이 먼저 탈출해도 실패다.
                // 다시 탐색해야 한다.
                continue;
            }

            // 겹쳤을 경우 처리 부분.
            // 빨간 구슬과 파란 구슬이 겹치는 경우
            // 더 많이 움직인 구슬의 위치를 조정한다.
            if (nextRed.isSame(nextBlue)) {
                if (nextRed.move < nextBlue.move) {
                    overlap(nextBlue, i);
                } else {
                    overlap(nextRed, i);
                }
            }

            dfs(nextRed, nextBlue, count + 1);
        }
    }

    // 한쪽 방향으로 기울였을 때 가게되는 최종 점을 반환한다.
    private static Position tilting(Position position, Position direction) {
        // 원래 객체의 값은 유지되어야 하기 때문에 새로 copy 한다.
        Position p = new Position(position.n, position.m);

        while (true) {
            // 구슬이 갈 수 있는 곳이라면.
            // 즉, 벽도 아니고 구멍도 아닌 빈 곳인 경우.
            int nextN = p.n + direction.n;
            int nextM = p.m + direction.m;
            if (map[nextN][nextM] == 0) {
                p.add(direction);
            } else if (map[nextN][nextM] == 2) {
                // 구멍일 경우, (-1,-1)을 가진 Position 객체를 반환한다.
                return new Position(-1, -1);
            } else {
                // 벽이라면 탈출한다.
                // 그만 이동.
                break;
            }
        }
        return p;
    }

    /**
     * 겹쳤을 때, 처리하는 부분(진행 방향의 반대 방향으로 이동)
     *
     * @param p         : 이동할 공의 위치
     * @param direction : 진행 방향
     */
    private static void overlap(Position p, int direction) {
        switch (direction) {
            case 0:
                p.n += 1;
                break;
            case 1:
                p.m -= 1;
                break;
            case 2:
                p.n -= 1;
                break;
            case 3:
                p.m += 1;
                break;
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Position {
        int n;
        int m;
        int move = 0;
        // 한 턴에 몇칸 움직였는지.

        Position(int n, int m) {
            this.n = n;
            this.m = m;
        }

        void add(Position position) {
            this.n += position.n;
            this.m += position.m;
            this.move++;

        }

        boolean isEscape() {
            return this.n == -1 && this.m == -1;
        }

        boolean isSame(Position position) {
            return this.n == position.n && this.m == position.m;
        }
    }
}
