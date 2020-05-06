package 카카오19인턴;

import java.util.Stack;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 19 인턴 기출.
 * 다시 푸는 중.
 * 크레인 인형 뽑기.
 */
public class RE_Test1 {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(solution(board, moves));
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        Stack<Integer> stack = new Stack<>();

        // 1. moves 배열을 돌면서 크레인이 인형을 뽑을 열을 선택한다.
        for (int col : moves) {
            col = col - 1;
            int doll = 0;
            // 2. 선택된 열에서 위에서부터 차근 차근 내려가면서 0이 아닌 값을 만날때까지 찾는다. 이는 뽑을 인형이 된다.
            for (int j = 0; j < n; j++) {
                if (board[j][col] != 0) {
                    // 인형을 doll 변수에 저장하고 인형이 있던 곳을 0으로 초기화 한다.
                    doll = board[j][col];
                    board[j][col] = 0;
                    break;
                }
            }

            // 뽑을 인형이 없을 경우, doll 이 0이 되는데, 이때는 건너뛴다.
            if (doll == 0) continue;

            // 3. 스택이 비어있지 않고, 스택에 위에 있는 인형이 doll 과 같으면 두 인형이 같으므로 스택에 있던 인형을 빼주고 +2 해준다.
            if (!stack.isEmpty() && stack.peek() == doll) {
                answer += 2;
                stack.pop();
            } else {
                // 4. 그렇지 않은 경우, 스택이 비어있거나 스택의 맨 위 인형과 doll 이 같지 않으므로 스택에 인형을 넣어준다.
                stack.push(doll);
            }
        }
        return answer;
    }
}
