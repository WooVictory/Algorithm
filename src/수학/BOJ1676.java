package 수학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * created by victory_woo on 25/03/2019
 * 수학 : 팩토리얼 0의 개수
 * 결국 5의 배수와 25의 배수를 구하는 문제로 바꿔서 풀 수 있다.
 * 팩토리얼의 값을 구하기 위해서는 소인수 분해를 통해서 값을 구해야 한다.
 * 하지만 문제의 특성상 소인수 분해를 하지 않고도 문제를 풀 수 있다.
 */
public class BOJ1676 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int result = 0;

        for (int i = 5; i <= n; i = i * 5) {
            result += n / i;
        }

        System.out.println(result);

    }
}
