package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 25/06/2019
 * dfs : 텀 프로젝트
 * 다른 방법.
 * started : 어디서부터 시작했는지 시작 정점을 저장하는 배열.
 * checked : 탐색된 정점의 개수를 저장한다. 즉, 몇개의 정점을 거쳐왔는지!
 *
 */
public class BOJ9466_other {
    private static int[] a, started, checked;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = parse(br.readLine());

        while (t-- > 0) {
            int n = parse(br.readLine());
            a = new int[n + 1];
            started = new int[n + 1];
            checked = new int[n + 1];
            result = 0;

            String[] in = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                a[i] = parse(in[i - 1]);
            }

            for (int i = 1; i <= n; i++) {
                if (checked[i] == 0) {
                    // result 변수에는 싸이클을 구성하는 정점의 개수를 구한다.
                    result += dfs(i, i);
                }
            }
            System.out.println(n - result);
        }
    }

    // v : 정점
    // step : 시작 정점
    private static int dfs(int v, int step) {
        int count = 1;

        while (true) {

            // 방문했는지 여부를 확인한다.
            if (checked[v] != 0) {
                // 이미 방문했고, 시작 정점이 다르다면 싸이클이 아니다.
                if (started[v] != step) {
                    return 0;
                }

                // 싸이클의 경우.
                // 탐색된 정점의 개수 - 싸이클이 존재하는 정점에 대한 길이 => 싸이클을 형성하는 정점의 개수
                return count - checked[v];
            }

            // v 정점이 어디서부터 시작했는지 저장한다.
            started[v] = step;
            // v 정점이 몇개의 정점을 거쳐왔는지 저장한다.
            checked[v] = count;
            // 다음 탐색을 위해 v 정점이 가리키는 정점을 v에 저장한다.
            v = a[v];
            // 탐색할 때마다 정점의 개수를 증가시킨다.
            count++;
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
