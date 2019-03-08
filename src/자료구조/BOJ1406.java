package 자료구조;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 09/03/2019
 */
public class BOJ1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> left_stack = new Stack<>();
        Stack<Character> right_stack = new Stack<>();

        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            left_stack.add(input.charAt(i));
        }
        int test_case = Integer.parseInt(br.readLine());
        while (test_case-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "L":
                    if (!left_stack.isEmpty()) {
                        right_stack.push(left_stack.pop());
                    }
                    break;
                case "D":
                    if (!right_stack.isEmpty()) {
                        left_stack.push(right_stack.pop());
                    }
                    break;
                case "B":
                    if (left_stack.isEmpty()) {
                        break;
                    } else {
                        left_stack.pop();
                    }
                    break;
                case "P":
                    String word = st.nextToken();
                    left_stack.push(word.charAt(0));
                    break;
            }
        }

        while (!left_stack.isEmpty()) {
            right_stack.push(left_stack.pop());
        }

        StringBuilder sb= new StringBuilder();
        while (!right_stack.isEmpty()) {
            sb.append(right_stack.pop());
        }

        bw.write(sb.toString()+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
