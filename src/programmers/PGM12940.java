package programmers;

/**
 * created by victory_woo on 2020/03/24
 * 최대공약수와 최소공배수.
 * 유클리드 호제법을 사용한다.
 */
public class PGM12940 {
    public static void main(String[] args) {
        solution(3, 12);
        solution(2, 5);
    }

    public static int[] solution(int n, int m) {
        int[] answer = new int[2];
        int mod = n % m;
        int gcd = n * m;
        // 미리 구해놓지 않으면, 아래 while 문에서 값이 바뀌게 된다.
        while (mod > 0) {
            n = m;
            m = mod;
            mod = n % m;
        }

        answer[0] = m;
        answer[1] = gcd / m;
        return answer;
    }
}
