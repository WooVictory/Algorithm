package 자료구조.Stack;


import java.util.Scanner;
import java.util.Stack;

public class BOJ9012_Stack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < test_case; i++) {
            if (check(sc.nextLine())) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

    public static Boolean check(String str) {
        Stack stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            } else if (str.charAt(i) == ')') { // 닫는 괄호가 나온 경우에는 스택에 값이 남아있는지 검사하는 과정을 거친다.
                if (!stack.isEmpty()) { // 스택에 값이 있다면 스택에서 여는 괄호를 뺀다.
                    stack.pop();
                } else { // 닫는 괄혼데 스택이 비어있으면 그 문자열은 올바른 문자열이 아닙니다.
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
