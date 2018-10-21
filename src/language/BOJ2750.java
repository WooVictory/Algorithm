package language;

import java.io.*;
import java.util.Arrays;

public class BOJ2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            int number = Integer.parseInt(bf.readLine());
            arr[i] = number;
        }

        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++)
            bw.write(arr[i]+"\n");
        bw.flush();
        bw.close();
        bf.close();



    }
}
