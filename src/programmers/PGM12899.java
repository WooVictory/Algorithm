package programmers;

/**
 * created by victory_woo on 2020/05/11
 */
public class PGM12899 {
    public static void main(String[] args) {
        System.out.println(solution(10));
    }

    public static String solution(int n) {
        StringBuilder answer = new StringBuilder();
        while (n > 0) {
            int mod = n % 3;
            n = n / 3;

            if (mod == 0) {
                mod = 4;
                n = n - 1;
            }
            answer.insert(0, mod);
        }

        return answer.toString();
    }
}
