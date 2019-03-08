package 자료구조.Queue;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * 2019.03.08 큐 품
 * 76ms
 * */

public class BOJ10845_RE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<Integer> queue = new LinkedList<>();
        int test_case = Integer.parseInt(br.readLine());

        while (test_case-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            /*
             * push : 큐의 앞에 삽입(front 삽입)
             * add : 큐의 뒤에 삽입(rear 삽입)
             * */

            switch (command) {
                case "push":
                    int number = Integer.parseInt(st.nextToken());
                    queue.add(number);
                    break;
                case "pop":
                    if (queue.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(queue.poll() + "\n");
                    }
                    break;
                case "size":
                    bw.write(queue.size() + "\n");
                    break;
                case "empty":
                    if (queue.isEmpty()) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case "front":
                    if (queue.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(queue.peekFirst() + "\n");
                    }
                    break;
                case "back":
                    if (queue.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(queue.peekLast() + "\n");
                    }
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
