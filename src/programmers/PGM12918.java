package programmers;

/**
 * created by victory_woo on 2020/03/20
 * 프로그래머스 12918
 * 예외 처리를 생각해야 하는 문제!
 * 문자열에서 숫자로만 구성되어 있는지 확인하는 문제이다.
 * 이 문제를 풀기 위해서 숫자의 아스키 값을 확인해서 그 범위 밖으로 벗어나면 false 를 반환한다.
 * 즉, 하나라도 숫자가 아닌 문자가 존재하면 false
 * 이 조건문을 안타면 return true 를 한다. 즉, 문자열이 숫자로만 구성되어 있음을 뜻한다.
 */
public class PGM12918 {
    public static void main(String[] args) {
        String s = "a23423232";
        String s1 = "1234";
        System.out.println(solution(s1));
    }

    public static boolean solution(String s) {
        if (s.length() != 4 && s.length() != 6) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }
}
