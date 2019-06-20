package dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 20/06/2019
 * dfs : 단지 번호 붙이기
 * visited 배열을 사용하지 않은 경우.
 */
public class BOJ2667 {
    private static int n, components, count;
    private static int[][] a;
    private static ArrayList<Integer> countList = new ArrayList<>();
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = parse(br.readLine());

        // 초기화.
        a = new int[n + 1][n + 1];

        // 입력을 받는다.
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }

        find();
        StringBuilder sb = new StringBuilder();
        sb.append(components).append("\n");

        Collections.sort(countList);
        for (int x : countList) {
            sb.append(x).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

    }

    // 집인 위치를 찾는다. 찾은 즉시 dfs 탐색을 한다.
    private static void find() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    components++; // 단지를 찾았으므로 단지의 개수를 증가시킨다.
                    count = 0; // 각 단지에 몇 개의 집이 존재하는지 확인하는 변수.
                    dfs(i, j);

                    countList.add(count);
                }
            }
        }
    }

    // dfs 탐색.
    private static void dfs(int x, int y) {
        a[x][y] = 0; // 방문했음을 표시하기 위해서 1(집) -> 0으로 변경한다.
        count++; // 해당 단지의 집의 개수를 세기 위해서 dfs 탐색할 때마다 증가시킨다.

        // 네 방향을 검사한다.
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (a[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}