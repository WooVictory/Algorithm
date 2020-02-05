package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/02/05
 * 텀 프로젝트.
 * dfs.
 * started : 시작 정점이 어디인지 저장한다. 즉, 어디서부터 온 정점인지를 저장한다.
 * checked : 탐색된 정점의 갯수를 저장한다. 즉, 어떤 정점까지 오는데 몇 개의 정점을 거쳤는지를 저장한다.
 * 다시 풀어보기!
 */
public class boj9466_2 {
    private static int[] a, started, checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            int n = toInt(br.readLine());

            int result = 0;
            a = new int[n + 1];
            started = new int[n + 1];
            checked = new int[n + 1];

            String[] in = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                a[i] = toInt(in[i - 1]);
            }

            // 방문한 적이 없는 정점에 대해서 dfs 탐색 진행.
            for (int i = 1; i <= n; i++) {
                if (checked[i] == 0) {
                    result += dfs(i, i);
                }
            }

            System.out.println(n - result);
        }
    }

    // v : 정점, step : 시작 정점.
    private static int dfs(int v, int step) {
        int count = 0;

        while (true) {

            // 방문한 적이 있는 경우.
            if (checked[v] != 0) {

                // 방문한 적이 있고, 시작 정점과 다르면, 이는 싸이클이 아니다.
                if (started[v] != step) {
                    return 0;
                }

                // 싸이클인 경우.
                // 탐색된 정점의 개수 - 싸이클이 존재하는 정점에 대한 길이 => 싸이클을 형성하는 정점의 개수.
                return count - checked[v];
            }

            started[v] = step; // v 정점이 어디서부터 시작했는지 저장.
            checked[v] = count; // v 정점까지 오는데 몇 개의 정점을 거쳐왔는지 저장.
            v = a[v]; // 다음 탐색을 위해 v 정점이 가리키는 정점을 v에 대입.
            count++; // 탐색할 때마다, 정점의 개수를 증가시킨다.
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
