package 자료구조.Stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1406 {
    // 에디터 문제
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack leftStack = new Stack();
        Stack rightStack = new Stack();

        String input = bf.readLine();
        for (int i = 0; i < input.length(); i++)
            leftStack.push(input.charAt(i));

        int test_case = Integer.parseInt(bf.readLine());
        while (test_case-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();
            switch (command) {
                case "L":
                    // 왼쪽 스택에 있는 단어를 오른쪽 스택으로 옮김.
                    if (!leftStack.isEmpty()) {
                        rightStack.push(leftStack.pop());
                    }
                    break;
                case "D":
                    // 오른쪽 스택에 있는 단어를 왼쪽 스택으로 옮김.
                    if (!rightStack.isEmpty()) {
                        leftStack.push(rightStack.pop());
                    }
                    break;
                case "B":
                    // 삭제
                    if (!leftStack.isEmpty()) {
                        leftStack.pop();
                    }
                    break;
                case "P":
                    String word = st.nextToken();
                    leftStack.push(word);
                    break;

            }
        }
        StringBuilder sb = new StringBuilder();
        while (!leftStack.isEmpty())
            rightStack.push(leftStack.pop());


        // 스택에 들어가 있는 값을 원래대로 뽑기 위해서 오른쪽 스택에 집어넣고 뺌.
        while (!rightStack.isEmpty())
            sb.append(rightStack.pop());


        bw.write(sb.toString() + "");
        bw.flush();
        bw.close();

    }

}
