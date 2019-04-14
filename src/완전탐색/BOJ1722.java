package 완전탐색;

import java.util.Scanner;

/**
 * created by victory_woo on 11/04/2019
 * 완전 탐색 : 순열의 순서.
 */
public class BOJ1722 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        
        long[] factorial = new long[n + 1];
        boolean[] check = new boolean[n + 1];

        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        int m = sc.nextInt();

        switch (m) {
            case 1: // k번째 순열이 무엇인지?!
                long k = sc.nextLong();
                int[] a = new int[n];
                for (int i = 0; i < n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (check[j]) {
                            continue;
                        }

                        if (factorial[n - i - 1] < k) {
                            k = k - factorial[n - i - 1];
                        } else {
                            a[i] = j;
                            check[j] = true;
                            break;
                        }
                    }
                }

                for (int i = 0; i < n; i++) {
                    System.out.print(a[i] + " ");
                }

                break;
            case 2: // 주어진 순열이 몇번째 순열인지?!
                a = new int[n];

                for (int i = 0; i < n; i++) {
                    a[i] = sc.nextInt();
                }

                long result = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 1; j < a[i]; j++) {
                        if (!check[j]) {
                            result = result + factorial[n - i - 1];
                        }
                    }
                    check[a[i]] = true;
                }
                System.out.println(result + 1);
                break;
        }
    }

}
