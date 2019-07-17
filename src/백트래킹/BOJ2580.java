package 백트래킹;

import java.io.*;

/**
 * created by victory_woo on 17/07/2019
 * 스도쿠.
 */
public class BOJ2580 {
    private static int currentRow, currentCol;
    private static final int MAX = 9;
    private static int[][] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        a = new int[MAX][MAX];
        for (int i = 0; i < 9; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                a[i][j] = parse(in[j]);
            }
        }
        go();
        // 확인용.
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                sb.append(a[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean go() {
        int r, c;

        // 0인 곳을 찾는다. 이 곳은 스도쿠의 규칙을 만족하는 숫자를 놓을 수 있는 공간이다.
        if (findEmpty()) {
            return true;
        }

        // 0인 곳의 좌표를 r,c로 받는다.
        r = currentRow;
        c = currentCol;

        // 1 ~ 9까지의 숫자를 스도쿠 판에 놓을 수 있는지 확인한다.
        for (int i = 1; i <= MAX; i++) {
            if (isPossible(r, c, i)) {
                a[r][c] = i; // 해당 칸에 숫자를 놓는다.
                if (go()) {
                    return true;
                }
                // failure.
                a[r][c] = 0;

            }
        }
        // 백트래킹
        // 해당 빈칸에 숫자를 놓을 수 없다면, false 를 반환해서 이전 수로 돌아가서 다른 수를 찾는다.
        return false;
    }

    // 0인 곳을 찾는다.
    // 0인 곳이 존재하면 false 를 반환.
    // 0인 곳이 없을 경우, true 를 반환.
    private static boolean findEmpty() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (a[i][j] == 0) {
                    currentRow = i;
                    currentCol = j;
                    return false;
                }
            }
        }
        return true;
    }

    // 해당 정점에 숫자를 놓을 수 있는지 확인한다.
    // 1. 행을 검사.
    // 2. 열을 검사.
    // 3. 3x3 박스 검사.
    private static boolean isPossible(int r, int c, int number) {
        return checkRow(r, number) && checkColumn(c, number) && checkBox(r, c, number);
    }

    // 같은 행에 놓으려는 숫자가 존재하는지 확인한다.
    // 존재한다면, false 반환.
    // 존재하지 않는다면, true 를 반환함으로써 숫자를 놓을 수 있다.
    private static boolean checkRow(int r, int num) {
        for (int i = 0; i < MAX; i++) {
            if (a[r][i] == num) {
                return false;
            }
        }
        return true;
    }

    // 같은 열에 놓으려는 숫자가 존재하는지 확인한다.
    private static boolean checkColumn(int c, int num) {
        for (int i = 0; i < MAX; i++) {
            if (a[i][c] == num) {
                return false;
            }
        }
        return true;
    }

    // 3x3 box 를 검사한다.
    private static boolean checkBox(int row, int col, int num) {
        int R = (row / 3) * 3;
        int C = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[R + i][C + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}