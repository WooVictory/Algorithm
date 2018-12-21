package 자료구조;

import java.io.*;

public class BOJ10809 {
    // 알파벳 찾기
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int ASCII = 97;
        String word = bf.readLine();

        // 알파벳의 위치를 나타낼 배열
        int[] words = new int[26];

        // -1로 초기화
        for (int i = 0; i < 26; i++)
            words[i] = -1;

        for (int i = 0; i < word.length(); i++) {
            // index는 알파벳 배열에서 알파벳 순서
            int index = word.charAt(i) - ASCII;
            if(words[index] == -1)
                words[index] = i;
            else
                continue;

        }

        for (int j=0;j<words.length;j++)
            bw.write(words[j]+" ");

        bw.flush();
        bw.close();
        bf.close();


    }
}
