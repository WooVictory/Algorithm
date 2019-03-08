package 자료구조;

import java.io.*;
/**
 * 2019.03.08 알파벳 찾기 품
 * 72ms 걸림
 * */
public class BOJ10809_RE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = new int[26];
        final int ASCII = 97;

        // 초기화
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }

        String word = br.readLine();

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - ASCII;
            if (array[index] == -1) {
                array[index] = i;
            } else {
                continue;
            }
        }

        for (int i = 0; i < array.length; i++) {
            bw.write(array[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
