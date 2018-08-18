package 자료구조.Stack;

import java.util.Scanner;
import java.util.Stack;

public class BOJ9012 {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int test_case = sc.nextInt();
        sc.nextLine();
        int left_count=0;
        int right_count=0;
        Stack stack = new Stack();
        for(int i=0;i<=test_case;i++){
            left_count = 0;
            right_count = 0;
            String data = sc.nextLine();
            for(int j=0;j<data.length();j++){
                if(data.charAt(j) == '('){
                    stack.push(data.charAt(j));
                    left_count++;
                }else if(data.charAt(j) == ')'){
                    stack.push(data.charAt(j));
                    right_count++;
                }
            }
            if(left_count == right_count){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
            System.out.println(left_count);
            System.out.println(right_count);

        }

    }

}
