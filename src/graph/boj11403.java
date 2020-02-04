package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/02/04
 * 경로 찾기.
 * dfs
 * 다시 풀어보기!
 */
public class boj11403 {
    private static int n;
    private static int[][] a, path;
    private static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];
        path = new int[n + 1][n + 1];
        check = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = toInt(in[j - 1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            dfs(i);

            for (int j = 1; j <= n; j++) path[i][j] = check[j];

            Arrays.fill(check, 0);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(int v) {
        for (int i = 1; i <= n; i++) {
            if (a[v][i] == 1 && check[i] == 0) {
                check[i] = 1;
                dfs(i);
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}