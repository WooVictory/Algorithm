package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 02/07/2019
 * dfs : 효율적인 해킹 review.
 */
public class BOJ1325_review {
    private static final int MAX = 10000;
    private static int n, m;
    private static ArrayList<Integer>[] a;
    private static boolean[] visited;
    private static int[] answer; // 해킹할 수 있는 컴퓨터의 수를 저장하는 배열.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        // 초기화.
        a = (ArrayList<Integer>[]) new ArrayList[MAX + 1];
        visited = new boolean[MAX + 1];
        answer = new int[MAX + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        // 입력.
        for (int i = 0; i < m; i++) {
            String[] num = br.readLine().split(" ");
            int u = parse(num[0]);
            int v = parse(num[1]);

            a[u].add(v);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[MAX + 1];
            dfs(i);
        }

        int max = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, answer[i]);
        }
        System.out.println(max);

        for (int i = 1; i <= n; i++) {
            if (max == answer[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int start) {
        if (visited[start]) {
            return;
        }

        visited[start] = true;

        for (int value : a[start]) {
            if (!visited[value]) {
                answer[value]++;
                dfs(value);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
