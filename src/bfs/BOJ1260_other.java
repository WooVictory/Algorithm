package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 03/06/2019
 * dfs 와 bfs
 * 인접 행렬을 이용한 풀이.
 * 시간이 조금 더 걸림.
 */
public class BOJ1260_other {
    private static int[][] a;
    private static boolean[] visited;
    private static int N, M, start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = parse(input[0]); // 정점의 갯수.
        M = parse(input[1]); // 간선의 갯수.
        start = parse(input[2]); // 시작 정점 번호.

        a = new int[N + 1][N + 1];
        visited = new boolean[N + 1];


        for (int i = 0; i < M; i++) {
            String[] numbers = br.readLine().split(" ");
            int v1 = parse(numbers[0]);
            int v2 = parse(numbers[1]);

            // 양방향 그래프이기 때문에.
            a[v1][v2] = 1;
            a[v2][v1] = 1;
        }

        dfs(start);
        System.out.println();

        visited = new boolean[N + 1];
        bfs(start);
        System.out.println();

    }

    // bfs
    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int x = q.poll();
            System.out.print(x + " ");

            for (int i = 1; i <= N; i++) {
                if (!visited[i] && a[x][i] == 1) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    // dfs
    private static void dfs(int start) {
        visited[start] = true;

        System.out.print(start + " ");

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && a[start][i] == 1) {
                dfs(i);
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

}
