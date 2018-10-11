package language;

import java.io.*;

public class BOJ2743 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = bf.readLine();
        bw.write(word.length()+"\n");
        bw.flush();
        bw.close();
        bf.close();
    }
}
