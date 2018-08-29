package 입출력;

import java.io.*;
import java.util.*;

public class BOJ10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> deque = new ArrayDeque<>();


        int test_case = Integer.parseInt(bf.readLine());
        for(int i=0;i<test_case;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            String command = st.nextToken();
            int num = 0;
            switch (command){
                case "push_front":
                    num = Integer.parseInt(st.nextToken());
                    deque.addFirst(num);
                    break;
                case "push_back":
                    num = Integer.parseInt(st.nextToken());
                    deque.addLast(num);
                    break;
                case "pop_front":
                    if(deque.isEmpty()){
                        System.out.println(-1);
                    }else{
                        System.out.println((deque.removeFirst()));
                    }
                    break;
                case "pop_back":
                    if(deque.isEmpty()){
                        System.out.println(-1);
                    }else{
                        System.out.println((deque.removeLast()));
                    }
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    if(deque.isEmpty()){
                        System.out.println(1);
                    }else {
                        System.out.println(0);
                    }
                    break;
                case "front":
                    if(deque.isEmpty()){
                        System.out.println(-1);
                    }else {
                        System.out.println(deque.peekFirst());
                    }
                    break;
                case "back":
                    if(deque.isEmpty()){
                        System.out.println(-1);
                    }else {
                        System.out.println(deque.peekLast());
                    }
                    break;
            }

        }

    }

}
