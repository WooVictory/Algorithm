package 카카오20;

import java.util.Stack;

/**
 * created by victory_woo on 2020/04/22
 */
public class Test3_Re {
    public static void main(String[] args) {
        System.out.println(solve("(()())()"));
        System.out.println(solve(")("));
        System.out.println(solve("()))((()"));
    }

    public String solution(String p) {
        return solve(p);
    }

    private static String solve(String p) {
        StringBuilder sb = new StringBuilder();
        // 1. 입력이 빈 문자열이면 빈 문자열을 반환한다.
        if (p.equals("")) return "";

        int index = divideBalanceBracket(p);
        String u = p.substring(0, index);
        String v = p.substring(index, p.length());

        // 3. u가 올바른 괄호 문자열인지 여부를 확인한다.
        if (isCorrectBracket(u)) {
            // 3-1. 올바른 괄호 문자열인 경우, v에 대해 1단계부터 반복하며, 수행한 결과 문자열을 u에 붙인 문자열을 반환한다.
            //return u + solve(v);
            return sb.append(u).append(solve(v)).toString();
        } else {
            // 4. u가 올바른 괄호 문자열이 아닌 경우,
            // 4-1. 빈 문자열의 첫 번째 문자로 '('를 붙인다.
            // 4-2. v에 대해 1단계부터 반복한 결과 문자열을 붙인다.
            // 4-3. ')'를 다시 붙인다.
            String temp = "(" + solve(v) + ")";
            // 4-4. 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 temp 뒤에 이어 붙인다.
            u = u.substring(1, u.length() - 1);
            u = reverse(u);
            // 4-5. 생성된 문자열을 반환한다.
            //return temp + u;
            return sb.append(temp).append(u).toString();
        }
    }

    // 2. 입력 문자열을 가장 작은 균형잡힌 문자열로 분리할 수 있는 인덱스를 반환한다.
    private static int divideBalanceBracket(String p) {
        Stack<Character> st = new Stack<>();
        int index = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (st.isEmpty() || st.peek().equals(c)) {
                st.push(c);
            } else {
                st.pop();
            }
            index++;

            if (st.isEmpty()) break;
        }

        return index;
    }

    // 올바른 괄호 문자열인지 검사한다.
    // 여는 괄호와 닫는 괄호의 순서가 맞아야 하며, 짝이 맞아야 한다.
    // )( -> 이 경우는 올바른 괄호 문자열이 아님.
    private static boolean isCorrectBracket(String u) {
        int count = 0;
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') count++;
            else count--;

            if (count < 0) return false;
        }
        return true;
    }

    // 나머지 문자열의 괄호 방향을 뒤집는다.
    // 단지, 문자열 자체를 뒤집는게 아니다. 괄호의 반대 방향으로 뒤집을 뿐!
    private static String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }

}
