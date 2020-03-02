package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/01
 * 좋은 수열.
 * dfs, 백트래킹
 * 다시 풀어보기!
 */
public class boj2661 {
    private static int n;
    private static boolean isEnd = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        dfs("1");
    }

    private static void dfs(String password) {
        if (isEnd) return;

        if (password.length() == n) {
            System.out.println(password);
            isEnd = true;
        }

        for (int i = 1; i <= 3; i++) {
            if (isPossible(password + i)) {
                dfs(password + i);
            }
        }
    }

    private static boolean isPossible(String password) {
        int len = password.length();

        for (int i = 1; i <= len / 2; i++) {
            String front = password.substring(len - i - i, len - i);
            String back = password.substring(len - i, len);

            if (front.equals(back)) return false;
        }
        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
