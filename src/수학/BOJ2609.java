package 수학;

import java.io.*;

/**
 * created by victory_woo on 20/03/2019
 * 수학 : 최대공약수 최소공배수
 * 유클리드 호제법으로 접근
 */
public class BOJ2609 {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(SPACE);

        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);

        bw.write(gcm(a, b) + NEW_LINE);
        bw.write(lcm(a, b) + NEW_LINE);

        bw.flush();
        bw.close();
        br.close();

    }

    private static int gcm(int a, int b) {
        while (b > 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }

        return a;
    }

    private static int lcm(int a, int b) {
        return (a * b) / gcm(a, b);
    }
}
