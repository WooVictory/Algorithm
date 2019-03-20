package 수학;

import java.io.*;

/**
 * created by victory_woo on 20/03/2019
 * 수학 : 최소공배수
 */
public class BOJ1934 {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] input = br.readLine().split(SPACE);
            long a = Long.parseLong(input[0]);
            long b = Long.parseLong(input[1]);
            bw.write(lcm(a, b) + NEW_LINE);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static long gcm(long a, long b) {
        while (b > 0) {
            long tmp = b;
            b = a % b;
            a = tmp;
        }

        return a;
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcm(a, b);
    }

}
