package programmers;

/**
 * created by victory_woo on 2020/05/26
 * 가장 큰 정사각형.
 */
public class PGM12905 {
    public static void main(String[] args) {
        int[][] board = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        int[][] board2 = {{0, 0, 1, 1}, {1, 1, 1, 1}};
        System.out.println(solution(board2));
        System.out.println(solution(board));

    }

    public static int solution(int[][] board) {
        int up, left, upLeft;
        int row = board.length;
        int col = board[0].length;
        int min;
        int max = Integer.MIN_VALUE;

        // [[1,0],[0,0]] 의 경우 1인 정사각형을 만들 수 있다.
        // 따라서 이 부분을 처리해줘야 테스트 케이스 1번을 틀리지 않고 맞을 수 있게 된다.
        if (row < 2 || col < 2) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 1) max = 1;
                }
            }
            return max * max;
        }

        // DP를 이용해 문제를 푼다.
        // [1][1]부터 시작한다. 현재 값을 기준으로 좌측,상단,좌측상단 값 중 가장 작은 값을 찾는다.
        // 가장 작은 값에 + 1을 한 값을 현재 값으로 바꾼다.
        // 이렇게 계속 반복하면 가장 큰 값이 가장 큰 정사각형의 한 변의 길이가 된다.
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == 1) {
                    up = board[i - 1][j];
                    left = board[i][j - 1];
                    upLeft = board[i - 1][j - 1];

                    // 가장 작은 값을 찾아서 이 값 + 1을 현재 값으로 바꾼다.
                    min = Math.min(up, Math.min(left, upLeft));
                    board[i][j] = min + 1;

                    // 반복문의 횟수를 줄이기 위해서 max 값을 바로 찾는다.
                    if (max < board[i][j]) max = board[i][j];
                }
            }
        }
        // 정사각형의 넓이를 반환한다.
        return max * max;
    }

}