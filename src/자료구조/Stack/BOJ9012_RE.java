package 자료구조.Stack;

import java.io.*;
import java.util.Stack;
/**
 * 2019.03.08 괄호 문제 품
 * 84ms -> 80ms 단축
 * */
public class BOJ9012_RE {

    private static final String ENTER = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = Integer.parseInt(br.readLine());

        for (int i = 0; i < test_case; i++) {
            String input = br.readLine();
            if (checkVPS(input)) {
                bw.write("YES" + ENTER);
            } else {
                bw.write("NO" + ENTER);
            }

        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkVPS(String input) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(input.charAt(i));
            }

        }

        return stack.empty();
    }
}
