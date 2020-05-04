package 카카오19인턴;

import java.util.Stack;

/**
 * created by victory_woo on 2020/05/03
 * 카카오 19 겨울 인턴
 * 크레인 인형 뽑기 게임.
 */
public class Test1 {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        System.out.println(solution(board, moves));
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;

        int n = board.length;
        int col;
        Stack<Integer> stack = new Stack<>();

        for (int move : moves) {
            int doll = 0;
            col = move - 1;
            for (int j = 0; j < n; j++) {
                if (board[j][col] != 0) {
                    doll = board[j][col];
                    board[j][col] = 0;
                    break;
                }
            }

            print(board);

            if (doll == 0) continue;

            if (!stack.isEmpty() && stack.peek() == doll) {
                stack.pop();
                answer += 2;
            } else {
                stack.push(doll);
            }
            System.out.println((col + 1) + "번째 열 : " + doll);
            System.out.println(stack);
        }
        return answer;
    }

    private static void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

