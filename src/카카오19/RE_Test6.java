package 카카오19;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 블록 게임.
 */
public class RE_Test6 {
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
    private static int n;

    public static int solution(int[][] board) {
        int answer = 0;
        map = board;
        n = board[0].length;

        int count = -1;
        while (count != 0) {
            count = 0;
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

    // 2x3, 3x2 범위 만큼을 확인한다.
    private static boolean check(int row, int col, int height, int width) {
        int block = -1;
        int emptyCnt = 0;
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                // 해당 공간에 블록이 없는 경우.
                if (map[r][c] == 0) {
                    // 검은 블록을 놓을 수 있는지 확인한다.
                    if (!isPossible(r, c)) return false;
                    // 빈 공간의 개수를 증가시킨다.
                    emptyCnt++;
                    // 빈 공간의 개수가 2개보다 커지면, 이 범위의 블록은 지울 수 없음.
                    if (emptyCnt > 2) return false;
                } else {
                    // block 값이 할당되고, -1이 아니고 확인하는 범위에서 map 값과 다르다면 다른 블록이 있으므로 false 반환.
                    if (block != -1 && block != map[r][c]) return false;

                    // 처음에 block 을 map 의 블록으로 초기화 한다.
                    block = map[r][c];
                }
            }
        }
        // 지워야 함.
        // 여기까지 왔다면, 같은 블록이 4개 존재하고 빈 공간은 2개가 있음.
        // 따라서 검은 블록을 놓을 수 있다.
        // 그러므로 해당 범위의 블록들을 0으로 초기화해서 지워준다.
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                map[r][c] = 0;
            }
        }
        return true;
    }

    // 빈 공간의 위쪽을 탐색하면서 0이 아닌 곳이 있다면 false 를 반환하고 검은 블록을 떨어트릴 수 없다.
    private static boolean isPossible(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (map[i][col] != 0) return false;
        }
        return true;
    }
}
