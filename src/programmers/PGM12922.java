package programmers;

/**
 * created by victory_woo on 2020/03/21
 * 수박수박수....
 * 배열에 넣어놓고 인덱스 나누어 떨어지는지 아닌지 확인해서 StringBuilder 사용해서 붙여서 반환하면 된다.
 */
public class PGM12922 {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static String solution(int n) {
        String[] s = {"수", "박"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) sb.append(s[0]);
            else sb.append(s[1]);
        }
        return sb.toString();
    }
}
