package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 25/06/2019
 * dfs : 텀프로젝트.
 * visited : 방문 여부를 체크하는 배열.
 * finished : 방문한 노드에서 싸이클을 뽑아냈었는가를 확인하는 배열.
 */
public class BOJ9466 {
    private static int count;
    private static int[] a;
    private static boolean[] visited, finished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = parse(br.readLine());

        while (t-- > 0) {
            int n = parse(br.readLine());
            count = 0;
            a = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];

            String[] in = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                a[i] = parse(in[i - 1]);
            }

            for (int i = 1; i <= n; i++) {
                dfs(i);
            }

            // 싸이클을 형성하는 노드의 갯수를 구하고, 원래 노드에서 빼주면
            // 싸이클을 형성하지 못하는 노드의 갯수를 구할 수 있다.
            System.out.println(n - count);
        }
    }

    private static void dfs(int now) {
        if (visited[now]) {
            return;
        }

        visited[now] = true;
        int next = a[now]; // 다음 노드를 가져온다.

        // 방문한 적이 있는지 없는지 확인한다.
        // 방문한 적이 없다면.
        if (!visited[next]) {
            dfs(next);
        } else {
            // 방문한 적이 있지만, 싸이클을 뽑아낸 적이 없다면.
            if (!finished[next]) {
                // 노드가 끝나기 위해서는 싸이클을 무조건 거쳐야 한다.
                // 따라서 현재 노드가 아닌 다음 노드를 기준으로 하면 싸이클이 무조건 존재한다.
                // 싸이클이기 때문에 현재 now 로 돌아올 때까지 뽑아주면 된다.

                count++;

                for (int i = next; i != now; i = a[i]) {
                    count++;
                }
            }
        }

        // 모든 작업이 끝나서 더 이상 사용되지 않으므로 true 로 변경한다.
        finished[now] = true;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
