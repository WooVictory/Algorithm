package dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * created by victory_woo on 22/06/2019
 * dfs : 영역 구하기.
 * 단지번호 붙이기 문제랑 비슷하다.
 */
public class BOJ2583 {
    private static final String SPACE = " ", NEW_LINE = "\n";
    private static int m, n, k, components, count;
    private static ArrayList<Integer> countList = new ArrayList<>();
    private static int[][] a;
    private static int[][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] in = br.readLine().split(SPACE);

        m = parse(in[0]);
        n = parse(in[1]);
        k = parse(in[2]);

        a = new int[m + 1][n + 1];
        visited = new int[m + 1][n + 1];

        // 1. 1로 초기화.
        for (int i = 0; i < m; i++) {
            Arrays.fill(a[i], 1);
        }

        //잘 출력되는지 확인.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        // 직사각형으로 채워질 부분에 대한 좌표를 입력받는다.
        while (k-- > 0) {
            String[] point = br.readLine().split(SPACE);
            int lx, ly, rx, ry;
            lx = parse(point[0]);
            ly = parse(point[1]);
            rx = parse(point[2]);
            ry = parse(point[3]);

            // 좌표를 표현하는 방법과 2차원 배열로 표현하는 방법이 다르기 때문에
            // x,y 값을 바꿔준다.
            for (int i = ly; i < ry; i++) {
                for (int j = lx; j < rx; j++) {
                    a[i][j] = 0; // 영역 색상을 칠한다.
                    visited[i][j] = 1; // 방문 했음을 표시한다.
                }
            }
        }

        //잘 출력되는지 확인.
        System.out.println("== 결과 ==");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 0 : 직사각형을 칠한 부분
                // 1 : 빈 공간. 즉, 빈 공간이면서 방문한 적이 없다면 탐색 시작.
                if (a[i][j] == 1 && visited[i][j] == 0) {
                    components++;
                    count = 0;
                    dfs(i, j);

                    countList.add(count);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(components).append(NEW_LINE);

        Collections.sort(countList);
        for (int value : countList) {
            sb.append(value).append(SPACE);
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int x, int y) {
        if (visited[x][y] == 1) {
            return;
        }

        visited[x][y] = 1;
        count++;

        // 네 방향을 검사해준다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                continue;

            if (a[nx][ny] == 1 && visited[nx][ny] == 0) {
                dfs(nx, ny);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
