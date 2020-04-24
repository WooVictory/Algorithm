package 카카오20;

/**
 * created by victory_woo on 2020/04/24
 * 카카오 20 기출.
 * 기둥과 보.
 */
public class Test7_re {
    public static void main(String[] args) {
        int[][] a = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        solution(5, a);
    }

    private static int len;
    // 기둥과 보의 설치 유무를 판단하기 위한 boolean 배열.
    private static boolean[][] cols;
    private static boolean[][] rows;
    // 기둥 = 0, 보 = 1
    private static final int COL = 0, ROW = 1;
    private static final int REMOVE = 0, ADD = 1;
    // 삭제 = 0, 설치 = 1

    public static int[][] solution(int n, int[][] build_frame) {
        len = n;

        // n이 5일 때, 0 ~ 5까지 구조물을 놓으므로 +1
        // 보는 양 쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 하는 조건이 있다.
        // (0,y) 위치에서 보를 삭제할 때, -1 위치를 제외하기 위해서 양쪽에 +2씩 더해 총 +3을 확장한다.
        cols = new boolean[len + 3][len + 3];
        rows = new boolean[len + 3][len + 3];

        int count = 0; // 설치한 구조물의 개수를 파악한다.
        for (int[] frame : build_frame) {
            int x = frame[0] + 1, y = frame[1] + 1;
            int struct = frame[2];
            int method = frame[3];

            // 기둥인 경우.
            if (struct == COL) {
                // 설치하는 방법이고 설치가 가능한지 조건을 확인한다.
                if (method == ADD && isExistCol(x, y)) {
                    cols[x][y] = true; // 설치함.
                    count++; // 개수 카운트.
                }

                // 삭제하는 방법이고, 삭제 가능한지 조건을 확인한다.
                if (method == REMOVE && isRemove(x, y, COL)) {
                    cols[x][y] = false; // 삭제함.
                    count--; // 개수 카운트.
                }
            } else { // 보인 경우.
                // 설치하는 방법이고 설치가 가능한지 조건을 확인한다.
                if (method == ADD && isExistRow(x, y)) {
                    rows[x][y] = true;
                    count++;
                }

                // 삭제하는 방법이고, 삭제가 가능한지 조건을 확인한다.
                if (method == REMOVE && isRemove(x, y, ROW)) {
                    rows[x][y] = false;
                    count--;
                }
            }
        }

        // 최종 결과를 담을 배열.
        // 구조물을 설치한 곳의 좌표가 cols,rows 배열에 담기게 된다.
        // 따라서 반복문을 순차적으로 돌기 때문에 cols, rows 에 설치된 구조물도 좌표 기준
        // 오름 차순으로 차례로 방문하여 answer 배열에 담을 것이다.
        // 다만, x == y 인 경우, 기둥이 보보다 앞에 와야 하기 때문에 기둥을 먼저 넣는다.
        int[][] answer = new int[count][3];
        int index = 0;
        for (int i = 1; i <= len + 1; i++) {
            for (int j = 1; j <= len + 1; j++) {
                if (cols[i][j]) answer[index++] = new int[]{i, j - 1, COL};
                if (rows[i][j]) answer[index++] = new int[]{i, j - 1, ROW};
            }
        }

        return answer;
    }

    // 기둥을 설치하기 위한 조건.
    private static boolean isExistCol(int x, int y) {
        return y == 1 || cols[x][y - 1] || rows[x][y] || rows[x - 1][y];
    }

    // 보를 설치하기 위한 조건.
    private static boolean isExistRow(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    // 삭제한다.
    // 임시로 삭제하고 난 뒤, 해당 구조물을 확인하여 여전히 조건을 만족한다면 삭제해도 된다.
    // 그렇지 않다면 삭제하면 안된다.
    // 여전히 만족하는지 여부를 확인하고 난 뒤, 삭제한 구조물을 원상복구시킨다.
    // 여전히 만족하는지 여부를 반환한다.
    private static boolean isRemove(int x, int y, int type) {
        boolean result = true;

        // 임시로 삭제한다.
        if (type == COL) cols[x][y] = false;
        else rows[x][y] = false;

        // 여전히 위의 규칙(조건)을 만족하는지 확인한다.
        // 만족하지 않으면 result = false 로 바꾸고 반복문을 다 빠져나온다.
        loop:
        for (int i = 1; i <= len + 1; i++) {
            for (int j = 1; j <= len + 1; j++) {
                if (cols[i][j] && !isExistCol(i, j)) {
                    result = false;
                    break loop;
                }

                if (rows[i][j] && !isExistRow(i, j)) {
                    result = false;
                    break loop;
                }
            }
        }

        // 원상 복구.
        if (type == COL) cols[x][y] = true;
        else rows[x][y] = true;

        return result;
    }
}
