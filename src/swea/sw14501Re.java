package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/12
 * 퇴사.
 * dfs, 삼성 기출.
 */
public class sw14501Re {
    private static int n, max;
    private static int[] t, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        t = new int[n + 1];
        p = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            String[] in = br.readLine().split(" ");
            t[i] = toInt(in[0]);
            p[i] = toInt(in[1]);
        }

        dfs(1, 0);
        System.out.println(max);
    }

    private static void dfs(int day, int total) {
        // 백준이가 퇴사하는 날의 경우에는 탐색이 끝나므로 max 값을 찾고 return 한다.
        if (day == n + 1) {
            max = Math.max(max, total);
            return;
        }

        // 백준이가 퇴사 하기 전에 해당 날짜(day)의 상담을 할 수 있는 경우, 백준이는 그 날짜의 상담을 수행한다.
        if (day + t[day] <= n + 1) dfs(day + t[day], total + p[day]);

        // 백준이가 해당 날짜(day)의 상담을 수행하지 않는 경우.
        dfs(day + 1, total);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
