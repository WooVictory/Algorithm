package 완전탐색;

import java.util.Scanner;

/**
 * created by victory_woo on 22/05/2019
 * N-Queen 조금 다른 방법
 * 체스의 위치를 찾기 위해서 방문했는지를 2차원 배열로 관리
 * 따라서 시간 복잡도가 증가함.
 */
public class BOJ9663_other {
    private static int N;
    private static boolean[][] visited;
    private static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[N + 1][N + 1];
        solve(0);
        System.out.println(count);
    }

    private static void solve(int row) {
        if (row == N) {
            count++;
            return;
        } else {
            for (int i = 0; i < N; i++) {
                if (!check(row, i)) {
                    continue;
                } else {
                    visited[row][i] = true;
                    solve(row + 1); // 다음 행에 대해서 dfs 진행.
                    visited[row][i] = false; // 백트래킹.
                }
            }
        }
    }

    // row : 행
    // col : 열
    private static boolean check(int row, int col) {

        // 위쪽 검사.
        for (int i = row; i >= 0; i--) {
            if (visited[i][col]) return false;
        }


        // 왼쪽 대각선 검사.
        int dx = -1;
        int dy = -1;

        int nextRow = row + dx;
        int nextCol = col + dy;

        while (nextRow >= 0 && nextCol >= 0) {
            if (visited[nextRow--][nextCol--]) return false;
        }

        // 오른쪽 대각선 검사.
        dx = -1;
        dy = 1;

        nextRow = row + dx;
        nextCol = col + dy;

        while (nextRow >= 0 && nextCol < N) {
            if (visited[nextRow--][nextCol++]) return false;
        }

        return true;

    }
}
