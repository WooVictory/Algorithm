package language;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10807 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bf.readLine());
        int[] intArr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        for(int i=0;i<N;i++){
            int value = Integer.parseInt(st.nextToken());
            intArr[i] = value;
        }

        int count = 0;
        int res = Integer.parseInt(bf.readLine());
        for(int i=0;i<N;i++){
            if(intArr[i] == res)
                count++;
        }

        bw.write(count+"\n");
        bw.flush();
        bw.close();
        bf.close();
    }
}
