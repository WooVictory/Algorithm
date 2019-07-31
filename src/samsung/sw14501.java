package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 31/07/2019
 * SW 역량 기출 퇴사.
 */
public class sw14501 {
    private static int n, total;
    private static Person[] a;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());
        a = new Person[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            int period = parse(in[0]);
            int money = parse(in[1]);

            a[i] = new Person(period, money);
        }
        dfs(1);
        System.out.println(total-a[n].money);
    }

    private static void dfs(int current) {
        if (visited[current]) {
            return;
        }

        if (current >= n) {
            return;
        }

        visited[current] = true;
        total += a[current].money;
        int idx = current;
        int count = a[current].period;

        while (count-- >= 1) {
            idx++;
        }

        if (0 < idx && idx <= n) {
            if (!visited[idx]) {
                //System.out.println(idx);
                dfs(idx);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Person {
        int period;
        int money;

        Person(int period, int money) {
            this.period = period;
            this.money = money;
        }
    }
}
