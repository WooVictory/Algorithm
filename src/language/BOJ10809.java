package language;

import java.io.*;

public class BOJ10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] checkArr = new int[26];
        for(int i=0;i<checkArr.length;i++)
            checkArr[i]=-1;

        String input = bf.readLine();
        for(int i=0;i<input.length();i++){
            int idx = input.charAt(i)-97;
            if((checkArr[idx]) == -1){
                checkArr[idx] = i;
            }else
                continue;
        }

        for(int i=0;i<26;i++)
            bw.write(checkArr[i]+" ");
        bw.flush();
        bw.close();
        bf.close();
    }
}
