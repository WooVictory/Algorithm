package language;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test_case = Integer.parseInt(bf.readLine());
        LinkedList<Integer> queue = new LinkedList<>();

        for(int i=0;i<test_case;i++){
            StringTokenizer st= new StringTokenizer(bf.readLine()," ");
            String commnad = st.nextToken();
            switch (commnad){
                case "push":
                    int number = Integer.parseInt(st.nextToken());
                    queue.add(number);
                    break;
                case "front":
                    if(queue.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(queue.peekFirst()+"\n");
                    break;
                case "pop":
                    if(queue.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(queue.poll()+"\n");
                    break;
                case "size":
                    bw.write(queue.size()+"\n");
                    break;
                case "empty":
                    if (queue.isEmpty())
                        bw.write(1+"\n");
                    else
                        bw.write(0+"\n");
                    break;
                case "back":
                    if(queue.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(queue.peekLast()+"\n");
                    break;
            }
        }

        bw.flush();
        bw.close();

    }
}
