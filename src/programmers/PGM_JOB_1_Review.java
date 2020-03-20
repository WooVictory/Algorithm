package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 06/04/2019
 * Anagram 문제
 * 대소문자를 구분하지 않는다.
 */
public class PGM_JOB_1_Review {
    public static void main(String[] args) {
        System.out.println(isAnagram("listen", "silent"));
    }

    private static boolean isAnagram(String a, String b) {
        // 대소문자를 구분하지 않기 때문에 문자열의 문자를 모두 대문자로 바꾼다.
        // 그리고 String을 char 배열로 변환한다.
        char[] char_a = a.toUpperCase().toCharArray();
        char[] char_b = b.toUpperCase().toCharArray();

        // 알파벳 순으로 정렬한다.
        Arrays.sort(char_a);
        Arrays.sort(char_b);

        // 다시 문자열에 할당한다.
        String str_a = new String(char_a);
        String str_b = new String(char_b);

        // 그러면 정렬도 했으니 같은 문자열이 담겨 있게 된다.
        // 같은 문자열일 경우 애너그램이라고 할 수 있으므로 true를 리턴한다.
        if (str_a.equals(str_b)) {
            return true;
        }

        return false;
    }
}
