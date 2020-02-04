package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/02/04
 * 연결요소의 개수.
 * dfs
 */
public class boj11724Re {
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int n = toInt(in[0]);
        int m = toInt(in[1]);

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) a[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            String[] input = br.readLine().split(" ");
            int v1 = toInt(input[0]), v2 = toInt(input[1]);

            a[v1].add(v2);
            a[v2].add(v1);
        }

        int total = 0;
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                total++;
                dfs(i);
            }
        }

        System.out.println(total);
    }

    private static void dfs(int start) {
        visit[start] = true;

        for (int x : a[start])
            if (!visit[x]) dfs(x);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
