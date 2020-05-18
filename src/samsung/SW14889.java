package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/18
 * 스타트와 링크.
 */
public class SW14889 {
    private static int n, min = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n + 1][n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = toInt(in[j - 1]);
            }
        }

        solve(0, 0);
        System.out.println(min);
    }

    private static void solve(int v, int depth) {
        if (depth == n / 2) {
            divide();
        } else {
            for (int i = v + 1; i <= n; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    solve(i, depth + 1);
                }
            }
        }

        visit[v] = false;
    }

    private static void divide() {
        int[] a = new int[(n / 2) + 1], b = new int[(n + 2) + 1];
        int ai = 1, bi = 1;

        for (int i = 1; i <= n; i++) {
            if (visit[i]) a[ai++] = i;
            else b[bi++] = i;
        }

        int diff = Math.abs(getResult(a) - getResult(b));
        min = Math.min(min, diff);
    }

    private static int getResult(int[] arr) {
        int result = 0;
        int len = n / 2;

        for (int i = 1; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                result += map[arr[i]][arr[j]];
                result += map[arr[j]][arr[i]];
            }
        }

        return result;
    }


    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
