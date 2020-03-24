package programmers;

import java.util.Scanner;

/**
 * created by victory_woo on 2020/03/24
 * 별찍기. 행, 열을 따라 별을 찍으면 된다.
 */
public class PGM12969 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) System.out.print("*");
            System.out.println();
        }
    }
}
