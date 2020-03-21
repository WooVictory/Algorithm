package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/03/21
 */
public class PGM12933 {
    public static void main(String[] args) {
        System.out.println(solution(118372));
    }

    public static long solution(long n) {
        char[] s = String.valueOf(n).toCharArray();
        Arrays.sort(s);
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<s.length;i++){
            sb.append(s[i]);
        }

        return Long.parseLong(sb.reverse().toString());
    }
}
