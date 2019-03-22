package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 22/03/2019
 * 수학 : 소수 찾기 - 기본적인 방법 2번째
 * 불필요한 반복문을 수행하지 않고 건너뛴다.
 * 72ms
 */
public class BOJ1978_2 {
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

            if(number == 1){
                continue;
            }

            for (int j = 2; j < number; j++) {
                if (number % j == 0) {
                    isPrime = false;
                    break;
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
