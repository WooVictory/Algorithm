package programmers;

/**
 * created by victory_woo on 2020/03/23
 * 히샤드 수.
 * 정수 x의 각 자릿수의 합으로 x가 나누어 떨어지는지 아닌지 확인하는 문제이다.
 * 간단쓰~
 */
public class PGM12947 {
    public static void main(String[] args) {
        System.out.println(solution(18));
    }

    public static boolean solution(int x) {
        int sum = 0, copy = x;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }

        return copy % sum == 0;
    }
}
