package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 14/04/2019
 * 완전탐색 : 날짜 계산.
 */
public class BOJ1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int count = 1;
        int E = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);
        int M = Integer.parseInt(input[2]);

        int e = 1, s = 1, m = 1;
        while (true) {

            if (e == E && s == S && m == M) {
                System.out.println(count);
                break;
            }
            e++;
            s++;
            m++;
            count++;
            if (e == 16) e = 1;
            if (s == 29) s = 1;
            if (m == 20) m = 1;
        }

    }
}
