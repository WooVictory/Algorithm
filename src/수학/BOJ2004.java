package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 25/03/2019
 * 수학 : 조합 0의 개수.
 */
public class BOJ2004 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        long n = Long.parseLong(input[0]);
        long m = Long.parseLong(input[1]);

        // n!의 2의 배수 구하기
        long two = getCountCombination(2, n);
        long five = getCountCombination(5, n);

        // m!의 2의 배수 구하기
        if (m != 0) {
            two -= getCountCombination(2, m);
            five -= getCountCombination(5, m);
        }

        // (n-m)!의 2의 배수 구하기
        if (n - m != 0) {
            two -= getCountCombination(2, n - m);
            five -= getCountCombination(5, n - m);
        }

        bw.write(Math.min(two, five) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    // 2의 배수 개수를 구함.
    private static long getCountCombination(int number, long n) {
        long count = 0;
        for (long i = number; i <= n; i = i * number) {
            count += n / i;
        }
        return count;
    }
}
