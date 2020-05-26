package programmers;

import java.math.BigInteger;

/**
 * created by victory_woo on 2020/05/26
 * 피보나치 수.
 */
public class PGM12945 {
    public static void main(String[] args) {
        System.out.println(solution(3));
        System.out.println(solution(5));
    }

    private static final int MAX = 100000;

    public static int solution(int n) {
        // 나눠야 할 값을 BigInteger 변수로 만든다.
        BigInteger mod = BigInteger.valueOf(1234567);

        // 범위가 커서 오버플로우가 발생하기 때문에 BigInteger 타입의 배열을 사용한다.
        BigInteger[] f = new BigInteger[MAX + 1];
        f[0] = BigInteger.valueOf(0);
        f[1] = BigInteger.valueOf(1);

        // add() 메소드를 사용한다.
        for (int i = 2; i <= MAX; i++) {
            f[i] = f[i - 1].add(f[i - 2]);
        }

        // mod 값으로 나눠주고 intValue()를 이용해 BigInteger -> int 값을 반환하도록 변환한다.
        return f[n].mod(mod).intValue();
    }
}
