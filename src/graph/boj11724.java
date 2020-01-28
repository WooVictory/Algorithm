package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/26
 */
public class boj11724 {
    private static boolean[] visit;
    private static ArrayList<Integer>[] a;
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(SPACE);

        int n = toInt(input[0]);
        int m = toInt(input[1]);

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

        int count = 0;
        // dfs.
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                //dfs(i);
                bfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    private static void bfs(int v) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(v);
        visit[v] = true;

        while (!q.isEmpty()) {
            int x = q.remove();

            for (int y : a[x]) {
                if (!visit[y]) {
                    visit[y] = true;
                    q.add(y);
                }
            }
        }
    }

    private static void dfs(int v) {
        visit[v] = true;

        for (int x : a[v]) {
            if (!visit[x]) dfs(x);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
