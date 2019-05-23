package 완전탐색;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 23/05/2019
 * 완탐 : 알파벳
 * 굳이 visited 배열을 만들 필요가 없다.
 */
public class BOJ1987 {
    private static final int ASCII = 65;
    private static int R, C, count, max;
    private static boolean[] check = new boolean[26];
    private static int[][] alpha = new int[21][21];

    // 4 방향을 검사하기 위한 배열.
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = parse(input[0]);
        C = parse(input[1]);

        for (int i = 1; i <= R; i++) {
            String in = br.readLine();
            for (int j = 1; j <= C; j++) {
                alpha[i][j] = in.charAt(j - 1) - ASCII;
            }
        }

        // (1,1) 부터 검사 시작.
        dfs(1, 1);
        System.out.println(max);

    }

    private static void dfs(int x, int y) {
        count++;
        if (max < count) {
            max = count;
        }

        // 방문한 알파벳을 체크한다.
        check[alpha[x][y]] = true;

        // 범위를 검사한다.
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (0 < nextX && nextX <= R && 0 < nextY && nextY <= C) {
                // 방문한 적이 없다면.
                if (!check[alpha[nextX][nextY]]) {
                    dfs(nextX, nextY);
                }
            }
        }
        // count 감소.
        // 백트래킹.
        count--;
        check[alpha[x][y]] = false;
    }


    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
