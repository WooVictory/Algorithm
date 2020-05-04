package 카카오19인턴;

import java.util.Stack;

/**
 * created by victory_woo on 2020/05/04
 * 카카오 19 인턴 기출.
 * 크레인 인형 뽑기 게임.
 */
public class Test1_re {
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

        // 1. moves 에서 크레인이 인형을 뽑을 열을 선택한다.
        for (int move : moves) {
            col = move - 1;
            int doll = 0;

            // 2. 해당 열에서 0이 아닌 지점의 인형을 뽑고 0으로 초기화한다.
            for (int i = 0; i < n; i++) {
                if (board[i][col] != 0) {
                    doll = board[i][col];
                    board[i][col] = 0;
                    break;
                }
            }

            // doll 이 0이라면 뽑을 인형이 없는 것이기 때문에 건너뛴다.
            if (doll == 0) continue;

            // 3.
            // 스택이 비어 있지 않고 스택에 상단에 있는 값이 doll 과 같다면
            // 인형 2개가 같으므로 지울 수 있다. 스택 상단에 있는 인형을 지우고 answer 에 지운 인형의 개수를 더한다.
            if (!stack.isEmpty() && stack.peek() == doll) {
                stack.pop();
                answer += 2;
            } else {
                // 위의 조건을 만족하지 않는다면 스택에 인형을 넣는다.
                // 스택이 비어있거나 상단에 있는 인형과 같지 않은 경우.
                stack.push(doll);
            }

        }
        return answer;
    }
}
