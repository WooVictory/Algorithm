package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 12/07/2019
 * 알파벳 백트래킹
 */
public class BOJ1987 {
    private static int R, C, count, max;
    private static int[][] map;
    private static boolean[] visited;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        R = parse(in[0]);
        C = parse(in[1]);

        map = new int[R + 1][C + 1];
        visited = new boolean[26];

        for (int i = 1; i <= R; i++) {
            String s = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = s.charAt(j - 1) - 'A';
            }
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        dfs(1, 1);
        System.out.println(max);
    }

    private static void dfs(int x, int y) {
        count++;
        if (max < count)
            max = count;

        int index = map[x][y];
        visited[index] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx <= 0 || nx > R || ny <= 0 || ny > C)
                continue;

            if (!visited[map[nx][ny]]) {
                dfs(nx, ny);
            }
        }
        count--;
        visited[map[x][y]] = false;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
