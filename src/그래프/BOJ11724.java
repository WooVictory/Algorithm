package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 02/04/2019
 * 그래프 : 연결 요소의 개수
 * DFS로 풀었다. 시간 -> 1280ms
 * 정렬하지 않고 DFS로 풀면 -> 780ms 걸림.
 * 보통 DFS는 시작 정점에서 방문을 시작하는데
 * 이 문제는 모든 정점에 대해서 방문을 해서 확인한다.
 * 그래야지 연결 요소의 개수를 구할 수 있다.
 */
public class BOJ11724 {
    private static final String SPACE = " ";
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(SPACE);
        int n = Integer.parseInt(input[0]); // 정점의 개수
        int m = Integer.parseInt(input[1]); // 간선의 개수

        a = new ArrayList[n + 1];
        visit = new boolean[n + 1];

        // 초기화.
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] inputs = br.readLine().split(SPACE);
            int u = Integer.parseInt(inputs[0]);
            int v = Integer.parseInt(inputs[1]);
            a[u].add(v);
            a[v].add(u);
        }

        // 인접한 정점이 여러 개일 경우, 가장 작은 정점부터 방문하기 위해서 정렬해준다.
/*        for (int i = 1; i <= n; i++) {
            Collections.sort(a[i]);
        }*/

        System.out.println(dfsAll(n));


    }

    private static int dfsAll(int N) {
        int components = 0;

        for (int i = 1; i <= N; i++) {
            if (!visit[i]) {
                components++;
                dfs(i);
            }
        }
        return components;
    }


    private static void dfs(int x) {
        if (visit[x]) {
            return;
        }

        visit[x] = true;

        for (int i = 0; i < a[x].size(); i++) {
            int y = a[x].get(i);
            if (!visit[y]) {
                dfs(y);
            }
        }
        /*for (int y : a[x]) {
            if (!visit[y]) {
                dfs(y);
            }
        }*/
    }
}
