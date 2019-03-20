package 수학;

import java.io.*;

/**
 * created by victory_woo on 20/03/2019
 * 수학 : 나머지
 */
public class BOJ10430 {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(SPACE);

        int a = convert(input[0]);
        int b = convert(input[1]);
        int c = convert(input[2]);

        bw.write(divideOne(a, b, c) + NEW_LINE);
        bw.write(divideTwo(a, b, c) + NEW_LINE);
        bw.write(divideThree(a, b, c) + NEW_LINE);
        bw.write(divideFour(a, b, c) + NEW_LINE);

        bw.flush();
        bw.close();
        br.close();

    }

    private static int convert(String input) {
        return Integer.parseInt(input);
    }

    private static int divideOne(int a, int b, int c) {
        return (a + b) % c;
    }

    private static int divideTwo(int a, int b, int c) {
        return ((a % c) + (b % c)) % c;
    }

    private static int divideThree(int a, int b, int c) {
        return (a * b) % c;
    }

    private static int divideFour(int a, int b, int c) {
        return ((a % c) * (b % c)) % c;
    }
}
