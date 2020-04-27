package 카카오19;

/**
 * created by victory_woo on 2020/04/27
 * 카카오 19 기출.
 * 블록 게임.
 */
public class Test6_re {
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
        int answer = 0;
        map = board;
        n = board.length;
        int count = -1;

        // (0,0) ~ (n-1,n-1)까지 돌면서 지울 수 있는 블록을 찾으면 count 값을 증가시킨다.
        // 그리고 answer 에 count 값을 누적한다.
        // 이 과정을 반복하고, count 는 반복문 안에 제일 윗줄에서 0으로 초기화한다.
        // 즉, count : 지울 수 있는 블록의 갯수.
        // 이 지울 수 있는 블록의 갯수가 0이면 종료한다.(증가하지 않으면!)
        // 또한, 지울 수 있는 블록을 찾은 후에도 다시 반복문을 도는 이유는 어떠한 경우에 의해서 지워지지 못한 블록이 있을 수 있기 때문!
        // 예를 들면, 어느 블록에 바로 아래 있었다던지 하는 등의 이유로!
        while (count != 0) {
            count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 범위를 만족하면서 해당 직사각형 공간에 검은 블록을 놓을 수 있다면 counting 한다.
                    if (isRange(i, j) && check(i, j, 2, 3)) count++;
                    else if (isRange(j, i) && check(i, j, 3, 2)) count++;
                }
            }

            // count 값을 누적한다.
            answer += count;
        }

        return answer;
    }

    // x,y 값이 범위를 벗어나는 경우를 방지한다.
    private static boolean isRange(int x, int y) {
        return x <= n - 2 && y <= n - 3;
    }

    // (row, col)로부터 2x3 혹은 3x2 만큼의 칸을 확인하면서
    // 검정 블록을 놓을 수 있는지 확인한다.
    private static boolean check(int row, int col, int height, int width) {
        int emptyCount = 0; // 빈 공간의 갯수를 센다.
        int preValue = -1; // 이전 값을 저장한다.

        // 지정된 범위만큼만 확인한다.
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                // 빈 공간이라면 그 빈 공간에 놓을 수 있는지 canFill()을 통해서 확인한다.
                if (map[r][c] == 0) {
                    if (!canFill(r, c)) return false;

                    if (++emptyCount > 2) return false;
                } else {
                    // -1이 아니고 preValue 값이 현재 값과 다르다면, 이는 다른 블록의 일부를 뜻한다.
                    // 따라서 return false. 블록을 지우기 위해서는 같은 숫자의 블록만 지울 수 있음!
                    if (preValue != -1 && preValue != map[r][c]) return false;

                    // preValue 값에 현재 좌표의 값을 업데이트 해준다.
                    preValue = map[r][c];
                }
            }
        }

        // return false 에 걸리지 않고 여기까지 왔다면 해당 블록을 지울 수 있음을 뜻한다.
        // 따라서 직사각형의 영역만큼 0으로 초기화한 뒤, true 반환한다.
        for (int r = row; r < row + height; r++) {
            for (int c = col; c < col + width; c++) {
                map[r][c] = 0;
            }
        }
        return true;
    }

    // 검정 블록을 놓기 위해서 0부터 해당 좌표 위까지 공간이 비어있는지 확인한다.
    // 아무것도 없고 비어있어야 검은 블록을 놓을 수 있다.
    private static boolean canFill(int row, int col) {
        for (int r = 0; r < row; r++) {
            if (map[r][col] != 0) return false;
        }
        return true;
    }
}
