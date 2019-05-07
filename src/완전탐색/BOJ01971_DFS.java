package 완전탐색;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 07/05/2019
 * 완전 탐색 : 외판원 순회2
 * DFS 로도 풀 수 있음.
 */
public class BOJ01971_DFS {
    private static int[][] cost;
    private static boolean[] visited;
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        cost = new int[n][n]; // 각 도시의 비용을 담는 배열.
        visited = new boolean[n]; // 방문했는지 체크하는 배열.

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            dfs(i, i, 0, 0, n);
        }
        bw.write(result + "");
        bw.flush();
    }

    private static void dfs(int start, int x, int sum, int count, int n) {
        // count == n : 모든 도시를 다 들렀는지 체크하는 구문.
        // start == x : 다시 start 로 돌아온 경우를 확인하는 구문.
        if (count == n && start == x) {
            if (result > sum) {
                result = sum;
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            // 갈 수 없는 도시를 확인하고 건너뛰어 다음 도시를 확인한다.
            if (cost[x][i] == 0) {
                continue;
            }

            // 방문한 적이 없고 0이 아닌 경우.
            // 즉, 이동할 도시가 있다는 뜻.
            if (!visited[i] && cost[x][i] != 0) {
                visited[i] = true; // 방문함을 표시한다.
                sum += cost[x][i]; // 도시의 비용을 더해준다.

                // sum 이 result 보다 작은 경우만 탐색한다.
                // 큰 경우는 탐색하지 않는다.
                if (sum < result) {
                    dfs(start, i, sum, count + 1, n);
                }

                // 처음 시작 점으로 돌아오는 경우
                // n번째 도시에서 시작점으로 돌아오는 간선은 이미 방문했다고 표시가 되어 있다.
                // 따라서 방문했음을 false 로 설정함으로써 처음 점으로 돌아가는 경우 방문할 수 있도록 해줘야 한다.
                visited[i] = false;
                sum -= cost[x][i];
            }
        }

    }
}
