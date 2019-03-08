package 자료구조;

import java.io.*;

/**
 * created by victory_woo on 08/03/2019
 */
public class BOJ11655 {
    private static final int ROT_13 = 13;
    private static final int SMALL_LETTER_A = 97;
    private static final int SMALL_LETTER_Z = 122;
    private static final int BIG_LETTER_A = 65;
    private static final int BIG_LETTER_Z = 90;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int idx = input.charAt(i);
            if (SMALL_LETTER_A <= idx && idx <= SMALL_LETTER_Z) {
                int value = idx + ROT_13;
                if (input.charAt(i) + ROT_13 > SMALL_LETTER_Z) {
                    value = input.charAt(i) - ROT_13;
                }
                char word = (char) (value);
                sb.append(word);
            } else if (BIG_LETTER_A <= idx && idx <= BIG_LETTER_Z) {
                int value = idx + ROT_13;
                if (input.charAt(i) + ROT_13 > BIG_LETTER_Z) {
                    value = input.charAt(i) - ROT_13;
                }
                char word = (char) (value);
                sb.append(word);
            } else {
                sb.append((char) idx);
            }
        }

        bw.write(sb.toString()+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
