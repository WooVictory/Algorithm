package 카카오19;

/**
 * created by victory_woo on 2020/04/27
 * 카카오 19 기출.
 * 블록 게임.
 */
public class Test6 {
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

    private static int[][] Board;

    public static int solution(int[][] board) {
        Board = board;
        int n = board.length;
        int answer = 0;

        // (0,0) ~ 끝까지 돌면서 지울 수 있는 영역을
        // 가로, 세로 직사각형으로 확인한다.
        int count = -1; // 지울 수 있는 블록을 카운팅할 변수.

        while (count != 0) {
            count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i <= n - 2 && j <= n - 3 && find(i, j, 2, 3)) count++;
                    else if (i <= n - 3 && j <= n - 2 && find(i, j, 3, 2)) count++;
                }
            }

            answer += count;

        }
        /*do {

        } while (count != 0);
        // 지운게 없다고 하면 종료.(0)
        // 지운 블록이 있다고 하면 0이 아님.*/

        return answer;
    }

    private static boolean find(int row, int col, int height, int width) {
        int emptyCount = 0; // 빈 공간을 카운트함.
        int lastValue = -1; // 이 값과 같아야지 동일한 블록이므로 지울 수 있고, 다르다면 동일하지 않기 때문에 지울 수 없다.

        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                if (Board[r][c] == 0) {
                    // 빈 공간이면서 1x1의 검정 블록으로 채울 수 있는 공간인지 봐야 함.
                    if (!canFill(r, c)) {
                        // 지금 위치의 이 공간을 채울 수 있는지 확인한다.
                        // 만약, canFill() 함수가 false 값을 반환하면, 우리가 지울 수 없는 영역이다.
                        return false;
                    }

                    if (++emptyCount > 2) return false;
                    // 왜냐하면 문제에서 주어진 도형에 대해서 채울 수 있는 검은 블록의 갯수는 2개뿐인데,
                    // 더 많이 채운다는 것은 이 공간 자체를 블록으로 채울 수 없다는 걸 의미한다.
                } else {
                    // 현재 위치가 0이 아니고 어떤 값을 갖는다고 하면
                    // 블록이 채워져 있는 경우(빈 공간이 아님)

                    // lastValue 가 -1이 아니고 현재 위치의 값과 다르다면 다른 블록이므로 false 반환.
                    if (lastValue != -1 && lastValue != Board[r][c]) return false;

                    // 값이 있는 영여깅 4개인지 카운팅할 필요 없음.
                    // 우리가 확인한 영역이 총 6개가 되고, empty 값이 2개가 넘어가면 return 하기 때문에
                    // 나머지 4개에 대해서만 확인된다.
                    lastValue = Board[r][c];
                }
            }
        }

        // 0으로 해당 영역을 지운다.
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                Board[r][c] = 0;
            }
        }

        // return false 없이 끝났다고 하면 결국, emptyCount 가 2개이고,
        // 어떤 값으로 채워진 영역이 4개라는 것이다.
        return true;
    }

    // 0부터 시작해서 해당 좌표의 바로 위까지 비어있는 공간인지 확인한다.
    // 하나라도 0이 아닌 곳이 있다는 것은 위가 비어있지 않다는 것이므로 이는 false 반환.
    // return false 없이 끝나면 이는 위가 비어있다는 것으로 true 반환.
    private static boolean canFill(int row, int col) {
        for (int r = 0; r < row; r++) {
            if (Board[r][col] != 0) return false;
        }
        return true;
    }

    private static void print(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
