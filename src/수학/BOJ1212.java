package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 22/03/2019
 * 수학 : 8진수 2진수
 * 비효율적 노가다...?
 */
public class BOJ1212 {
    private static final int ASCII = 48;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        sb = new StringBuilder();
        int length = input.length();

        for (int i = 0; i < length; i++) {
            int ch = input.charAt(i) - ASCII;
            convert(ch, i);
        }

        bw.write(sb.toString()+"");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void convert(int ch, int i) {
        switch (ch) {
            case 0:
                if (i == 0) {
                    sb.append("0");
                } else {
                    sb.append("000");
                }
                break;
            case 1:
                if (i == 0) {
                    sb.append("1");
                } else {
                    sb.append("001");
                }
                break;
            case 2:
                if (i == 0) {
                    sb.append("10");
                } else {
                    sb.append("010");
                }
                break;
            case 3:
                if (i == 0) {
                    sb.append("11");
                } else {
                    sb.append("011");
                }
                break;
            case 4:
                sb.append("100");
                break;
            case 5:
                sb.append("101");
                break;
            case 6:
                sb.append("110");
                break;
            case 7:
                sb.append("111");
                break;
        }
    }
}
