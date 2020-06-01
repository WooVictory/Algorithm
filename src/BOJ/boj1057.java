package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/06/01
 * 토너먼트
 */
public class boj1057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]), a = toInt(in[1]), b = toInt(in[2]);

        int answer = 0;
        while (a != b) {
            if (a % 2 != 0) a += 1;
            if (b % 2 != 0) b += 1;

            a = a / 2;
            b = b / 2;
            answer++;
        }

        System.out.println(answer);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
