package 입출력;

import java.util.Scanner;

public class BOJ11718 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) { // EOF까지 입력받기 위해 sc.hasNext() 사용
            System.out.println(sc.nextLine());
            // 공백을 포함한 한 줄을 입력받기 위해 sc.nextLine() 사용
            // sc.next() : 공백을 포함하지 않고 입력을 받음 즉, 공백을 입력하면 거기서 짤림
        }
    }
}
