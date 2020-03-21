package programmers;

/**
 * created by victory_woo on 2020/03/21
 * 문자열 내 p와 y의 개수
 * 소문자 혹은 대문자로 변환한 뒤, p를 만나면 count 증가 y를 만나면 count 감소.
 * count 가 0이면 p와 y의 갯수가 동일하기 때문에 true 반환. 아니라면 false 반환.
 */
public class PGM12916 {
    public static void main(String[] args) {
        String s = "pPoooyY";
        String s1= "Pyy";
        System.out.println(solution(s));
        System.out.println(solution(s1));
    }

    static boolean solution(String s) {
        int count = 0;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p') count++;

            if (s.charAt(i) == 'y') count--;
        }

        return count == 0;
    }
}
