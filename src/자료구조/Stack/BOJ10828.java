package 자료구조.Stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 2019.3.8 알고리즘 스택 품
 * 예전 400ms -> 136ms로 단축
 * */
public class BOJ10828 {
    private static final String ENTER = "\n";

    public static void main(String[] args) throws IOException {

        // 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 스택
        Stack<Integer> stack = new Stack<>();

        int test_case = Integer.parseInt(br.readLine());

        for (int i = 0; i < test_case; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    int number = Integer.parseInt(st.nextToken());
                    stack.push(number);
                    break;
                case "pop":
                    if (stack.isEmpty()) {
                        bw.write(-1 + ENTER);
                    } else {
                        bw.write(stack.pop() + ENTER);
                    }
                    break;
                case "size":
                    bw.write(stack.size() + ENTER);
                    break;
                case "empty":
                    if (stack.isEmpty()) {
                        bw.write("1" + ENTER);
                    } else {
                        bw.write("0" + ENTER);
                    }
                    break;
                case "top":
                    if (stack.isEmpty()) {
                        bw.write(-1 + ENTER);
                    } else {
                        bw.write(stack.peek() + ENTER);
                    }
                    break;
            }
        }


        bw.flush();
        bw.close();
        br.close();

    }
}
