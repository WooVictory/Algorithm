package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 31/07/2019
 * SW 역량 기출 퇴사.
 */
public class sw14501_1 {
    private static int n, result;
    private static Person[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        a = new Person[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            int period = parse(in[0]);
            int money = parse(in[1]);

            a[i] = new Person(period, money);
        }

        dfs(1, 0);
        System.out.println(result);
    }

    // 결국, 오늘의 날짜를 선택하는 경우와 선택하지 않는 경우 2가지로 나누어서 DFS 탐색을 진행한다.
    // 실제로 디버깅 해보면 조금은 복잡한 재귀 호출을 만나볼 수 있다.
    // 그래도 핵심은 선택하는 경우, 선택하지 않는 경우 DFS 탐색을 끝까지 돌면서 최대값을 찾는다는 것이다.
    private static void dfs(int day, int total) {
        // day 가 n인 날에 도착하면 result 를 최대값으로 갱신해준다.
        if (day == n + 1) {
            result = Math.max(result, total);
            return;
        }

        // 오늘 날짜를 선택하지 않는 경우.
        dfs(day + 1, total);

        // 오늘의 날짜를 선택하는 경우.
        // 이때는 N보다 작아야 한다. 백준이가 N+1일에 퇴사를 해야 하기 때문에
        // N일 이전의 일만 처리해야 한다.
        // 밑의 N+1일 7에 해당하는 인덱스 값이 되는게 맞음.
        // N+1일이 퇴사하는 날임.
        if (day + a[day].period <= n + 1) {
            dfs(day + a[day].period, total + a[day].money);
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
