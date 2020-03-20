package programmers;

/**
 * created by victory_woo on 2020/03/20
 * 가운데 글자 가져오기
 * 홀수인 경우는 가운데 글자만 가져오면 되고,
 * 짝수인 경우에는 가운데 글자 2개를 가져와야 한다.
 *
 * 문자열 길이의 반을 half 변수에 저장한다.
 * 그리고 짝수일 때는 half 이전 문자열을 가져와서 붙이고, 아닌 경우에는 half 문자열만 붙이도록 한다.
 */
public class PGM12903 {
    public static void main(String[] args) {
        String s = "abcded";
        System.out.println(solution(s));
    }

    public static String solution(String s) {
        String answer = "";
        int half = s.length() / 2;

        if (s.length() % 2 == 0) answer += String.valueOf(s.charAt(half - 1));

        answer += String.valueOf(s.charAt(half));
        return answer;
    }

}
