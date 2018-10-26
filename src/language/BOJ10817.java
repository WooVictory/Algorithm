package language;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10817 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int a = Integer.parseInt(st.nextToken())
                , b = Integer.parseInt(st.nextToken())
                , c = Integer.parseInt(st.nextToken());

        int mid = 0;
        if(a>b){
            if(a>c){
                if(b>c){
                    mid=b;
                }else {
                    mid=c;
                }
            }else
                mid=a;
        } else if(b>c){
            if(a>c)
                mid=a;
            else
                mid=c;
        }else {
            mid=b;
        }

        bw.write(mid+"\n");
        bw.flush();
        bw.close();
        bf.close();


    }
}
