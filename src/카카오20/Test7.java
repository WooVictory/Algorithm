package 카카오20;

/**
 * created by victory_woo on 2020/04/24
 * 카카오 20 기출.
 * 기둥과 보.
 * 문제에서는 그림이 x,y가 바뀌는데 문제를 풀 때는 그대로 입력이 주어진 대로 사용한다.
 * 입력 받은 대로 조건만 잘 구현한다면 문제가 없다.
 */
public class Test7 {
    public static void main(String[] args) {
        int[][] a = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        solution(5, a);
    }

    private static int len;
    private static boolean[][] cols, rows;
    private static final int COL = 0, ROW = 1, REMOVE = 0, ADD = 1;
    // COL : 기둥, ROW : 보.
    // REMOVE : 삭제, ADD : 추가.

    public static int[][] solution(int n, int[][] build_frame) {
        len = n;
        cols = new boolean[len + 3][len + 3];
        rows = new boolean[len + 3][len + 3];

        // 설치된 구조물의 갯수를 센다.
        int count = 0;
        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            int struct = frame[2];
            int method = frame[3];

            // 기둥의 경우.
            if (struct == COL) {
                // 방법이 설치이고 설치할 수 있는 조건을 만족한다면 기둥을 설치한다.
                if (method == ADD && isExistCol(x, y)) {
                    cols[x][y] = true;
                    count++;
                }

                if (method == REMOVE && isRemove(x, y, COL)) {
                    cols[x][y] = false;
                    count--;
                }
            } else {
                // 보의 경우.
                if (method == ADD && isExistRow(x, y)) {
                    rows[x][y] = true;
                    count++;
                }

                if (method == REMOVE && isRemove(x, y, ROW)) {
                    rows[x][y] = false;
                    count--;
                }
            }
        }

        // 남아있는 기둥과 보를 answer 배열에 저장한다.
        // x와 y가 같은 경우는 기둥이 보보다 앞에 와야 하기 때문에 cols 배열을 먼저 확인하고 넣는다.
        // 좌표로 표현했고, 반복문이 순차적으로 돌기 때문에 순차적으로 저장된다.
        // 앞 부분 좌표에 구조물이 설치되었으면, 반복문을 돌면서 가장 앞에 들어간다.
        // 뒷 부분 좌표에 구조물이 설치되었으면, 반복문을 돌면서 가장 나중에 들어간다.
        int[][] answer = new int[count][3];
        int index = 0;
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                if (cols[i][j]) answer[index++] = new int[]{i - 1, j - 1, COL};
                if (rows[i][j]) answer[index++] = new int[]{i - 1, j - 1, ROW};
            }
        }

        //print(answer);
        return answer;
    }

    // 기둥을 세울 수 있는 조건을 확인한다.
    // 기둥을 바닥 위에 있거나 -> 아래 좌표가 바닥 위의 좌표이어야 한다.
    // 다른 기둥 위에 있거나 -> 해당 기둥 아래에 설치된 기둥이 있거나.
    // 보의 한쪽 끝 부분 위에 있거나 -> 그림에서 보는 두 칸에 걸쳐 설치되지만, 좌표 상에서는 한 칸만 차지한다.
    // 따라서 이를 비교하기 위해서는 기둥이 설치된 좌표에서의 보와 그 이전 좌표의 보가 있는지 확인한다.
    private static boolean isExistCol(int x, int y) {
        return y == 1 || cols[x][y - 1] || rows[x][y] || rows[x - 1][y];
    }

    // 보를 설치할 수 있는 조건을 확인한다.
    // 보는 한쪽 끝 부분이 기둥 위에 있거나 -> 보의 바로 아래쪽에 기둥이 있거나 보는 그림에서 두 칸을 차지하기 때문에 보의 아래쪽의 한칸 더 확장한 칸에
    // 기둥이 있어야 한다.
    // 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 한다. -> 설치한 보의 좌표를 기준으로 양쪽에 보가 존재해야 한다.
    private static boolean isExistRow(int x, int y) {
        return cols[x][y - 1] || cols[x + 1][y - 1] || (rows[x - 1][y] && rows[x + 1][y]);
    }

    private static boolean isRemove(int x, int y, int type) {
        boolean result = true;

        // 임시로 삭제한다.
        if (type == COL) cols[x][y] = false;
        else rows[x][y] = false;


        // 구조물을 임시로 삭제한 뒤,
        // 반복문을 돌면서, 설치한 구조물들이 위의 두 규칙을 여전히 만족하는지 확인한다.
        // 여전히 만족한다면 삭제가 가능함을 뜻하고,
        // 만족하지 못한다면 삭제하면 안됨을 뜻한다.
        // 이를 result 값으로 표현한다.
        loop:
        for (int i = 1; i <= len + 1; i++) {
            for (int j = 1; j <= len; j++) {
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

        // 임시로 삭제했던 구조물을 원상복구 시킨다.
        if (type == COL) cols[x][y] = true;
        else rows[x][y] = true;

        // result 의 값을 받는 곳에서 삭제할지 말지를 결정한다.
        return result;
    }

    private static void print(int[][] answer) {
        for (int[] a : answer) {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
    }
}
