package 카카오20;

import java.util.Stack;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 괄호 변환.
 */
public class RE_Test2 {
    public static void main(String[] args) {
        //System.out.println(solution("()))((()"));
        //System.out.println(solution(")("));
        System.out.println(solution("(()())()"));
        System.out.println(isRightBracket(")("));
    }

    public static String solution(String p) {
        // 1. 입력이 빈 문자열인 경우, 그 빈문자열을 반환한다.
        if (p.equals("")) return p;
        return solve(p);
    }

    // 2. 과정.
    private static String solve(String p) {
        StringBuilder sb = new StringBuilder();

        // 3. 문자열 p를 두 균형잡힙 괄호 문자열 u,v로 분리한다.
        // 분리하기 위한 인덱스를 찾고, u와 v로 분리한다.
        int index = find(p);
        if (index == -1) return "";

        String u = p.substring(0, index);
        String v = p.substring(index);
        System.out.println(u + ", " + v); // 확인용.

        // 4. 문자열 u가 올바른 괄호 문자열인지 확인한다.
        // 문자열 v에 대해서 위의 과정을 반복하고 수행한 결과를 u 뒤에 붙여 반환한다.
        if (isRightBracket(u)) {
            return sb.append(u).append(solve(v)).toString();
        } else {
            // 5. 문자열 u가 올바른 괄호 문자열이 아니라면
            // 문자열 v를 기준으로 위의 과정을 반복한다. 그리고 앞, 뒤로 '(', ')'를 붙인다.
            String tmp = "(" + solve(v) + ")";

            // 6. u의 첫 번째와 마지막 문자를 제거한다.
            u = u.substring(1, u.length() - 1);
            // 7. 나머지 문자열의 괄호 방향을 뒤집는다.
            u = reverse(u);
            // 8. tmp 뒤에 u를 붙여 반환한다.
            return sb.append(tmp).append(u).toString();
        }
    }

    // 가장 짧은 균형 잡힙 괄호 문자열로 분리할 수 있는 인덱스를 찾는다.
    private static int find(String p) {
        int sCnt = 0, eCnt = 0;
        for (int index = 0; index < p.length(); index++) {
            if (p.charAt(index) == '(') sCnt++;
            else eCnt++;

            if (sCnt == eCnt) return (index + 1);
        }

        return -1;
    }

    // u 문자열의 괄호 방향을 뒤집은 문자열을 반환한다.
    private static String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }

    // 올바른 괄호 문자열인지 확인한다.
    // 스택을 사용한다. 하지만, 굳이 스택을 쓰지 않아도 된다.
    private static boolean isRightBracket(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                if (stack.peek() == '(') stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
