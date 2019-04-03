package 그래프;

import java.io.*;

/**
 * created by victory_woo on 03/04/2019
 * 그래프 : 순열 싸이클
 * 단방향 그래프이다. 즉 방향을 갖는다.
 * 이럴 때는 인접 리스트, 배열을 사용해서 풀 수 있다.
 * 인접 리스트로 풀면 704ms 걸림
 * 배열로 풀면 504ms 걸림.
 */
public class BOJ10451_RE {
    private static int[] a;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            a = new int[n + 1];
            visit = new boolean[n + 1];

            String[] input = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(input[i - 1]);
            }

            int count = 0;

            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    dfs(i);
                    count++;
                }
            }

            bw.write(count + "\n");

        }

        bw.flush();
    }

    private static void dfs(int x) {
        visit[x] = true;

        int y = a[x];
        if (!visit[y]) {
            dfs(y);
        }
    }

}
