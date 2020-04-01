package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/01
 * 퇴사.
 * dfs 방법으로 풀기.
 */
public class boj14501 {
    private static int n, result = 0;
    private static int[] t, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        t = new int[n + 1];
        p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            t[i] = toInt(in[0]);
            p[i] = toInt(in[1]);
        }

        dfs(1, 0);
        System.out.println(result);
    }

    private static void dfs(int day, int total) {
        // 2가지 경우로 나눠서 dfs 탐색을 진행하기 때문에 종료 조건에 걸리게 된다.
        // day 가 상근이가 퇴사하는 날이 되면 total 값을 비교하여 가장 큰 값을 result 에 담을 수 있도록 한다.
        if (day == n + 1) {
            if (result < total) result = total;
            return;
        }

        // day : 인덱스 역할을 하면서 기간과 더해져서 상근이가 해당 날짜의 일을 할 수 있는지 확인하는 용도로 사용됨.
        // 그 날짜의 일을 하는 경우와 하지 않고 다음 날짜를 확인하는 경우 2가지로 나눠서 dfs 탐색을 진행한다.
        if (day + t[day] <= n + 1) dfs(day + t[day], total + p[day]);
        dfs(day + 1, total);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
