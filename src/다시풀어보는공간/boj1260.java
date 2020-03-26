package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/25
 * dfs, bfs 문제.
 */
public class boj1260 {
    private static int n, m, v;
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        v = toInt(in[2]);

        a = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) a[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            int v1 = toInt(in[0]);
            int v2 = toInt(in[1]);

            a[v1].add(v2);
            a[v2].add(v1);
        }

        // 한 정점에서 방문할 수 있는 정점이 여러개인 경우에는 정점의 번호가 작은 것부터 방문해야 한다.
        // 따라서 한 정점에서 방문할 수 있는 정점에 대해 오름차순 정렬을 한다.
        for (int i = 1; i <= n; i++) Collections.sort(a[i]);

        sb = new StringBuilder();
        visit = new boolean[n + 1];
        dfs(v);
        sb.append("\n");

        visit = new boolean[n + 1];
        bfs(v);
        System.out.println(sb.toString());
    }

    private static void dfs(int v) {
        if (visit[v]) return;
        visit[v] = true;
        sb.append(v).append(" ");

        for (int vv : a[v]) {
            if (!visit[vv]) dfs(vv);
        }
    }

    private static void bfs(int v) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(v);
        visit[v] = true;

        while (!q.isEmpty()) {
            int num = q.remove();
            sb.append(num).append(" ");

            for (int vv : a[num]) {
                if (!visit[vv]) {
                    visit[vv] = true;
                    q.add(vv);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
