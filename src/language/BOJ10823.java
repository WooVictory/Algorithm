package language;

import java.io.*;
import java.util.Scanner;

public class BOJ10823 {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringBuilder sb= new StringBuilder();
        int sum=0;
        while(true){
            String input = bf.readLine();
            if(input == null) break;

            for(int i=0;i<input.length();i++){
                if(input.charAt(i) == ','){
                    sum+=Integer.parseInt(sb.toString());
                    sb = new StringBuilder(); // 초기화
                }else {
                    sb.append(input.charAt(i));
                }
            }
        }
        bw.write(sum+Integer.parseInt(sb.toString())+"\n");
        bw.flush();
        bw.close();
        bf.close();
    }
}
