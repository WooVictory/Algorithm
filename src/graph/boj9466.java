package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/05
 * 텀 프로젝트.
 * dfs
 * 다시 풀어보기!
 */
public class boj9466 {
    private static int[] a;
    private static boolean[] visit, finished;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            int n = toInt(br.readLine());

            a = new int[n + 1];
            visit = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            String[] in = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) a[i] = toInt(in[i - 1]);


            for (int i = 1; i <= n; i++) dfs(i);

            System.out.println(n - count);
        }
    }

    private static void dfs(int x) {
        if (visit[x]) return;

        visit[x] = true;
        int next = a[x];


        if (!visit[next]) {
            dfs(next);
        } else {
            if (!finished[next]) {
                count++;
                for (int i = next; i != x; i = a[i])
                    count++;
            }
        }

        finished[x] = true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
