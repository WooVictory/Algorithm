package language;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10813 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];

        for(int i=1;i<=N;i++)
            arr[i]=i;

        for(int k=0;k<M;k++){
            st = new StringTokenizer(bf.readLine()," ");
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        for(int i=1;i<arr.length;i++)
            bw.write(arr[i]+" ");

        bw.flush();
        bw.close();
        bf.close();
    }
}
