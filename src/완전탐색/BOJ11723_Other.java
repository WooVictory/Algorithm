package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 10/04/2019
 * 완전탐색 : 집합
 * 비트 마스크.
 */
public class BOJ11723_Other {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        int s = 0; // 집합.

        while (m-- > 0) {
            String[] input = br.readLine().split(" ");
            String cmd = input[0];
            int x;

            switch (cmd) {
                case "add": // 추가.
                    x = Integer.parseInt(input[1]);
                    x--;
                    s = s | (1 << x);
                    break;
                case "remove": // 삭제.
                    x = Integer.parseInt(input[1]);
                    x--;
                    s = s & ~(1 << x);
                    break;
                case "check":
                    x = Integer.parseInt(input[1]);
                    x--;
                    int tmp = s & (1 << x);
                    if (tmp != 0) {
                        sb.append("1").append("\n");
                    } else {
                        sb.append("0").append("\n");
                    }
                    break;
                case "toggle":
                    x = Integer.parseInt(input[1]);
                    x--;
                    s = s ^ (1 << x);
                    break;
                case "all":
                    s = (1 << 20) - 1;
                    break;
                case "empty":
                    s = 0;
                    break;
            }
        }

        System.out.println(sb.toString());
    }
}
