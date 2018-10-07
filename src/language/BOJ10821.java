package language;

import java.io.*;

public class BOJ10821 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //StringTokenizer st = new StringTokenizer(bf.readLine(),",");

        String word = bf.readLine();
        String[] words = word.split(",");
        bw.write(words.length+"\n");
        bw.flush();
        bw.close();
    }
}
