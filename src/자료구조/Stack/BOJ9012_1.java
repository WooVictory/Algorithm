package 자료구조.Stack;

import java.io.*;
import java.util.Stack;

public class BOJ9012_1 {
    // 스택 괄호 문제 - 이전에 비해 시간 단축!
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(bf.readLine());
        for (int i = 0; i < T; i++) {
            if (solution(bf.readLine()))
                bw.write("YES\n");
            else
                bw.write("NO\n");
        }

        bw.flush();
        bw.close();
    }

    public static boolean solution(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') { // 여는 괄호
                stack.push(str.charAt(i));
            } else if (str.charAt(i) == ')') {// 닫는 괄호
                if (!stack.isEmpty())
                    stack.pop();
                else
                    return false;
            }
        }
        // stack이 비어있는 지를 리텀함으로써
        // 스택이 비어있으면 짝이 맞는 괄호가 들어왔음을 알 수 있다.
        return stack.empty();
    }
}
