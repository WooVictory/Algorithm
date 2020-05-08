package 카카오20;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 기둥과 보 설치.
 */
public class RE_Test5_1 {
    public static void main(String[] args) {

        int[][] a = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        solution(5, a);
    }

    private static int len = 0;
    private static boolean[][] cols, rows;
    private static final int COL = 0, ROW = 1;
    private static final int REMOVE = 0, ADD = 1;

    public static int[][] solution(int n, int[][] build_frame) {
        len = n;

        // 1. 기둥과 보의 설치를 boolean 배열로 표현한다.
        // 배열을 확장한다. 이유는 범위를 벗어나는 부분을 처리하는 대신으로 확장한다.
        // 맨 앞과 끝, 맨 위와 뒤는 사용되지 않는다.
        // 따라서 1 ~ n + 1까지만 탐색하면 된다.
        cols = new boolean[n + 3][n + 3];
        rows = new boolean[n + 3][n + 3];

        int count = 0;
        // 2. build_frame 배열을 기반으로 구조물을 설치 혹은 삭제하는 과정을 진행한다.
        // count 변수를 통해 설치한 구조물의 개수를 카운트한다.
        for (int[] frame : build_frame) {
            int x = frame[0] + 1, y = frame[1] + 1;
            int struct = frame[2], method = frame[3];

            if (struct == COL) { // 기둥인 경우.
                // 설치 작업이면서 설치할 수 있는지 확인한다.
                if (method == ADD && checkCol(x, y)) {
                    cols[x][y] = true;
                    count++;
                }

                // 삭제 작업이면서 삭제 가능한지 확인한다.
                if (method == REMOVE && isRemove(x, y, struct)) {
                    cols[x][y] = false;
                    count--;
                }
            } else { // 보인 경우.
                // 설치 작업이면서 설치할 수 있는지 확인한다.
                if (method == ADD && checkRow(x, y)) {
                    rows[x][y] = true;
                    count++;
                }

                // 삭제 작업이면서 삭제 가능한지 확인한다.
                if (method == REMOVE && isRemove(x, y, struct)) {
                    rows[x][y] = false;
                    count--;
                }
            }
        }

        // 구조물의 갯수만큼 정답 배열의 행을 세팅한다.
        int[][] answer = new int[count][3];
        int index = 0;
        for (int i = 1; i <= len + 1; i++) {
            for (int j = 1; j <= len + 1; j++) {
                if (cols[i][j]) answer[index++] = new int[]{(i - 1), (j - 1), COL};
                if (rows[i][j]) answer[index++] = new int[]{(i - 1), (j - 1), ROW};
            }
        }

        for (int[] a : answer) {
            for (int b : a) System.out.print(b + " ");
            System.out.println();
        }

        return answer;
    }

    // 기둥을 놓을 수 있는지 확인한다.
    // 기둥이 바닥에 있거나 보의 한쪽 끝 부분에 있거나 다른 기둥 위에 있어야 한다.(현재 기둥 밑에 기둥이 있어야 한다.)
    private static boolean checkCol(int x, int y) {
        return y == 1 || rows[x][y] || rows[x - 1][y] || cols[x][y - 1];
    }

    // 보를 놓을 수 있는지 확인한다.
    // 한쪽 끝 부분이 기둥 위에 있거나 양쪽 끝 부분이 다른 보와 동시에 연결되어야 한다.
    private static boolean checkRow(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    // 구조물을 삭제할 수 있는지 확인한다.
    private static boolean isRemove(int x, int y, int type) {
        // 1. 먼저 삭제한다.
        if (type == COL) cols[x][y] = false;
        else rows[x][y] = false;

        boolean flag = true;
        // 2. 그리고 문제의 조건을 여전히 만족하는지 확인한다.
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                // 구조물이 존재하지만, 조건을 만족하지 않는다면 어떤 다른 구조물을 삭제함으로써 문제의 조건을 만족하지 못함을 뜻한다.
                if (cols[i][j] && !checkCol(i, j)) flag = false;
                if (rows[i][j] && !checkRow(i, j)) flag = false;
                if (!flag) break;
            }
            if (!flag) break;
        }

        // 3. 원상복구한다.
        if (type == COL) cols[x][y] = true;
        else rows[x][y] = true;

        return flag;
    }
}
