package 자료구조;

import java.io.*;
/**
 * 2019.03.08 단어 길이 품
 * 76ms
 * */
public class BOJ2743_RE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();

        bw.write(word.length() + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
