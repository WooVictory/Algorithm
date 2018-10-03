package language;

import java.io.*;
import java.util.*;

public class BOJ1764 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf=  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st= new StringTokenizer(bf.readLine()," ");
        Set<String> noEarSett = new HashSet<>();
        List<String> result = new ArrayList<>();


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++) {
            noEarSett.add(bf.readLine());
        }


        for(int i=0;i<M;i++){
            String person = bf.readLine();
            if(noEarSett.contains(person)){
                result.add(person);
            }
        }
        StringBuilder sb= new StringBuilder();
        Collections.sort(result);
        sb.append(result.size()+"\n");
        for (String str :
                result) {
            sb.append(str+"\n");

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
