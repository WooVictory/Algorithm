package 자료구조.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class BOJ9012 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(bf.readLine());
        sc.nextLine();
        int left_count = 0;
        int right_count = 0;
        Stack stack = new Stack();
        for (int i = 0; i <= test_case; i++) {
            left_count = 0;
            right_count = 0;
            String data = bf.readLine();
            for (int j = 0; j < data.length(); j++) {
                if (data.charAt(j) == '(') {
                    stack.push(data.charAt(j));
                    left_count++;
                } else if (data.charAt(j) == ')') {
                    stack.push(data.charAt(j));
                    right_count++;
                }
            }
            if (left_count == right_count) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            System.out.println(left_count);
            System.out.println(right_count);

        }

    }

}
