package programmers;

import java.util.Stack;

/**
 * created by victory_woo on 2020/03/25
 * 탑.
 */
public class PGM42588 {
    public static void main(String[] args) {
        solution(new int[]{6, 9, 5, 7, 4});
        //solution(new int[]{3, 9, 9, 3, 5, 7, 2});
        //solution(new int[]{1, 5, 3, 6, 7, 6, 5});
    }

    public static int[] solution(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            // i : 기준이 되는 수. 배열의 마지막에서부터 확인을 한다.
            for (int j = i - 1; j >= 0; j--) {
                // 기준이 되는 수에서 왼쪽을 확인한다.
                // 왼쪽에 있는 탑이 기준이 되는 수보다 높다면 기준 탑에서 보낸 신호를
                // 수신할 수 있으므로 stack 에 저장한다.
                if (heights[i] < heights[j]) {
                    stack.push(j + 1);
                    break;
                } else if (j == 0) {
                    // 기준 탑 왼쪽으로 계속 확인해도 없을 때 즉, j==0이 되었을 때는 stack 에 0을 넣어준다.
                    stack.push(0);
                }
            }
        }

        int[] answer = new int[heights.length];
        // 맨 처음 원소는 왼쪽에 아무것도 없기 때문에 0을 넣어준다.
        answer[0] = 0;
        int index = 1;
        // 스택에서 값을 빼서 배열에 넣어준다.
        while (!stack.isEmpty()) {
            answer[index++] = stack.pop();
        }

        return answer;
    }
}
