package 카카오20;

import java.util.Stack;

/**
 * created by victory_woo on 2020/04/22
 */
public class Test3 {
    public static void main(String[] args) {
        //System.out.println(solution("(())))(("));
        //System.out.println(solution("()))((()"));
        //System.out.println(solution("((()))"));
        System.out.println(solution("(()())()"));
        System.out.println(solution(")("));
        System.out.println(solution("()))((()"));

    }

    public static String solution(String p) {
        return solve(p);
    }

    // 재귀 호출을 수행하는 함수.
    private static String solve(String p) {
        // 입력이 빈 문자열인 경우, 빈 문자열을 반환한다.
        if (p.length() == 0) return "";

        int cut = division(p);
        String u = p.substring(0, cut);
        String v = p.substring(cut, p.length());

        // u가 올바른 괄호 문자열이라면, v에 대해 1단계 부터 다시 수행한다.
        // 수행한 결과 문자열을 u에 이어 붙인 후 반환한다.
        if (isCorrectPattern(u)) {
            return u + solution(v);
        } else {
            String temp = "(" + solve(v) + ")";
            u = u.substring(1, u.length() - 1); // u의 처음과 마지막 문자를 제거한다.
            u = reverse(u);
            return temp + u;
        }
    }

    // p 문자열을 가장 작은 크기의 균형 문자열로 자를 수 있는 인덱스를 반환한다.
    // stack 자료구조를 사용했다.
    private static int division(String p) {
        Stack<Character> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (stack.isEmpty() || c == stack.peek()) {
                stack.push(c);
            } else {
                stack.pop();
            }
            index++;

            if (stack.isEmpty()) break;
        }
        return index;
    }

    // 올바른 괄호 문자열인지 여부를 반환한다. ( 와 ) 갯수가 같고, 짝이 맞으면 올바른 괄호 문자열이다.
    private static boolean isCorrectPattern(String p) {
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') count++;
            else count--;

            if (count < 0) return false;
        }
        return count == 0;
    }

    // 문자열의 괄호 방향을 뒤집는다.
    // 문자열을 뒤집는 것이 아니라 단지, 괄호 방향을 뒤집는다. 따라서 ( ->  ) 가 되고, ) -> (이 된다.
    private static String reverse(String w) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }
}
