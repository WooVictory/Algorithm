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
public class boj2661Re {
    private static int n;
    private static boolean isEnd = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        dfs("");
    }

    private static void dfs(String s) {
        // 결과를 찾으면 해당 플래그가 바뀌어 dfs 를 종료한다.
        if (isEnd) return;

        // 수열의 길이가 n 이면 결과를 출력하고 종료할 수 있도록 플래그를 바꾼다.
        // n 길이의 좋은 수열이 여러 개일 때, 가장 작은 수를 출력해야 한다.
        // dfs 탐색 자체를 1부터 시작하기 때문에 먼저 찾은 좋은 수열은 제일 작은 수열이 된다.
        if (s.length() == n) {
            System.out.println(s);
            isEnd = true;
        } else {
            // 1,2,3을 붙이면서 수열이 좋은 수열인지 나쁜 수열인지 판단한다.
            for (int i = 1; i <= 3; i++) {
                if (isPossible(s + i)) {
                    dfs(s + i);
                }
            }
        }
    }

    // 좋은 수열인지 나쁜 수열인지 판단한다.
    // 수열의 절반 길이까지만 탐색한다.
    // 123132 일 때
    // i = 1, front = 3, back = 2
    // i = 2, front = 31, back = 32
    // i = 3, front = 123, back = 132
    private static boolean isPossible(String s) {
        int len = s.length();

        for (int i = 1; i <= len / 2; i++) {
            String front = s.substring(len - i - i, len - i);
            String back = s.substring(len - i, len);

            if (front.equals(back)) return false;
        }
        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}