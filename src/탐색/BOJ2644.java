package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 08/07/2019
 * 촌수 계산.
 * 1부터 탐색이 아니라 7부터 start 부터 탐색해야 함.
 */
public class BOJ2644 {
    private static int n, m;
    private static ArrayList<Integer>[] a;
    private static int[][] arr;
    private static boolean[] visited;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parse(br.readLine());
        String[] in = br.readLine().split(" ");

        // 촌수 계산이 필요한 두 사람.
        int x = parse(in[0]);
        int y = parse(in[1]);

        m = parse(br.readLine());

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        arr = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        distance = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] num = br.readLine().split(" ");
            int u = parse(num[0]);
            int v = parse(num[1]);

            a[u].add(v);
            a[v].add(u);
            arr[u][v] = 1;
            arr[v][u] = 1;
        }

        bfsArr(x);
        System.out.println(distance[y] == 0 ? -1 : distance[y]);

    }

    // 인접 리스트를 사용했을 때의 dfs
    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int vv : a[now]) {
                if (!visited[vv]) {
                    q.add(vv);
                    visited[vv] = true;
                    distance[vv] = distance[now] + 1;
                }
            }
        }
    }

    // 인접 행렬을 사용했을 때의 dfs
    private static void bfsArr(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 1; i <= n; i++) {
                if (arr[now][i] == 0 || distance[i] != 0)
                    continue;

                q.add(i);
                distance[i] = distance[now] + 1;
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

}
