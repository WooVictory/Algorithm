package 자료구조;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class BOJ2743 {
    // 단어 길이 재기
    public static void main(String[] args) throws IOException {
       /* BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = bf.readLine();
        bw.write(input.length()+"");
        bw.flush();
        bw.close();
        bf.close();*/
        HashMap map = new HashMap();
        map.put(1, "kim");
        map.put(1, "lee");
        map.put(1, "Jung");
        map.put(1, "Park");

        System.out.println(map.size());
        System.out.println(map.get(1));

    }


}
class Card{
    static int cnt ;
    int data;
    Card(int data){
        this.data = data;
        cnt++;
    }
}

