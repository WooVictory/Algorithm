package language;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<Integer> list = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder("<");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            list.add(i+1);
        }

        int index=0;
        while(!list.isEmpty()){
            index +=(M-1);

            if(index>=list.size()){
                index = index % list.size();
            }

            sb.append(list.remove(index)+", ");

        }


        //bw.write(sb.toString()+"\n");
        String result = sb.substring(0, sb.length()-2);
        bw.write(result+">");
        bw.flush();
        bw.close();
    }
}
