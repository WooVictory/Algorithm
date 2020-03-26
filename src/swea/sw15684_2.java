package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/22
 * 사다리 조작.
 * 삼성 기출.
 *
 */
public class sw15684_2 {
    private static int n, m, h, result = Integer.MAX_VALUE;
    private static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        h = toInt(in[2]);

        ladder = new int[30][10];
        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            int a = toInt(in[0]), b = toInt(in[1]);
            ladder[a - 1][b - 1] = 1;
            ladder[a - 1][b] = 2;
        }

        int result = solve(0, 0);
        System.out.println((result != Integer.MAX_VALUE) ? result : -1);
    }

    private static int solve(int position, int count) {

        if (count == 3 || position >= h * n) {
            if (check()) {
                return count;
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            int row = position / n;
            int col = position % n;

            if (col != n - 1 && ladder[row][col] == 0 && ladder[row][col + 1] == 0) {
                ladder[row][col] = 1;
                ladder[row][col + 1] = 2;
                result = Math.min(result, solve(position + 2, count + 1));

                ladder[row][col] = 0;
                ladder[row][col + 1] = 0;
            }

            result = Math.min(result, solve(position + 1, count));
            return result;
        }
    }

    private static boolean check() {
        for (int i = 0; i < n; i++) {
            int row = 0;
            int col = i;

            do {
                if (ladder[row][col] == 1) col++;
                else if (ladder[row][col] == 2) col--;

                row++;
            } while (row != h);

            if (col != i) return false;
        }

        return true;
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
