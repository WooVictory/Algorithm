package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 02/07/2019
 * dfs : 효율적인 해킹.
 */
public class BOJ1325 {
    private static final int MAX = 10000;
    private static final String SPACE = " ";
    private static ArrayList<Integer>[] a = (ArrayList<Integer>[]) new ArrayList[MAX + 1];
    private static boolean[] visited = new boolean[MAX + 1];
    private static int[] answer = new int[MAX + 1];
    private static StringBuilder sb = new StringBuilder();
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);

        int n = parse(in[0]);
        int m = parse(in[1]);

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] num = br.readLine().split(SPACE);
            int u = parse(num[0]);
            int v = parse(num[1]);

            a[u].add(v);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[MAX + 1];
            dfs(i);
        }

        /*//int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, answer[i]);
        }*/

        for (int i = 1; i <= n; i++) {
            if (max == answer[i]) {
                sb.append(i).append(SPACE);
            }
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int start) {
        if (visited[start]) {
            return;
        }

        visited[start] = true;

        for (int v : a[start]) {
            if (!visited[v]) {
                answer[v]++;
                max = Math.max(max, answer[v]);
                dfs(v);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
