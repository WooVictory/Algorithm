package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 04/04/2019
 * 그래프 : 텀 프로젝트
 * 다시 풀어보기!
 */
public class BOJ9466 {
    private static int[] a; // 정점
    private static int[] visited; // 방문 순서. 탐색된 정점의 개수를 저장한다.
    private static int[] finished; // 시작 정점. 정점 기준

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            a = new int[n + 1];
            visited = new int[n + 1];
            finished = new int[n + 1];

            String[] input = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(input[i - 1]);
            }

            int result = 0;

            for (int i = 1; i <= n; i++) {
                result += dfs(i, i);
            }
            System.out.println(n - result);
        }
    }

    // step : 시작 정점
    // x : 현재 정점.
    // 싸이클이 아닌 정점의 개수를 찾아야한다.
    private static int dfs(int x, int step) {
        int count = 1;

        while (true) {
            // 방문한 적이 있다는 뜻.
            if (visited[x] != 0) {
                // 정점 시작점이 다를 경우 사이클이 아니다.
                // 즉, 다른 BFS 라는 뜻.
                if (finished[x] != step) {
                    return 0;
                }
                return count - visited[x];
            }

            finished[x] = step;
            visited[x] = count;
            x = a[x];
            count++;
        }
    }
}
