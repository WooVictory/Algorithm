package programmers;

/**
 * created by victory_woo on 2020/03/23
 * 시저 암호.
 * (c+n-start)%26 -> 이렇게 생각하는 게 가장 key point 가 되는 문제이다.
 */
public class PGM12926 {
    public static void main(String[] args) {
        System.out.println(solution("AB", 1));
        System.out.println(solution("Z", 25));
        System.out.println(solution("Z", 1));
        System.out.println(solution("z", 1));
        System.out.println(solution("a B z", 4));
        System.out.println(solution(" ", 4));

    }

    public static String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                char start = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) (start + ((c + n - start) % 26));
            }
            sb.append(c);
        }

        return sb.toString();
    }
}
