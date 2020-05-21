package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/21
 * 사다리 조작.
 */
public class sw15684 {
    private static int N, M, H;
    private static int[][] map;
    private static int answer = 0;
    private static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);
        H = toInt(in[2]);

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            int a = toInt(in[0]), b = toInt(in[1]);
            map[a][b] = 1;
            map[a][b + 1] = 2;
        }
        print();

        for (int i = 0; i <= 3; i++) {
            answer = i;
            solve(1, 0);
            if (flag) break;
        }

        System.out.println(flag ? answer : -1);
    }

    private static void solve(int v, int depth) {
        if (flag) return;

        if (answer == depth) {
            if (check()) flag = true;
            return;
        }

        for (int row = v; row <= H; row++) {
            for (int col = 1; col <= N - 1; col++) {
                if (map[row][col] == 0 && map[row][col + 1] == 0) {
                    map[row][col] = 1;
                    map[row][col + 1] = 2;
                    solve(row, depth + 1);

                    map[row][col] = map[row][col + 1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int row = 1, col = i;
            for (int j = 0; j < H; j++) {
                if (map[row][col] == 1) col++;
                else if (map[row][col] == 2) col--;
                row++;
            }

            if (i != col) return false;
        }
        return true;
    }

    private static void print() {
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
