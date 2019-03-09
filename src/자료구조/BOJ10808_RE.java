package 자료구조;

import java.io.*;

/**
 * 2019.03.08 알파벳 개수 품
 */
public class BOJ10808_RE {
    private static final int ASCII = 97;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] wordArray = new int[26];

        String word = br.readLine();

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - ASCII;
            wordArray[index]++;
        }

        for (int i = 0; i < wordArray.length; i++) {
            bw.write(wordArray[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();


    }
}
