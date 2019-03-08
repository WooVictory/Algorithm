package 자료구조;

import java.io.*;
/**
 * 2019.03.08 문자열 분석 품
 * */
public class BOJ10820_RE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;

        while ((input = br.readLine()) != null) {
            int small_count = 0;
            int big_count = 0;
            int number_count = 0;
            int space_count = 0;
            for (int i = 0; i < input.length(); i++) {
                int index = input.charAt(i);
                if (97 <= index && index <= 122) {
                    small_count++;
                } else if (65 <= index && index <= 90) {
                    big_count++;
                } else if (48 <= index && index <= 57) {
                    number_count++;
                } else if (index == 32) {
                    space_count++;
                }
            }
            System.out.print(small_count + " ");
            System.out.print(big_count + " ");
            System.out.print(number_count + " ");
            System.out.print(space_count + "\n");

            bw.write(small_count + " ");
            bw.write(big_count + " ");
            bw.write(number_count + " ");
            bw.write(space_count + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
