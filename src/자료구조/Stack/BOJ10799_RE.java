package 자료구조.Stack;

import java.io.*;
import java.util.Stack;
/**
 * 2019.03.08 쇠막대기 품
 * 132ms 걸림
 * */

public class BOJ10799_RE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        bw.write(solution(input) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(String input) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') { // 여는 괄호
                stack.push(input.charAt(i));
            } else if (input.charAt(i) == ')') { // 닫는 괄호
                stack.pop();
                if (input.charAt(i - 1) == '(') { // 여는 괄호 => 레이저
                    count += stack.size();
                } else { // 쇠막대기
                    count += 1;
                }
            }
        }
        return count;
    }
}
