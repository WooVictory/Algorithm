package programmers;

/**
 * created by victory_woo on 2020/03/23
 * 제곱근 판별.
 */
public class PGM12934 {
    public static void main(String[] args) {
        System.out.println(solution(121));
        System.out.println(solution(3));
    }

    public static long solution(long n) {
        long x = (long) (Math.sqrt(n));
        if ((x * x) == n) return (x + 1) * (x + 1);
        else return -1;
    }
}
