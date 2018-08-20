package 자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class BOJ9012 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < test_case; i++) {
            System.out.println(check(sc.nextLine()));
        }


    }

    public static String check(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count += 1;
            } else if (str.charAt(i) == ')') {
                count -= 1;
            }
            if (count < 0) { // 닫는 괄호에 대한 여는 괄호가 스택에 없음. 문자열에 닫는 괄호가 있어서 count가 음수
                return "NO";
            }
        }

        if (count == 0) { // 스택이 비어있음. 올바른 문자열
            return "YES";
        } else { // 스택이 비어있지 않음. 올바르지 못한 문자열
            return "NO";
        }
    }

}
