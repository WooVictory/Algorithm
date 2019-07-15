package 백트래킹;

import java.io.*;

/**
 * created by victory_woo on 15/07/2019
 * 로또
 * 암호 만들기 문제와 비슷하다.
 */
public class BOJ6603 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder sb;
    private static final int SIX = 6;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        while (true) {
            String[] in = br.readLine().split(" ");
            sb = new StringBuilder();
            int k = parse(in[0]);

            if (k == 0) {
                break;
            }

            a = new int[k + 1];

            for (int i = 1; i <= k; i++) {
                a[i] = parse(in[i]);
            }

            dfs("", 1, 0);
            bw.write(sb.toString() + "\n");
        }

        bw.flush();
    }

    private static void dfs(String lotto, int index, int count) {
        if (count == SIX) {
            sb.append(lotto).append("\n");
            return;
        }

        if (index >= a.length) {
            return;
        }

        dfs(lotto + a[index] + " ", index + 1, count + 1);
        dfs(lotto, index + 1, count);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}