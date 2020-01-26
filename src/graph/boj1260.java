package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/26
 * bfs+dfs
 */
public class boj1260 {
    private static String SPACE = " ";
    private static boolean[] visit;
    private static ArrayList<Integer>[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(SPACE);
        int n = toInt(input[0]);
        int m = toInt(input[1]);
        int s = toInt(input[2]);

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) a[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] in = br.readLine().split(SPACE);
            int v1 = toInt(in[0]);
            int v2 = toInt(in[1]);

            a[v1].add(v2);
            a[v2].add(v1);
        }

        for (int i = 1; i <= m; i++) Collections.sort(a[i]);

        dfs(s);

        visit = new boolean[n + 1];
        visit[s] = true;
        System.out.println();

        bfs(s);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int num = q.remove();
            System.out.print(num + SPACE);

            for (int x : a[num]) {
                if (!visit[x]) {
                    visit[x] = true;
                    q.add(x);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    private static void dfs(int x) {
        if (visit[x]) return;

        visit[x] = true;
        System.out.print(x + SPACE);

        for (int y : a[x]) {
            if (!visit[y]) dfs(y);
        }
    }
}
