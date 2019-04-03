package 그래프;

import java.io.*;
import java.util.ArrayList;

/**
 * created by victory_woo on 03/04/2019
 * 그래프 : 순열 싸이클.
 * 방향이 나타나 있다. 즉, 단방향 그래프.
 * 이럴 경우 인접 리스트보다 배열을 사용해서 푸는 방식이 더 낫다.
 */
public class BOJ10451 {
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int t = parse(br.readLine());
        while (t-- > 0) {
            int n = parse(br.readLine());
            a = new ArrayList[n + 1];
            visit = new boolean[n + 1];

            String[] input = br.readLine().split(" ");

            for (int i = 1; i <= n; i++) {
                a[i] = new ArrayList<>();
                int u = parse(input[i - 1]);

                a[i].add(u);
            }

            sb.append(dfsAll(n)).append(NEW_LINE);
            //System.out.println(dfsAll(n));
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static int parse(String cmd) {
        return Integer.parseInt(cmd);
    }

    private static int dfsAll(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                dfs(i);
                count++;
            }
        }

        return count;
    }

    private static void dfs(int x) {
        visit[x] = true;

        for (int y : a[x]) {
            if (!visit[y]) {
                dfs(y);
            }
        }
    }
}
