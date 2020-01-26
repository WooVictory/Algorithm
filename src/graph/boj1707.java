package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/26
 */
public class boj1707 {
    private static final String SPACE = " ";
    private static ArrayList<Integer>[] a;
    private static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(SPACE);
            int n = toInt(in[0]);
            int m = toInt(in[1]);

            a = (ArrayList<Integer>[]) new ArrayList[n + 1];
            visit = new int[n + 1];

            for (int i = 1; i <= n; i++) a[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                String[] input = br.readLine().split(SPACE);
                int v1 = toInt(input[0]);
                int v2 = toInt(input[1]);

                a[v1].add(v2);
                a[v2].add(v1);
            }

            for (int i = 1; i <= n; i++) {
                if (visit[i] == 0) {
                    dfs(i, 1);
                    bfs(i, 1);
                }
            }

            boolean isOkay = true;

            for (int i = 1; i <= n; i++) {
                for (int x : a[i])
                    if (visit[i] == visit[x]) isOkay = false;
            }

            System.out.println(isOkay ? "YES" : "NO");
        }

    }

    private static void dfs(int x, int state) {
        visit[x] = state;
        for (int v : a[x]) {
            if (visit[v] == 0) dfs(v, -state);
        }
    }

    private static void bfs(int x, int state) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(x);
        visit[x] = state;

        while (!q.isEmpty()) {
            int v = q.remove();

            for (int y : a[v]) {
                // 방문하지 않은 정점이면 큐에 넣고 다른 색으로 방문 여부를 표시한다.
                // 같은 레벨의 정점은 같은 색으로 색칠한다.
                if (visit[y] == 0) {
                    q.add(y);
                    visit[y] = -visit[v];
                }
            }

        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
