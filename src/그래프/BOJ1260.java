package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 01/04/2019
 * 그래프 :  DFS, BFS
 * DFS - Stack
 * BFS - Queue
 */
public class BOJ1260 {
    private static ArrayList<Integer>[] a;
    private static boolean[] check; // 방문했는지 체크하는 배열.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 정점의 개수
        int M = Integer.parseInt(input[1]); // 간선의 개수
        int V = Integer.parseInt(input[2]); // 탐색을 시작할 정점의 번호

        // 인접 리스트 초기화.
        a = (ArrayList<Integer>[]) new ArrayList[N + 1];
        // 1 ~ N까지 각 노드마다 인접 리스트 생성.
        for (int i = 1; i <= N; i++) {
            a[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            String[] inputs = br.readLine().split(" ");
            int u = Integer.parseInt(inputs[0]);
            int v = Integer.parseInt(inputs[1]);

            a[u].add(v);
            a[v].add(u);
        }


        for (int i = 1; i <= N; i++) {
            Collections.sort(a[i]);
        }
        // 방문할 수 있는 정점이 여러 개일 경우 정점 번호가 작은 것부터 방문하기 위해서 정렬한다.

        // 방문했는지 확인하는 배열 초기화.
        check = new boolean[N + 1];
        dfs(V);
        System.out.println();

        check = new boolean[N + 1];
        bfs(V);
        System.out.println();
    }

    // DFS
    private static void dfs(int x) {
        // 방문한 적이 있다면 종료한다.
        if (check[x]) {
            return;
        }

        // 방문했다고 체크한다.
        check[x] = true;
        System.out.print(x + " ");

        for (int y : a[x]) {
            if (!check[y]) {
                dfs(y);
            }
        }
    }

    private static void bfs(int start) {
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(start);
        check[start] = true;

        while (!queue.isEmpty()) {
            int x = queue.remove(); // 큐에서 정점을 뺀다.
            System.out.print(x + " ");

            for (int y : a[x]) {
                // 방문한 적이 없다면
                if (!check[y]) {
                    // 방문했다고 true로 체크한다.
                    check[y] = true;
                    // 그리고 큐에 넣는다.
                    queue.add(y);
                }
            }

        }

    }
}
