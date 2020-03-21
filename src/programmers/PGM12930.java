package programmers;

/**
 * created by victory_woo on 2020/03/21
 */
public class PGM12930 {
    public static void main(String[] args) {
        String s = "try hello world";
        System.out.println(solution(s));
    }

    public static String solution(String s) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append(' ');
                index = 0;
                continue;
            }

            String value;
            if (index % 2 == 0) value = String.valueOf(s.charAt(i)).toUpperCase();
            else value = String.valueOf(s.charAt(i)).toLowerCase();

            sb.append(value);
            index++;
        }

        return sb.toString();
    }
}
