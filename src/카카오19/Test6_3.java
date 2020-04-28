package 카카오19;

/**
 * created by victory_woo on 2020/04/28
 * 카카오 19 기출.
 * 블록 게임.
 */
public class Test6_3 {
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0},
                {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}};
        System.out.println(solution(board));
    }

    private static int[][] map;
    private static int n;

    public static int solution(int[][] board) {
        map = board;
        n = board.length;
        int count = -1;
        int answer = 0;

        // 지울 수 있는 블록이 있는 경우, count 값을 증가시킨다.
        // 이 count 값을 answer 에 누적한다.
        // 지울 수 있는 블록이 없는 경우, while 루프를 빠져나온다.
        while (count != 0) {
            count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (isRange(i, j) && check(i, j, 2, 3)) count++;
                    else if (isRange(j, i) && check(i, j, 3, 2)) count++;
                }
            }

            answer += count;
        }

        return answer;
    }

    // (row, col) 부터 2x3, 3x2 범위 안에 검은 블록을 놓음으로 인해서 해당 블록을 지울 수 있는지 확인한다.

    private static boolean check(int row, int col, int height, int width) {
        int emptyCount = 0;
        int preValue = -1;

        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                if (map[r][c] == 0) {
                    if (!canFill(r, c)) return false;
                    if (++emptyCount > 2) return false;

                } else {
                    if (preValue != -1 && preValue != map[r][c]) return false;
                    preValue = map[r][c];
                }
            }
        }

        erase(row, col, height, width);
        return true;
    }

    private static boolean isRange(int x, int y) {
        return x <= n - 2 && y <= n - 3;
    }

    // (r,c) 좌표를 기준으로 0부터 r 위의 좌표까지 모두 빈 공간이어야 검은 블록을 떨어트릴 수 있다.
    // 그렇지 않다면 검은 블록을 떨어트릴 수 없다.
    private static boolean canFill(int row, int col) {
        for (int r = 0; r < row; r++) {
            if (map[r][col] != 0) return false;
        }
        return true;
    }

    // 블록을 지울 수 있는 경우, 해당 블록을 0으로 초기화함으로써 지운다.
    private static void erase(int row, int col, int height, int width) {
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                map[r][c] = 0;
            }
        }
    }
}
