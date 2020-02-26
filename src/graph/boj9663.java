package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/26
 * N Queen
 * dfs, 백트래킹.
 * 다시 풀어보기!
 */
public class boj9663 {
    private static int n, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        // 1행에 대해서 모든 열에 퀸을 놓는 것을 기준으로 dfs 탐색을 진행한다.
        for (int i = 1; i <= n; i++) {
            int[] col = new int[n + 1];

            // 1행 i열에 퀸을 놓는 것을 의미한다.
            col[1] = i;
            dfs(col, 1);
        }

        System.out.println(count);
    }

    private static void dfs(int[] col, int row) {
        // row == n 은 마지막 행까지 도달해서 퀸을 놓았음을 의미하므로 count 를 증가시킨다.
        if (row == n) {
            count++;
        } else {
            // row 행까지 놓았고, 바로 다음 행부터 1 ~ n 열까지 퀸을 놓을 수 있는지 확인한다.
            // 퀸을 놓을 수 있다면, dfs 탐색을 해서 다음 행의 1 ~ n 열까지 퀸을 놓을 수 있는지 확인하고 퀸을 놓는 방법으로 진행한다.
            for (int i = 1; i <= n; i++) {
                col[row + 1] = i;
                if (isPossible(col, row + 1)) {
                    dfs(col, row + 1);
                }
            }
        }
    }

    private static boolean isPossible(int[] col, int row) {
        for (int i = 1; i < row; i++) {
            // i 행과 row 행의 값이 같다는 것은 열 값이 같다는 것을 의미한다.
            //
            if (col[i] == col[row]) return false;

            if (Math.abs(i - row) == Math.abs(col[i] - col[row])) return false;
        }
        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
