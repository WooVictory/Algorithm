package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 05/07/2019
 * dfs 와 bfs
 */
public class BOJ1260 {
    private static int n, m;
    private static ArrayList<Integer>[] a;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = parse(in[0]);
        m = parse(in[1]);
        int start = parse(in[2]);

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] num = br.readLine().split(" ");
            int u = parse(num[0]);
            int v = parse(num[1]);

            // 양방향이므로.
            a[u].add(v);
            a[v].add(u);
        }

        // 정점이 여러개 있을 때,
        // 번호가 작은 정점을 먼저 방문하기 위해서.
        for (int i=1;i<=n;i++){
            Collections.sort(a[i]);
        }

        dfs(start);

        visited = new boolean[n + 1];
        System.out.println();

        bfs(start);
    }

    private static void dfs(int start) {
        if (visited[start]) {
            return;
        }

        System.out.print(start + " ");
        visited[start] = true;

        for (int value : a[start]) {
            if (!visited[value]) {
                dfs(value);
            }
        }
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;


        while (!q.isEmpty()) {
            int value = q.remove();
            System.out.print(value + " ");
            for (int vv : a[value]) {
                if (!visited[vv]) {
                    q.add(vv);
                    visited[vv] = true;
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
