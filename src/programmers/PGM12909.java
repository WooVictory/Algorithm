package programmers;

/**
 * created by victory_woo on 2020/05/13
 * 올바른 괄호.
 */
public class PGM12909 {
    public static void main(String[] args) {
        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
        System.out.println(solution("())"));
        System.out.println(solution("()))((()"));
    }

    public static boolean solution(String s) {

        if (s.charAt(0) == ')') return false;

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') count++;
            else count--;

            if (count < 0) return false;
        }


        return count == 0;
    }
}
