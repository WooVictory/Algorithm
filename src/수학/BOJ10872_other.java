package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 25/03/2019
 * 수학 : 팩토리얼 구하기.
 * 재귀 함수 호출을 이용한 다른 방법
 */
public class BOJ10872_other {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        long result = getFactorial(num);

        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private static long getFactorial(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }
        // 재귀 호출을 진행한다.
        return n * getFactorial(n - 1);
    }
}
