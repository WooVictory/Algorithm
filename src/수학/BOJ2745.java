package 수학;

import java.io.*;

/**
 * created by victory_woo on 21/03/2019
 * 수학 : 진법변환
 * B진법을 10진법으로
 */
public class BOJ2745 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        String word = input[0];
        int b = Integer.parseInt(input[1]);
        long result = 0;

        for (int i = 0; i < word.length(); i++) {
            //char ch = word.charAt(i);
            int ch = word.charAt(i);
          /*  if ('0' <= ch && ch <= '9') {
                result = result * b + (ch - '0');
            } else {
                result = result * b + (ch - 'A' + 10);
            }*/
            if (48 <= ch && ch <= 57) {
                result = result * b + (ch - 48);
            } else {
                result = result * b + (ch - 55);
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();

    }
}
