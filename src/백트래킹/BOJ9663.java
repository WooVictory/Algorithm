package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 14/07/2019
 * N - Queen
 */
public class BOJ9663 {
    private static int n, count;
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());

        for (int i = 1; i <= n; i++) {
            a = new int[n + 1];
            a[1] = i; // 1행 i열에 퀸을 놓는다.

            // 내가 푼 방법은 1행의 1~N 열까지 퀸을 놓는 경우에 대해서 탐색을 진행하는 방법이다.
            // 따라서 dfs(1)은 매번 호출될 것이다. 1행에 대해서 계속 하기 때문이다.
            // 1. DFS 수행.
            dfs(1);
        }
        System.out.println(count);
    }

    private static void dfs(int row) {
        // row 와 n 이 같다면 끝까지 탐색을 진행했으므로
        // 경우의 수인 count 를 증가시킨다.
        if (row == n) {
            count++;
        } else {
            // 2. 1행을 제외한 다음 행들에 대해서 탐색을 진행한다.
            for (int i = 1; i <= n; i++) {
                a[row + 1] = i; // (row+1)행 i열에 퀸을 놓는다.

                // 3. 퀸을 놓을 자리가 조건에 만족하는지 검사한다.
                // 조건에 만족한다면 퀸을 놓고 DFS 탐색을 수행한다.
                if (isPossible(row + 1)) {
                    dfs(row + 1);
                } else {
                    a[row + 1] = -1;
                }
            }
        }
        // 백트래킹.
        // 이전 값을 원래대로 되돌려주는 것.
        a[row] = 0;
    }

    // 조건에 맞는지 검사한다.
    private static boolean isPossible(int row) {
        for (int i = 1; i < row; i++) {
            // 퀸을 놓는 규칙 상, 같은 행, 열에는 놓을 수 없다.
            // i행과 row 행의 열이 같으면 퀸을 놓을 수 없다.
            if (a[i] == a[row]) {
                return false;
            }

            // 퀸은 같은 대각선 상에 놓일 수 없으므로
            // 행의 차이와 열의 차이가 같다면 대각선상에 위치한다고 볼 수 있다.
            // 따라서 대각선에 위치한다면 false 를 리턴한다.
            if (Math.abs(i - row) == Math.abs(a[i] - a[row])) {
                return false;
            }
        }
        // 위의 경우, 모두 해당하지 않는다면 true 를 반환한다.
        return true;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}