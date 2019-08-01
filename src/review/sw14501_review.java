package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 01/08/2019
 * SW 역량 기출 퇴사 Review.
 * DFS 탐색을 이용.
 */
public class sw14501_review {
    private static final String SPACE = " ";
    private static int n, result;
    private static Node[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        a = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(SPACE);
            int day = parse(in[0]);
            int money = parse(in[1]);

            a[i] = new Node(day, money);
        }

        dfs(1,0);
        System.out.println(result);
    }

    private static void dfs(int day, int total) {
        // 마지막 날짜에 도착하면 total, result 비교 후 탐색 종료.
        // 결국, N이 7일 때로 가정한다면 8일째에 오면 더 이상의 탐색은 필요가 없다는 뜻이다.
        // 그래서 최대값을 찾고 반환한다. 이유는 백준이가 N+1 일 째에 퇴사하기 때문이다.
        if (day == n + 1) {
            result = Math.max(result, total);
            return;
        }

        // 오늘 날짜의 상담을 선택하지 않는 경우.
        dfs(day + 1, total);

        // day 와 다음 탐색할 날짜의 합이 기간 내에 처리가 가능한지 확인한다.
        if (day + a[day].day <= n + 1) {
            // 오늘 날짜의 상담을 선택하는 경우.
            dfs(day + a[day].day, total + a[day].money);
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Node {
        int day;
        int money;

        Node(int day, int money) {
            this.day = day;
            this.money = money;
        }
    }

}
