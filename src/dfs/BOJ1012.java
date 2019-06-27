package dfs;

import java.io.*;

/**
 * created by victory_woo on 25/06/2019
 * dfs : 유기농 배추.
 */
public class BOJ1012 {
    private static final String SPACE = " ", NEW_LINE = "\n";
    private static int m, n;
    private static int[][] a;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = parse(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(SPACE);
            m = parse(in[0]);
            n = parse(in[1]);
            int k = parse(in[2]);
            int components = 0;

            a = new int[m + 1][n + 1];
            visited = new boolean[m + 1][n + 1];

            // k만큼 배추의 좌표를 입력받는다.
            for (int i = 0; i < k; i++) {
                String[] num = br.readLine().split(SPACE);
                int x = parse(num[0]);
                int y = parse(num[1]);

                // 배추가 있음을 표시한다.
                a[x][y] = 1;
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 배추인 곳이면서 방문하지 않은 곳만 탐색 시작.
                    // dfs 탐색을 돌면서 같은 구성요소에 포함된다면 components 는 1만 증가한다.
                    // 인접해 있는 구성 요소에 대해서만 components 가 증가한다.
                    // components => 배추흰지렁이의 수.
                    if (a[i][j] == 1 && !visited[i][j]) {
                        components++;
                        dfs(i, j);
                    }
                }
            }
            bw.write(String.valueOf(components) + NEW_LINE);
        }
        bw.flush();

        /*
        확인을 위해 찍어보기.
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }*/
    }

    // dfs 탐색 시작.
    private static void dfs(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        // 네 방향을 검사한다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위에 벗어난다면 다음 정점을 검사한다.
            if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                continue;

            // 배추가 존재하고 방문한 적이 없다면 탐색한다.
            if (a[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}