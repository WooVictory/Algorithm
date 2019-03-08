package language;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ10815Re {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashSet<Integer> set = new HashSet<>();
        int N = Integer.parseInt(br.readLine());
        // default가 공백을 기준으로 나눔.
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            int num = Integer.parseInt(st.nextToken());
            if(set.contains(num)){
                sb.append(1+" ");
            }else {
                sb.append(0+" ");
            }
        }

        bw.write(sb.toString()+"\n");
        bw.flush();
        bw.close();
    }
}