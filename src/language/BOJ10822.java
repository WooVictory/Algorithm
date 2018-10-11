package language;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10822 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String word = bf.readLine();
        String[] strArr = word.split(",");
        int sum=0;
        for(int i=0;i<strArr.length;i++)
            sum+=Integer.parseInt(strArr[i]);

        bw.write(sum+"\n");
        bw.flush();
        bw.close();
        bf.close();
    }
}
