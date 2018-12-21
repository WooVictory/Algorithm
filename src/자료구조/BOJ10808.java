package 자료구조;

import java.io.*;

public class BOJ10808 {
    // 알파벳 갯수
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = bf.readLine();
        int[] words = new int[26];
        final int ASCII = 97;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - ASCII;
            words[index]++;
        }

        for (int i=0;i<words.length;i++)
            bw.write(words[i]+" ");

        bw.flush();
        bw.close();
        bf.close();
    }
}
