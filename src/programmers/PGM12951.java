package programmers;

/**
 * created by victory_woo on 2020/05/27
 * JadenCase 문자열 만들기
 */
public class PGM12951 {
    public static void main(String[] args) {
        System.out.println(solution("3people unFollowed me"));
        System.out.println(solution("for the last week"));
        System.out.println(" ");
    }

    public static String solution(String s) {
        // 1. 먼저 문자열을 모두 소문자로 변환한다.
        s = s.toLowerCase();

        // 2. 공백을 기준으로 문자열을 잘라서 배열에 담는다.
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            // 3. 문자열의 길이가 1 이상인 것에 대해서 JadenCase 문자열을 만든다.
            // 연속된 공백도 같은 과정을 반복한다.
            if (words[i].length() >= 1) {

                // 문자열을 문자 배열로 자른 뒤, 제일 앞의 문자만 대문자로 변환한다.
                char[] chars = words[i].toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);

                // 변환된 문자 배열을 String 으로 만들어 words 배열에 다시 넣는다.
                words[i] = new String(chars);
            }
        }

        // 다시 원래대로 문자열을 만든다.
        StringBuilder sb = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) sb.append(" ").append(words[i]);

        // 8번 테케는 아마 마지막에 공백이 있는 듯 하다.
        // 그래서 원래 문자열의 마지막이 공백이라면 split 하면서 잘려나간 공백을 다시 추가해준다.
        if (s.substring(s.length() - 1).equals(" ")) sb.append(" ");

        return sb.toString();
    }
}
