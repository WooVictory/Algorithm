package programmers;

import java.util.Stack;

/**
 * created by victory_woo on 2020/03/25
 * 쇠막대기.
 * 스택 사용.
 */
public class PGM42585 {
    public static void main(String[] args) {
        System.out.println(solution2("()(((()())(())()))(())"));
        System.out.println(solution2("((()))"));
    }

    public static int solution(String arrangement) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arrangement.length(); i++) {
            char c = arrangement.charAt(i);
            if (c == '(') { // 여는 괄호일 경우, 막대기의 시작을 뜻하므로 스택에 넣는다.
                stack.push(c);
            } else {
                // 닫는 괄호의 경우, 스택에 있는 괄호를 뺀다.
                // 레이저이든, 쇠막대기이든 어차피 스택에 있는 여는 괄호는 빼야하기 때문이다.
                stack.pop();
                // 문자열에서 이전 문자가 여는 괄호라면 레이저이기 때문에 스택에 존재하는 여는 괄호의 갯수를 answer 값에 더해준다.
                // 여는 괄호가 아니라면 쇠막대기가 끝났음을 의미하기 때문에 answer++을 해준다.
                // 이는 레이저에 의해서 잘린 쇠막대기의 마지막 끝 부분 처리를 위함이다.
                if (arrangement.charAt(i - 1) == '(') answer += stack.size();
                else answer++;
            }
        }
        return answer;
    }

    public static int solution2(String arrangement) {
        int answer = 0;
        arrangement = arrangement.replace("()", "1");
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arrangement.length(); i++) {
            char c = arrangement.charAt(i);
            if (c == '(') {
                stack.push('(');
            } else if (c == ')') {
                answer++;
                stack.pop();
            } else if (c == '1') {
                answer += stack.size();
            }
        }

        return answer;
    }
}
