package 수학;

import java.io.*;

/**
 * created by victory_woo on 20/03/2019
 * 수학 : GCD 합
 */
public class BOJ9613 {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            long result = 0;
            String[] input = br.readLine().split(SPACE);
            int n = Integer.parseInt(input[0]);
            int[] a = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(input[i]);
            }

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    result += gcm(a[i], a[j]);
                }
            }
            bw.write(result + NEW_LINE);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static long gcm(long a, long b) {
        while (b > 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}
