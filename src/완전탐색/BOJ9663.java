package 완전탐색;

import java.util.Scanner;

/**
 * created by victory_woo on 22/05/2019
 * 완탐 : DFS / N-Queen
 * 백트래킹의 대표적인 문제.
 */
public class BOJ9663 {
    private static int N = 0;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            int[] column = new int[N + 1]; // N행 까지 담기 위해. index -> 행, value -> 열.
            column[1] = i; // 1행 i열에 퀸을 놓음.

            // 1. DFS 수행.
            // 1행 i 열에 퀸을 놓았을 경우 dfs 로 가능한 경우를 확인한다.
            dfs(column, 1);
        }

        System.out.println(count);
    }

    private static void dfs(int[] column, int row) {
        // row 와 N 이 같다는 말은 N 번째 행까지 퀸을 놓았다는 의미이다.
        // 즉, 퀸을 다 놓았다는 말! 따라서 count 를 증가시킨다.
        if (row == N) {
            count++;
        } else {
            for (int i = 1; i <= N; i++) {
                column[row + 1] = i; // (row+1)행 i열에 퀸을 놓는다.

                // 2. 유망한 노드인지 판단.
                if (isPossible(column, row + 1)) {
                    // 3. 서브 트리로 이동.(해당 노드의 하위 노드)
                    dfs(column, row + 1);
                }else {
                    // 4. 백트래킹 수행. 해당 노드는 가지치기 됨.
                    // 아니면 백트래킹. 0이면 퀸을 못놓는다는 의미.
                    column[row+1] = 0;
                }
            }
        }
        column[row] = 0;
    }

    private static boolean isPossible(int[] column, int row) {

        // (row+1)이 들어오는데 그 전까지 즉, row 행 전까지 검사한다.
        for (int i = 1; i < row; i++) {

            // i 행과 row 행의 열이 같으면 퀸을 놓을 수 없다.
            if (column[i] == column[row]) {
                return false;
            }
            // i 행과 row 행의 열 값이 대각선 위치에 존재하면 퀸을 놓을 수 없다.
            if (Math.abs(i - row) == Math.abs(column[i] - column[row])) {
                return false;
            }

        }
        // 위의 경우가 모두 된다면 true 반환한다.
        return true;

    }
}
