package language;

import java.io.*;
import java.util.*;

public class BOJ1927 {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(bf.readLine());
            if (x == 0) {
                if (!priorityQueue.isEmpty())
                    bw.write(priorityQueue.poll() + "\n");
                else
                    bw.write(0 + "\n");
            } else {
                priorityQueue.add(x);
            }
        }


        bw.flush();
        bw.close();

    }

}
