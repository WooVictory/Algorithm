package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/02/06
 */
public class boj1325Re {
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]), m = toInt(in[1]);

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        result = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] num = br.readLine().split(" ");
            int v1 = toInt(num[0]), v2 = toInt(num[1]);

            a[v1].add(v2);
        }

        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];
            dfs(i);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) if (max < result[i]) max = result[i];

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) if (max == result[i]) sb.append(i).append(" ");

        System.out.println(sb.toString());
    }

    private static void dfs(int v) {
        visit[v] = true;

        for (int x : a[v]) {
            if (!visit[x]) {
                result[x]++;
                dfs(x);
            }
        }
    }

    static int toInt(String value) {
        return Integer.parseInt(value);
    }
}