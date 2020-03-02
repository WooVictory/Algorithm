package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/01
 * 비숍.
 * dfs, 백트래킹
 * 다시 풀어보기!
 */
public class boj1799 {
    private static int n;
    private static int[][] a;
    private static boolean[][] visit;
    private static int blackCount = 0, whiteCount;
    private static int[] dy = {-1, -1, 1, 1};
    private static int[] dx = {-1, 1, -1, 1};
    // 좌상, 우상, 좌하, 우하


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = toInt(in[j - 1]);
            }
        }

        visit = new boolean[n + 1][n + 1];
        blackSearch(1, 1, 0);

        visit = new boolean[n + 1][n + 1];
        whiteSearch(1, 2, 0);

        System.out.println(whiteCount + blackCount);
    }

    private static void blackSearch(int y, int x, int count) {
        blackCount = Math.max(blackCount, count);

        // x -> 오른쪽으로 이동한다. 즉, 열을 나타냄.
        // y -> 아래쪽으로 이동한다. 즉, 행을 나타낸다.
        // x 가 체스판의 크기보다 크면(x 가 오른쪽으로 이동하다가 끝에 도달했을 때)
        // 다음 칸의 첫 번째 x 좌표로 이동시켜준다.
        // 단, 흑색칸은 y 가 홀수일 경우, x의 시작점이 1부터 시작되고
        // 짝수일 경우, 2부터 시작된다.
        if (x > n) {
            y += 1;
            x = (y % 2 == 0) ? 2 : 1;
        }

        // y 즉, 아래로 이동하는 좌표가 체스판의 크기보다 크면 탐색을 종료한다.
        if (y > n) return;

        // 현재 자리에 비숍을 놓을 수 있는지 확인한다.
        // 비숍을 놓을 수 있다면 비숍을 놓고 다음 탐색을 진행한다.
        // 다음 탐색을 진행할 때는 x 좌표를 2 증가 시켜줘야 한다.
        // 이유는 바로 옆 자리는 백색이기 때문에 흑색칸을 탐색하기 위해서 2를 증가시켜준다.
        // 비숍을 두었기 때문에 경우의 수 count 를 증가시켜준다.
        if (isSafe(y, x)) {
            visit[y][x] = true;
            blackSearch(y, x + 2, count + 1);
            visit[y][x] = false; // 백트래킹.
        }

        // 현재 자리에 비숍을 놓을 수도 있고 안놓을 수도 있기 때문에 비숍을 놓지 않았을 경우에 대한 탐색도 진행해야 한다.
        blackSearch(y, x + 2, count);
    }

    private static void whiteSearch(int y, int x, int count) {
        whiteCount = Math.max(whiteCount, count);

        // x 좌표가 체스판의 크기보다 크면 좌표를 다음 칸의 첫 번째 x 좌표로 이동시킨다.
        // 단, 백색칸의 경우 y가 홀수일 경우 x의 시작점이 2부터 시작되고
        // 짝수일 경우, 1부터 시작된다.
        if (x > n) {
            y += 1;
            x = (y % 2 == 0) ? 1 : 2;
        }

        // y 좌표가 체크판의 크기를 초과할 경우 탐색을 종료한다.
        if (y > n) return;

        // 현재 자리에 비숍을 놓을 수 있는지 확인한다.
        // 놓을 수 있다면 비숍을 놓고 다음 탐색을 진행한다.
        if (isSafe(y, x)) {
            visit[y][x] = true;
            whiteSearch(y, x + 2, count + 1);
            visit[y][x] = false;
        }

        // 현재 자리에 비숍을 놓을 수도 있고 안놓을 수도 있기 때문에 비숍을 놓지 않았을 경우에 대한 탐색도 진행해야 한다.
        whiteSearch(y, x + 2, count);

    }

    private static boolean isSafe(int y, int x) {
        if (a[y][x] == 0) return false;

        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;

            for (int j = 1; j <= n; j++) {
                if (ny <= 0 || nx <= 0 || ny > n || nx > n) continue;

                if (visit[ny][nx]) return false;

                // 해당하는 대각선 방향으로 계속 가면서 확인을 한다.
                ny = ny + dy[i];
                nx = nx + dx[i];
            }
        }
        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
