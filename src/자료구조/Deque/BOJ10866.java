package 자료구조.Deque;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ10866 {
    // deque 문제 - 시간 단축
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        ArrayDeque<Integer> deque = new ArrayDeque<>();



        int test_case = Integer.parseInt(bf.readLine());

        while (test_case-->0){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            switch (command){
                case "push_front":
                    int num = Integer.parseInt(st.nextToken());
                    deque.addFirst(num); // 덱의 앞에 숫자 대입
                    break;
                case "push_back":
                    int num2 = Integer.parseInt(st.nextToken());
                    deque.addLast(num2);
                    break;
                case "pop_front":
                    if (!deque.isEmpty())
                        bw.write(deque.pollFirst()+"\n");
                    else
                        bw.write(-1+"\n");
                    break;
                case "pop_back":
                    if (!deque.isEmpty())
                        bw.write(deque.pollLast()+"\n");
                    else
                        bw.write(-1+"\n");

                    break;
                case "size":
                    bw.write(deque.size()+"\n");
                    break;
                case "empty":
                    if (!deque.isEmpty())
                        bw.write(0+"\n");
                    else
                        bw.write(1+"\n");
                    break;
                case "front":
                    if (!deque.isEmpty())
                        bw.write(deque.peekFirst()+"\n");
                    else
                        bw.write(-1+"\n");
                    break;
                case "back":
                    if (!deque.isEmpty())
                        bw.write(deque.peekLast()+"\n");
                    else
                        bw.write(-1+"\n");
                    break;
            }
        }
        bw.flush();
        bw.close();

    }
}
