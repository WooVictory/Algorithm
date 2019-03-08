package 자료구조.Deque;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
/**
 * 2019.03.08 덱 품
 * 144ms
 * */
public class BOJ10866_RE {
    private static final String ENTER = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < test_case; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int number;
            switch (command) {
                case "push_front":
                    number = Integer.parseInt(st.nextToken());
                    arrayDeque.addFirst(number);
                    break;
                case "push_back":
                    number = Integer.parseInt(st.nextToken());
                    arrayDeque.addLast(number);
                    break;
                case "pop_front":
                    if (arrayDeque.isEmpty()) {
                        bw.write(-1 + ENTER);
                    } else {
                        bw.write(arrayDeque.pollFirst() + ENTER);
                    }
                    break;
                case "pop_back":
                    if (arrayDeque.isEmpty()) {
                        bw.write(-1 + ENTER);
                    } else {
                        bw.write(arrayDeque.pollLast() + ENTER);
                    }
                    break;
                case "size":
                    bw.write(arrayDeque.size() + ENTER);
                    break;
                case "empty":
                    if (arrayDeque.isEmpty()) {
                        bw.write(1 + ENTER);
                    } else {
                        bw.write(0 + ENTER);
                    }
                    break;
                case "front":
                    if (arrayDeque.isEmpty()) {
                        bw.write(-1 + ENTER);
                    } else {
                        bw.write(arrayDeque.peekFirst() + ENTER);
                    }
                    break;
                case "back":
                    if (arrayDeque.isEmpty()) {
                        bw.write(-1 + ENTER);
                    } else {
                        bw.write(arrayDeque.peekLast() + ENTER);
                    }
                    break;
            }
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
