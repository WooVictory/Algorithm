package 자료구조;

import java.io.*;
import java.util.Arrays;
/**
 * 2019.03.08 접미사 배열 품
 * 108ms
 * */
public class BOJ11656 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String[] arr = new String[input.length()];
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            arr[i] = input.substring(i);
        }

        Arrays.sort(arr);
        for (int i = 0; i < length; i++) {
            bw.write(arr[i] + NEW_LINE);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
