package 카카오20;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 기둥과 보 설치.
 */
public class RE_Test5 {
    public static void main(String[] args) {
        int[][] a = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        solution(5, a);
    }

    private static final int COL = 0, ROW = 1;
    private static final int REMOVE = 0, ADD = 1;
    private static boolean[][] cols, rows;
    private static int len;

    public static int[][] solution(int n, int[][] build_frame) {
        len = n;
        cols = new boolean[n + 3][n + 3];
        rows = new boolean[n + 3][n + 3];

        int count = 0;
        for (int[] frame : build_frame) {
            int x = frame[0] + 1, y = frame[1] + 1;
            int struct = frame[2], method = frame[3];

            if (struct == COL) {
                if (method == ADD) {
                    if (isPossibleCols(x, y)) {
                        cols[x][y] = true;
                        count++;
                    }
                }

                if (method == REMOVE) {
                    if (isRemove(x, y, struct)) {
                        cols[x][y] = false;
                        count--;
                    }
                }

            } else {
                if (method == ADD) {
                    if (isPossibleRows(x, y)) {
                        rows[x][y] = true;
                        count++;
                    }
                }

                if (method == REMOVE) {
                    if (isRemove(x, y, struct)) {
                        rows[x][y] = false;
                        count--;
                    }
                }
            }
        }

        int[][] answer = new int[count][3];

        int index = 0;
        for (int i = 1; i <= len + 1; i++) {
            for (int j = 1; j <= len + 1; j++) {
                if (cols[i][j]) answer[index++] = new int[]{(i - 1), (j - 1), COL};
                if (rows[i][j]) answer[index++] = new int[]{(i - 1), (j - 1), ROW};
            }
        }

        for (int[] a : answer) {
            for (int v : a) System.out.print(v + " ");
            System.out.println();
        }

        return answer;
    }

    // 기둥을 설치할 수 있는지 확인한다.
    // 바닥 위에 있어야 한다.
    // 보의 한쪽 끝 부분 위에 있거나(보의 방향이 오른쪽으로 정해져 있기 때문에 현재 위치에서 오른쪽 방향으로 가는 보가 있거나
    // 이전 위치에서 현재 위치로 오는 방향의 보가 있거나 확인해야 한다.)
    // 또는 다른 기둥 위에 있어야 한다.(이 기둥 아래에 기둥이 있어야 한다.)
    private static boolean isPossibleCols(int x, int y) {
        return y == 1 || rows[x][y] || rows[x - 1][y] || cols[x][y - 1];
    }

    // 보를 설치할 수 있는지 확인한다.
    // 한쪽 끝 부분이 기둥 위에 있거나 -> 현재 위치 아래에 기둥이 있거나 보의 방향으로 갔을 때, 아래에 기둥이 있어야 한다.
    // 또는 양쪽 끝 부분이 다른 보와 연결되어 있어야 한다.
    private static boolean isPossibleRows(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    private static boolean isRemove(int x, int y, int type) {
        if (type == COL) cols[x][y] = false;
        else rows[x][y] = false;

        boolean flag = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (cols[i][j] && !isPossibleCols(i, j)) flag = false;

                if (rows[i][j] && !isPossibleRows(i, j)) flag = false;

                if (!flag) break;
            }
            if (!flag) break;
        }

        if (type == COL) cols[x][y] = true;
        else rows[x][y] = true;

        return flag;
    }
}
