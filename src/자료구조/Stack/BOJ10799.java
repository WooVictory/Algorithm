package 자료구조.Stack;

import java.io.*;
import java.util.Stack;

public class BOJ10799 {
    // stack 쇠막대기 문제
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(solution(bf.readLine())+"");
        bw.flush();
        bw.close();

    }

    public static int solution(String str) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') { // 여는 괄호
                stack.push(str.charAt(i));
            } else if (str.charAt(i) == ')') { // 닫는 괄호
                if (str.charAt(i - 1) == '(') { // 레이저인 경우
                    stack.pop(); // 여는 괄호 제거해줘야됨.
                    count += stack.size();

                } else { // 레이저가 아닌 경우 쇠막대기 하나가 끝난 경우
                    stack.pop(); // 여는 괄호 제거해줘야됨.
                    count += 1;

                }
            }
        }

        return count;

    }
}
