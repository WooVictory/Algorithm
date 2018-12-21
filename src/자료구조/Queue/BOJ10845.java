package 자료구조.Queue;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10845 {
    // queue 문제
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<Integer> queue = new LinkedList<>();

        int T = Integer.parseInt(bf.readLine());
        while(T-->0){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            switch (command){
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    /*FIXME
                    * add와 push는 0다르게 동작하므로 알고 있어야 함.
                    * */
                    queue.add(num);
                    break;
                case "pop":
                    if(!queue.isEmpty())
                        bw.write(queue.poll()+"\n");
                    else
                        bw.write(-1+"\n");
                    break;
                case "size":
                    bw.write(queue.size()+"\n");
                    break;
                case "empty":
                    if(!queue.isEmpty())
                        bw.write(0+"\n");
                    else
                        bw.write(1+"\n");
                    break;
                case "front":
                    if (!queue.isEmpty())
                        bw.write(queue.peekFirst()+"\n");
                    else
                        bw.write(-1+"\n");
                    break;
                case "back":
                    if(!queue.isEmpty())
                        bw.write(queue.peekLast()+"\n");
                    else
                        bw.write(-1+"\n");
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}
