package 완전탐색;

import java.util.Scanner;

/**
 * created by victory_woo on 22/05/2019
 */
public class BOJ9663_sample {

    static int count = 0;

    public static void main(String[] args) {
        int N;
        int[] board;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new int[N];
        dfs(0, board);

        System.out.println(count);
    }

    public static void dfs(int row, int[] board) {
        int n = board.length;
        if (row == n) count++;
        else {
            for (int col = 1; col <= n; col++) {
                board[row] = col;

                if (check(board, row)) {
                    dfs(row + 1, board);
                    board[row] = -1;
                }
            }
        }
    }

    // y 가 row 역할을 함.
    public static boolean check(int[] board, int row) {
        for (int i = 0; i < row; i++) {
            if (Math.abs(board[i] - board[row]) == Math.abs(i - row))
                return false;
            if (board[i] == board[row])
                return false;
        }
        return true;
    }
}
