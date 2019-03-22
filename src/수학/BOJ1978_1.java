package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 22/03/2019
 * 수학 : 소수 찾기 - 기본적인 방법
 * 기본적인 방법인만큼 쉽지만 좋은 방법은 아니다. 비효율적인 부분이 있다.
 * 76ms
 */
public class BOJ1978_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int count = 0;
        boolean isPrime;
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < t; i++) {
            isPrime = true;
            int number = Integer.parseInt(input[i]);
            if (number == 1) {
                continue;
            }

            // 1과 자기 자신(number)를 제외한 수로 나눠떨어진다면 소수가 아님.
            for (int j = 2; j < number; j++) {
                if (number % j == 0) {
                    isPrime = false;
                }
            }

            if (isPrime) {
                count++;
            }
        }

        bw.write(count + "");

        bw.flush();
        bw.close();
        br.close();

    }
}
