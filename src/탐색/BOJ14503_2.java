package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 11/07/2019
 * 로봇 청소기
 * dfs 방법.
 * a 배열의 값을 청소했다라는 상태로 바꾼다면 굳이 visited 배열이 필요 없다.
 */
public class BOJ14503_2 {
    private static int n, m, count;
    private static int[][] a;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        a = new int[n][m];

        in = br.readLine().split(" ");
        int start = parse(in[0]);
        int end = parse(in[1]);
        int direction = parse(in[2]);

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[i][j] = parse(in[j]);
            }
        }

        dfs(start, end, direction);
        System.out.println(count + 1);
    }

    /**
     *
     * @param x
     * @param y
     * @param d
     *
     * 규칙
     * 1. 4 방향을 돌면서 청소 가능한 곳을 탐색한다. 그리고 다시 dfs()를 호출한다.
     * 2. 4 방향 청소가 끝나면 후진한다.
     * 3. 후진이 불가능하면 return 한다.
     * */
    private static void dfs(int x, int y, int d) {
        a[x][y] = 2;

        int nx;
        int ny;
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            nx = x + dx[d];
            ny = y + dy[d];

            // 왼쪽이 청소 가능한 경우 청소를 한다.
            // 그 다음 return 해서 청소를 그만한다.
            // 왜냐하면 왼쪽 방향에 대해서만 청소를 해야 하는데, dfs()가 끝나서 원래 for()문 안으로 들어오면
            // 나머지 방향에 대해서 탐색을 진행하기 때문이다.
            if (0 <= nx && nx < n && 0 <= ny && ny < m && a[nx][ny] == 0) {
                count++;
                dfs(nx, ny, d);
                return;
            }
        }

        // 후진.
        int back = (d + 2) % 4;
        nx = x + dx[back];
        ny = y + dy[back];

        // 방향은 원래 바라보던 곳을 본 채로 후진을 한다.
        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
            if (a[nx][ny] != 1) {
                dfs(nx, ny, d);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}