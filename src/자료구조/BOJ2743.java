package 자료구조;

import java.io.*;

public class BOJ2743 {
    // 단어 길이 재기
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = bf.readLine();
        bw.write(input.length()+"");
        bw.flush();
        bw.close();
        bf.close();

    }
}

