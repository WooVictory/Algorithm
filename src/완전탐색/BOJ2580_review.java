package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 27/05/2019
 * 완탐 : 스도쿠
 */
public class BOJ2580_review {
    private static final int SIZE = 9;
    private static int currentRow, currentColumn;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[SIZE][SIZE];

        // 입력을 받는다.
        for (int i = 0; i < SIZE; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        // go() 함수는 dfs, 백트래킹을 수행하는 함수이다.
        go();

        // 결과를 출력하는 함수.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean go() {
        int row, column;

        // 입력받은 스도쿠 칸 중에서 비어있는 칸이 존재하는지 확인한다.
        // 비어있는 칸 -> 0으로 된 칸을 말한다.
        if (findEmpty()) {
            return true;
        }

        // 0인 위치를 row, column 지역 변수에 저장한다.
        row = currentRow;
        column = currentColumn;

        // row, column 위치에 1~9까지의 숫자를 놓을 수 있는지 확인한다.
        for (int i = 1; i <= SIZE; i++) {
            // 해당 스도쿠 칸에 숫자를 놓을 수 있는지 결정한다.
            if (isPossible(row, column, i)) {
                map[row][column] = i; // row, column 에 조건을 만족하는 숫자를 넣는다.
                if (go()) return true; // dfs 실행.
                map[row][column] = 0; // failure.
            }
        }

        // 백트래킹.
        // 해당 빈칸에 숫자를 놓을 수 없다면 false 를 리턴함으로써 이전 수로 돌아가서 다른 수를 찾는다.
        // 즉, 가지치기를 실행한다. 따라서 백트래킹 과정이라고 볼 수 있다.
        return false;


    }

    // 3가지 조건을 검사해서 모두 만족할 경우 숫자를 놓을 수 있다.
    // 1. 행을 검사한다.
    // 2. 열을 검사한다.
    // 3. 3x3 정사각형 박스 안을 검사한다.
    private static boolean isPossible(int row, int column, int i) {
        return checkRow(row, i) && checkColumn(column, i) && checkBox(row, column, i);
    }

    // row 행의 1~9까지의 열을 검사해서 해당 숫자가 있는지 검사한다.
    // 즉, 해당 자리에 숫자를 놓기 위함이다.
    // 있다면 false 를 리턴하고, 없다면 true 를 리턴한다.
    private static boolean checkRow(int row, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (map[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    // 1~9까지의 행의 column 열을 검사해서 해당 숫자가 있는지 검사한다.
    // 있다면 false 를 리턴하고, 없다면 true 를 리턴한다.
    private static boolean checkColumn(int column, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (map[i][column] == num) {
                return false;
            }
        }
        return true;
    }

    // 3x3 격자를 검사하기 위함이다.
    // row/3*3, column/3*3 으로 검사할 수 있다.
    // 정사각형 내에서 해당 숫자가 없으면 true 를 리턴한다.
    private static boolean checkBox(int row, int column, int num) {
        int R = row / 3 * 3;
        int C = column / 3 * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[R + i][C + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // 비어있는 칸을 찾는다.
    // 비어있는 칸에 대한 좌표를 currentRow, currentColumn 변수에 저장한다. 그리고 false 를 리턴한다.
    // 0인 칸이 없으면 true 를 리턴한다.
    private static boolean findEmpty() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 0) {
                    currentRow = i;
                    currentColumn = j;
                    return false;
                }
            }
        }
        return true;
    }
}
