package 자료구조;

import java.io.*;
/**
 * 2019.03.08 네 수 품
 * 76ms
 * 자료형 Long형
 * */
public class BOJ10824 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();
        String[] array = word.split(" ");
        String first = array[0] + array[1];
        String last = array[2] + array[3];

        Long result = Long.parseLong(first) + Long.parseLong(last);
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();

    }
}
