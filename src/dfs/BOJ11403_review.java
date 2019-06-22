package dfs;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 22/06/2019
 * dfs : 경로 찾기 리뷰
 */
public class BOJ11403_review {
    private static final String SPACE = " ", NEW_LINE = "\n";
    private static int n;
    private static int[][] a, path;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = convert(br.readLine());

        a = new int[n + 1][n + 1];
        path = new int[n + 1][n + 1];
        visited = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] s = br.readLine().split(SPACE);
            for (int j = 1; j <= n; j++) {
                a[i][j] = convert(s[j - 1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            dfs(i);

            for (int j = 1; j <= n; j++) {
                path[i][j] = visited[j];
            }

            Arrays.fill(visited, 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(path[i][j]).append(SPACE);
            }
            sb.append(NEW_LINE);
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int v) {
        int len = a.length - 1;

        for (int i = 1; i <= len; i++) {
            if (a[v][i] == 1 && visited[i] == 0) {
                visited[i] = 1;
                dfs(i);
            }
        }
    }

    private static int convert(String str) {
        return Integer.parseInt(str);
    }
}