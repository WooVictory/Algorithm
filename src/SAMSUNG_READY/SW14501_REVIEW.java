package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/14
 * 퇴사.
 * 재귀호출.
 */
public class SW14501_REVIEW {
    private static int n, max = 0;
    private static int[] time, pay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        time = new int[n];
        pay = new int[n];

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            time[i] = toInt(in[0]);
            pay[i] = toInt(in[1]);
        }

        solve(0, 0);
        System.out.println(max);
    }

    private static void solve(int index, int sum) {
        // index == n 은 상근이가 퇴사하는 날짜까지 왔다면 종료해야 한다.
        // 따라서 종료 조건이 되며, 이때는 상근이가 벌어들인 금액의 최대값을 구하기 위해 max 와 비교하여
        // 최대값을 갱신한다.
        if (index == n) {
            max = Math.max(max, sum);
            return;
        }

        // 지금 상근이가 퇴사하기 전날까지 time[index]만큼의 시간이 걸리는 상담을 할 수 있다면
        // 그 상담을 한다. 그리고 그 상담을 했을 때 받을 수 있는 금액을 더하여 solve()를 다시 호출한다.
        if (index + time[index] <= n) solve(index + time[index], sum + pay[index]);
        // 그렇지 않다면, 그 상담은 진행하지 않고 index 값을 1 증가시킨다.
        solve(index + 1, sum);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
