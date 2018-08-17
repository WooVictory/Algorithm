package 자료구조.Stack;

import java.util.Scanner;
import java.util.Stack;

public class BOJ10828 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack();
        int test_case = sc.nextInt();
        for(int i=0;i<test_case;i++){
            String command = sc.next();
            switch (command){
                case "push":
                    int num = sc.nextInt();
                    stack.push(num);
                    break;
                case "pop":
                    if(stack.isEmpty()){
                        System.out.println(-1);
                    }else {
                        System.out.println(stack.pop());
                    }
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    if(stack.isEmpty()){
                        System.out.println(1);
                    }else{
                        System.out.println(0);
                    }
                    break;
                case "top":
                    if(stack.isEmpty()){
                        System.out.println(-1);
                    }else{
                        System.out.println(stack.peek());
                    }
                    break;
            }
        }
    }
}
