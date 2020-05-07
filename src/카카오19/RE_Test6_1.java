package 카카오19;

/**
 * created by victory_woo on 2020/05/07
 */
public class RE_Test6_1 {
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
        solution(board);
    }

    private static int[][] map;

    public static int solution(int[][] board) {
        int answer = 0, count = -1;
        map = board;
        int n = board[0].length;

        // 1. 지울 수 있는 블록이 있는지 없는지 확인한다.
        // 한번만 확인하면 안된다. 블록이 겹치거나 어떠한 경우로 인해서 한번에 지울 수 없는 경우가 있기 때문에
        // 루프를 돌면서 지울 수 있는 블록이 없을 때까지 반복해야 한다.
        while (count != 0) {
            count = 0;
            // 2. 2중 for 반복문을 돌면서 2x3, 3x2 범위만큼 확인한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i <= n - 2 && j <= n - 3 && check(i, j, 2, 3)) count++;
                    if (i <= n - 3 && j <= n - 2 && check(i, j, 3, 2)) count++;
                }
            }
            answer += count;
        }
        System.out.println(answer);
        return answer;
    }

    // 2x3, 3x2 범위를 확인해서 검은 블록을 떨어트려 블록을 지울 수 있는지 확인한다.
    private static boolean check(int row, int col, int height, int width) {
        int count = 0, block = -1;
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                // 해당 공간이 빈 곳이라면 여기에 검은 블록을 놓을 수 있는지 확인한다.
                if (map[r][c] == 0) {
                    if (!isPossible(r, c)) return false;

                    // 빈 공간의 개수를 카운트한다.
                    // 빈 공간이 2개 이상이라면 이는 검은 블록을 2개 놓아서 블록을 지울 수 없으므로 false 반환.
                    count++;
                    if (count > 2) return false;
                } else {
                    // 해당 공간에 블록이 있는 경우.
                    // 첫 진입 이후부터 block 이 다른 블록들과 값이 다르다면 이는 다른 블록이 섞여 있음을 의미하므로
                    // false 반환.
                    if (block != -1 && block != map[r][c]) return false;

                    // 초창기에는 블록에 해당 좌표의 블록을 할당하낟.
                    block = map[r][c];
                }
            }
        }

        // 여기까지 왔다는 것은 검은 블록을 떨어트려 블록을 지울 수 있음을 뜻한다.
        // 0을 놓음으로써 지운다.
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                if (map[r][c] != 0) map[r][c] = 0;
            }
        }
        return true;
    }

    // 검은 블록을 놓을 수 있는지 확인한다.
    // 위에서부터 해당 좌표까지 내려오면서 0이 아닌 곳이 있다면 검은 블록을 털어뜨리지 못한다.
    // 모두 0이라면 검은 블록을 떨어트릴 수 있다
    private static boolean isPossible(int row, int col) {
        for (int r = 0; r < row; r++) {
            if (map[r][col] != 0) return false;
        }
        return true;
    }
}
