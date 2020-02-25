package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/25
 * 알파벳
 * dfs, 백트래킹.
 */
public class boj1987Re {
    private static int r, c, count = 1, max = 1;
    private static int[][] a;
    private static boolean[] visit;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        r = toInt(in[0]);
        c = toInt(in[1]);

        a = new int[r + 1][c + 1];
        visit = new boolean[26]; // 해당 알파벳을 인덱스로 변환해서 방문한 적이 있는지 없는지 체크한다.

        for (int i = 1; i <= r; i++) {
            String s = br.readLine();
            for (int j = 1; j <= c; j++) {
                a[i][j] = s.charAt(j - 1) - 65; // a 배열에는 알파벳에 해당하는 인덱스를 저장할 수 있도록 했다.
            }
        }

        dfs(1, 1);
        System.out.println(max);
    }

    private static void dfs(int x, int y) {
        int alpha = a[x][y];
        if (visit[alpha]) return;
        visit[alpha] = true;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x, ny = dy[i] + y;

            if (nx < 1 || ny < 1 || nx > r || ny > c) continue;

            int nextAlpha = a[nx][ny];
            if (!visit[nextAlpha]) {
                count++;
                max = Math.max(max, count);
                dfs(nx, ny);
            }
        }

        // 백트래킹.
        // 이전 정점으로 돌아갔을 때, 다시 다른 정점을 방문하기 위해서 현재 정점에 대해 count 와 visit 배열을 초기화해준다.
        count--;
        visit[alpha] = false;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}