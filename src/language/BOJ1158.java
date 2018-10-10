package language;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<Integer> q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            q.add(i+1);
        }

        for(int i=0;i<q.size();i++){
            bw.write(q.size()+"\n");
            bw.write("removed 원소 : "+q.remove(M)+"\n");
            //q.add(M, -1);
        }
        //bw.write(q.remove(2)+"\n");

        bw.write(q+"\n");
        bw.flush();
        bw.close();

    }
}
