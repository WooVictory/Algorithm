package 수학;

import java.io.*;

/**
 * created by victory_woo on 21/03/2019
 * 수학 : 진법 변환2
 * n 10억까지 가능
 * A=10, B=11, ... Z = 35
 * 아스키 코드표에서 대문자랑 55 차이남.
 * 10진법을 B 진법으로
 */
public class BOJ11005 {
    private static final int ASCII = 55;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        long n = Long.parseLong(input[0]); // 입력 숫자
        int b = Integer.parseInt(input[1]); // 변환할 진법
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            if ((n % b) < 10) { // 10보다 작을 경우
                sb.append(n % b);
            } else {
                long value = (n % b) + ASCII;
                sb.append((char) value);
            }
            n /= b; // 나눠준다.

        }

        bw.write(sb.reverse().toString() + "");

        bw.flush();
        bw.close();
        br.close();
        //System.out.println(sb.toString());
    }
}
