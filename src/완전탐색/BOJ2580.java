package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 27/05/2019
 * 완탐 : 스도쿠
 * 436ms
 */
public class BOJ2580 {
    private static final String SPACE = " ";
    private static final int SIZE = 9;
    private static int[][] map = new int[SIZE + 1][SIZE + 1];
    private static int currentRow, currentColumn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 스도쿠 판을 입력받는다.
        for (int i = 1; i <= SIZE; i++) {
            String[] input = br.readLine().split(SPACE);
            for (int j = 1; j <= SIZE; j++) {
                map[i][j] = parse(input[j - 1]);
            }
        }

        dfs();
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                System.out.print(map[i][j] + SPACE);
            }
            System.out.println();
        }
    }

    private static boolean dfs() {

        // 0인 곳을 먼저 찾는다. 즉, 비어있는 곳을 찾는다.
        // true 를 리턴한다는 것은 0으로 되어 있는 곳이 없음을 의미한다.
        if (findEmpty()) {
            return true;
        }

        // 빈 칸을 찾음.
        int row = currentRow;
        int column = currentColumn;

        // 현재 row, column 에 숫자를 놓을 수 있는지 없는지 확인한다.
        for (int i = 1; i <= SIZE; i++) {
            // 해당 스도쿠 칸에 숫자를 놓을 수 있는지 결정한다.
            if (isPossible(row, column, i)) {
                map[row][column] = i;

                if (dfs()) return true;
                // failure.
                map[row][column] = 0;
            }
        }
        // 백트래킹.
        // 해당 빈칸에 수를 놓을 수 없다면 false 를 리턴함으로써 이전으로 돌아가서 다른 가지를 찾는다.
        return false;

    }

    // 0인 스도쿠 칸 즉, 비어있는 칸이 존재하는지 확인한다.
    // 비어 있다면 비어 있는 좌표가 어디인지 저장하고 false 를 리턴한다.
    // 비어 있지 않다면 true 를 리턴한다.
    private static boolean findEmpty() {
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                if (map[i][j] == 0) {
                    currentRow = i;
                    currentColumn = j;
                    System.out.println("current 행: " + currentRow+", current 열: "+currentColumn);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPossible(int row, int column, int number) {
        if (checkRow(row, number) && checkColumn(column, number) && checkBox(row, column, number)) {
            return true;
        }
        return false;
    }


    // row 행의 1~9열까지 검사를 하면서 number 가 있으면 놓을 수 없으므로 false 를 리턴한다.
    // 없다면 숫자를 놓을 수 있기 때문에 true 를 리턴한다.
    private static boolean checkRow(int row, int number) {
        for (int i = 1; i <= SIZE; i++) {
            if (map[row][i] == number) {
                return false;
            }
        }
        return true;
    }

    // 1~9행의 column 열을 검사하면서 number 가 있으면 숫자를 놓을 수 없으므로 false 를 리턴한다.
    // 일치하는 숫자가 없다면 숫자를 놓을 수 있기 때문에 true 를 리턴한다.
    private static boolean checkColumn(int column, int number) {
        for (int i = 1; i <= SIZE; i++) {
            if (map[i][column] == number) {
                return false;
            }
        }
        return true;
    }
    // 3x3 격자 내부
    // 이게 이해가 안감..;;
    private static boolean checkBox(int row, int column, int number) {
        int x = ((int) Math.ceil(row / 3.0) - 1) * 3 + 1;
        int y = ((int) Math.ceil(column / 3.0) - 1) * 3 + 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[x + i][y + j] == number) {
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