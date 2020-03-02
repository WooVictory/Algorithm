package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/02
 * 비숍
 * dfs, 백트래킹
 * 다시 풀어보기!
 */
public class boj1799_2 {
    private static int n;
    private static int[][] a;
    private static int[][] colors;
    private static boolean[] visit = new boolean[100];
    private static int[] answer = new int[2];
    private static int[] dy = {-1, -1, 1, 1};
    private static int[] dx = {-1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n][n];
        colors = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                a[i][j] = toInt(in[j]);

                // 흑색 칸에 대해서 표시를 1로 한다.
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        colors[i][j] = 1;
                    }
                } else {
                    if (j % 2 != 0) {
                        colors[i][j] = 1;
                    }
                }
            }
        }

        // 1 : 흑백칸
        // 0 : 백색칸
        dfs(-1, 0, 1);
        dfs(-1, 0, 0);

        System.out.println(answer[0] + answer[1]);
    }

    private static void dfs(int v, int count, int color) {
        if (answer[color] < count) answer[color] = count;

        // for 문 하나로 2차원 배열을 표현하기 위해서
        // c, r의 표현이 나온다.
        for (int i = v + 1; i < n * n; i++) {
            int c = i / n; // 행. 위에서부터 c만큼 떨어져있음.
            int r = i % n; // 열. 왼쪽에서 r만큼 떨어져있음.
            // (c,r)로 표현을 했지만, c는 아래로 내려가는 방향이다. 즉, 하나의 열을 표현한다.
            // r은 오른쪽으로 가는 방향이다. 즉, 하나의 행을 표현한다.

            // colors 의 (c,r)가 color (흑 또는 백)와 같지 않으면 다시 반복문으로 올라간다.
            if (colors[c][r] != color) continue;

            // 비숍을 놓을 수 있는 자리일 경우.
            // 비숍을 놓을 수 있는지 확인한다.
            if (a[c][r] == 1) {
                if (isSafe(c, r)) {
                    visit[i] = true;
                    dfs(i, count + 1, color);
                }
            }
        }
        if (v == -1) return;
        visit[v] = false;
    }

    private static boolean isSafe(int c, int r) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + r;
            // nx가 다음에 방문할 x 좌표이기 때문에 하나의 행에서 오른쪽으로 이동하는 즉, r을 더해준다.(x)

            int ny = dy[i] + c;
            // ny가 다음에 방문할 y 좌표이기 때문에 하나의 열에서 아래쪽으로 이동하는 즉, c를 더해준다.(y)

            int v = ny * n + nx;
            // r, c가 나누기, 나머지 연산을 통해서 계산되었기 때문에
            // 이를 기준으로 다시 v라는 정점을 복원하는 계산을 통해 구한다.

            for (int j = 1; j <= n; j++) {
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (visit[v]) return false;

                nx += dx[i];
                ny += dy[i];
                v = ny * n + nx;
            }
        }

        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
