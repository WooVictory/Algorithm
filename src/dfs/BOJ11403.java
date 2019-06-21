package dfs;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 21/06/2019
 * dfs : 경로 찾기.
 */
public class BOJ11403 {
    private static int n;
    private static int[][] a, path;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = parse(br.readLine());
        a = new int[n + 1][n + 1]; // 인접 행렬로 이루어진 방향 그래프.
        path = new int[n + 1][n + 1]; // i -> j로 가는 길 여부를 저장하는 2차원 배열.
        visited = new int[n + 1]; // 방문 여부를 저장하는 배열.

        // 입력.
        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[i][j] = parse(in[j - 1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            dfs(i); // 정점에 대해서 dfs 탐색을 진행한다.

            for (int j = 1; j <= n; j++) {
                // path 배열에 visited 배열을 이용해 표시한다.
                path[i][j] = visited[j];
            }
            // 탐색을 다시 하기 위해 리셋한다.
            Arrays.fill(visited, 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(path[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

    }

    // dfs 탐색.
    private static void dfs(int v) {
        int len = a.length - 1;

        // 정점 v에서 정점 i로 갈 수 있는지 확인한다.
        // 정점 v에서 정점 i로 갈 수 있고 방문한 적이 없다면 visited 배열을 1로 표시함으로써
        // 갈 수 있음(경로가 존재)을 표시하고 해당 정점 i를 가지고 다시 dfs 탐색을 한다.
        for (int i = 1; i <= len; i++) {
            if (a[v][i] == 1 && visited[i] == 0) {
                visited[i] = 1;
                dfs(i);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}