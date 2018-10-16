package language;

import java.io.*;

public class BOJ10808 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] checkArr = new int[26];

        String input = bf.readLine();

        for(int i=0;i<26;i++)
            checkArr[i]=0;

        for(int i=0;i<input.length();i++) {
            int index = input.charAt(i) - 97;
            checkArr[index]++;
        }

        for(int i=0;i<26;i++){
            System.out.print(checkArr[i]+" ");
        }
    }
}
