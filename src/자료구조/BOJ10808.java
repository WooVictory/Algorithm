package 자료구조;

import java.io.*;

/**
 * 2019.03.08
 * 소문자만으로 이루어짐.
 * 아스키 코드 사용
 */
public class BOJ10808 {
    // 알파벳 갯수
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = bf.readLine();
        int[] wordArray = new int[26];
        // 아스키 코드 사용.
        final int ASCII = 97;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - ASCII;
            wordArray[index]++;
        }

        for (int words : wordArray) {
            bw.write(words + " ");
        }

        bw.flush();
        bw.close();
        bf.close();
    }
}
