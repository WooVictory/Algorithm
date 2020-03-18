package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/18
 * 경사로.
 * 삼성 기출.
 * 시뮬레이션.
 * 다시 풀어보기.
 */
public class sw14890 {
    private static int n, l, count = 0;
    private static int[][] map, map2;
    private static boolean[] slop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        l = toInt(in[1]);

        map = new int[n][n];
        map2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) map[i][j] = map2[j][i] = toInt(in[j]);
        }

        for (int i = 0; i < n; i++) {
            solve(i, map);
            solve(i, map2);
        }
        System.out.println(count);
    }

    private static void solve(int index, int[][] a) {
        slop = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            if (a[index][i] == a[index][i + 1]) continue;

            int diff = a[index][i] - a[index][i + 1];
            if (diff != 1 && diff != -1) return;

            if (diff == -1) {
                // 왼쪽 경사로.
                for (int j = 0; j < l; j++) {
                    if (i - j < 0 || slop[i - j]) return;

                    if (a[index][i] == a[index][i - j]) slop[i - j] = true;
                    else return;
                }

            } else {
                // 오른쪽 경사로.
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || slop[i + j]) return;

                    if (a[index][i] - 1 == a[index][i + j]) slop[i + j] = true;
                    else return;
                }
            }
        }
        count++;
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
