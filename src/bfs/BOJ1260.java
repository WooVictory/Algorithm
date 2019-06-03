package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 03/06/2019
 * bfs 와 dfs
 * 인접 리스트를 사용.
 */
public class BOJ1260 {
    private static ArrayList<Integer>[] a;
    private static boolean[] visited;
    private static int N, M, start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = parse(input[0]); // 정점의 갯수.
        M = parse(input[1]); // 간선의 갯수.
        start = parse(input[2]); // 시작 정점 번호.

        a = (ArrayList<Integer>[]) new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] numbers = br.readLine().split(" ");
            int v1 = parse(numbers[0]);
            int v2 = parse(numbers[1]);

            // 양방향 그래프이기 때문에.
            a[v1].add(v2);
            a[v2].add(v1);
        }

        // 정점이 여러개 인 경우, 정점 번호가 적은 것을 먼저 방문하기 위해서 정렬한다.
        for (int i = 0; i < N; i++) {
            Collections.sort(a[i]);
        }

        dfs(start);
        System.out.println();
        visited = new boolean[N + 1];

        bfs(start);
        System.out.println();

    }

    // dfs - 깊이 우선 탐색.
    private static void dfs(int start) {

        visited[start] = true;
        System.out.print(start + " ");

        for (int number : a[start]) {
            if (!visited[number]) {
                dfs(number);
            }
        }

    }


    // bfs - 너비 우선 탐색.
    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int x = q.poll();
            System.out.print(x + " ");

            for (int number : a[x]) {
                if (!visited[number]) {
                    q.add(number);
                    visited[number] = true;
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
