package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/12
 * 퇴사.
 * 삼성 기출.
 * dfs
 * 다시 풀어보기!
 */
public class sw14501 {
    private static int n, max;
    private static int[] t, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        // 걸리는 시간과 비용을 배열로 입력 받는다.
        t = new int[n + 1];
        p = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            String[] in = br.readLine().split(" ");
            t[i] = toInt(in[0]);
            p[i] = toInt(in[1]);
        }

        // dfs 함수인 solve()를 호출한다.
        solve(1, 0);
        System.out.println(max);
    }

    // 1일부터 시작하기 때문에 n+1 번째 되는 날 퇴사를 함으로
    // day == n+1 을 탈출 조건으로 설정한다.
    // 결국, 백준이가 day 날의 상담을 하는 경우와 하지 않는 경우로 구분하여 dfs() 재귀 호출을 구성하면 된다.
    private static void solve(int day, int total) {
        if (day == n + 1) {
            max = Math.max(max, total);
            return;
        }

        // day + t[day] <= n+1 가 의미하는 것은 한 상담의 기간이 퇴사 전에 수행할 수 있는 상담을 뜻한다.
        // 따라서 이 상담은 기간 내에 할 수 있으므로 백준이는 이 상담을 할 것이다. 상담을 하기 때문에 비용도 total + p[day] 증가한다.
        if (day + t[day] <= n + 1) solve(day + t[day], total + p[day]);

        // 그리고 백준이가 day 날짜의 상담을 하지 않을 수도 있기 때문에 이 경우에는 다음 날 상담을 할 것으로 solve()를 호출한다.
        solve(day + 1, total);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }

}
