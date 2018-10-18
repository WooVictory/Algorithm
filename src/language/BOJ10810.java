package language;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ10810 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(bf.readLine()," ");
        int N = Integer.parseInt(st1.nextToken());
        //List<Integer> list = new ArrayList<>(N);
        int[] intArr = new int[N];
        int M = Integer.parseInt(st1.nextToken());


        for(int p=0;p<M;p++){
            st1 = new StringTokenizer(bf.readLine()," ");
            int i = Integer.parseInt(st1.nextToken());
            int j = Integer.parseInt(st1.nextToken());
            int k = Integer.parseInt(st1.nextToken());
            for(int m=i-1;m<j;m++){
                //list.add(k);
                intArr[m] = k;
            }

        }

        for(int i=0;i<intArr.length;i++)
            System.out.print(intArr[i]+" ");
    }
}
