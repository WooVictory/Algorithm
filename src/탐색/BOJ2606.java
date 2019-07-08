package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 08/07/2019
 * dfs 바이러스 문제.
 * 기본적인 dfs 문제이고, 싸이클을 구하는 문제이다.
 */
public class BOJ2606 {
    private static int n, m, count;
    private static ArrayList<Integer>[] a;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());
        m = parse(br.readLine());

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] in = br.readLine().split(" ");
            int u = parse(in[0]);
            int v = parse(in[1]);

            a[u].add(v);
            a[v].add(u);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(a[i]);
        }

        dfs(1);
        System.out.println(count - 1);
    }

    private static void dfs(int start) {
        if (visited[start]) {
            return;
        }

        count++;
        visited[start] = true;

        for (int vv : a[start]) {
            if (!visited[vv]) {
                dfs(vv);
            }
        }

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
