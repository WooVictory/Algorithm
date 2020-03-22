package programmers;

/**
 * created by victory_woo on 2020/03/22
 */
public class PGM12931 {
    public static void main(String[] args) {
        System.out.println(solution2(123));
        System.out.println(solution2(987));
    }

    public static int solution(int n) {
        int answer = 0;
        String s = Integer.toString(n);
        for (int i = 0; i < s.length(); i++) answer += s.charAt(i) - '0';

        return answer;
    }

    public static int solution2(int n) {
        int answer = 0;
        while (n != 0) {
            answer += n % 10;
            n /= 10;
        }

        return answer;
    }
}
