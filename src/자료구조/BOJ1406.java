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

        // 커서를 기준으로 스택 두 개 사용.
        // 커서 왼쪽에 있는 단어는 left_stack에 저장.
        // 커서 오른쪽에 있는 단어는 right_stack에 저장.
        Stack<Character> left_stack = new Stack<>();
        Stack<Character> right_stack = new Stack<>();

        String input = br.readLine();

        // 초기 문자열을 left_stack에 넣는다.
        for (int i = 0; i < input.length(); i++) {
            left_stack.add(input.charAt(i));
        }
        int test_case = Integer.parseInt(br.readLine());
        while (test_case-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "L": // 커서를 왼쪽으로 한 칸 이동하므로 left_stack -> right_stack로 이동.
                    if (!left_stack.isEmpty()) {
                        right_stack.push(left_stack.pop());
                    }
                    break;
                case "D": // 커서를 오른쪽으로 한 칸 이동하므로 right_stack -> left_stack로 이동.
                    if (!right_stack.isEmpty()) {
                        left_stack.push(right_stack.pop());
                    }
                    break;
                case "B": // 스택이 비어있지 않다면 커서 왼쪽에 있는 단어 삭제.
                    if (left_stack.isEmpty()) {
                        break;
                    } else {
                        left_stack.pop();
                    }
                    break;
                case "P": // left_stack에 단어 추가.
                    String word = st.nextToken();
                    left_stack.push(word.charAt(0));
                    break;
            }
        }

        // left_stack에 있는 단어를 모두 right_stack으로 이동시킴.
        while (!left_stack.isEmpty()) {
            right_stack.push(left_stack.pop());
        }

        // right_stack에 있는 단어를 빼면서 원하는 순서로 출력.
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
