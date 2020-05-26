package programmers;

import java.util.Stack;

/**
 * created by victory_woo on 2020/05/17
 * 큰 수 만들기 - Level2
 * Stack 사용.
 */
public class PGM42883_REVIEW {
    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
        System.out.println(solution("123", 1));
        System.out.println(solution("777777", 2));
    }

    public static String solution(String number, int k) {
        // 스택을 활용한다.
        Stack<Character> stack = new Stack<>();

        //
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);

            // 스택에 문자를 하나 넣고 나서 조건을 확인할 수 있다.
            // 스택이 비어있지 않고, 스택에 들어있는 값이 다음 문자보다 작고 k가 0보다 커서 아직 지울 수 있다면
            // 스택에 있는 값을 빼준다. 이를 통해서 이전 문자가 현재 문자보다 작다면 제거할 수 있다.(스택의 특징 활용)
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }

            // 스택에 문자를 넣는다.
            stack.push(c);
        }

        // 위의 for 반복문이 끝났음에도 여전히 스택에 값이 남아있다면
        // 이는 동일한 값이 반복되어 나타나는 경우일 수 있다. ex) 777777
        // 따라서 이때는 while 반복문을 돌며 k가 0보다 큰 동안 스택에서 pop 한다.
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }

        // 남아있는 스택의 문자를 활용해 가장 큰 수를 만드는데, 꺼내서 reverse()를 통해 뒤집어 줘야 한다.
        // 스택의 특징인 LIFO 때문!
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
