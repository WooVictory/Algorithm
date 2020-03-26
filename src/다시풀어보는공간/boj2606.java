package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/26
 */
public class boj2606 {
    private static int n, m, count;
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        m = toInt(br.readLine());

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) a[i] = new ArrayList<>();
        visit = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            String[] in = br.readLine().split(" ");
            int v1 = toInt(in[0]), v2 = toInt(in[1]);
            a[v1].add(v2);
            a[v2].add(v1);
        }

        //bfs();
        // dfs()를 통해서 결과값을 구한 경우, 1까지 포함되기 때문에 1을 빼줘야 한다.
        dfs(1);
        System.out.println(count - 1);
    }

    private static void bfs() {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;

        while (!q.isEmpty()) {
            int num = q.remove();
            for (int vv : a[num]) {
                if (!visit[vv]) {
                    count++;
                    visit[vv] = true;
                    q.add(vv);
                }
            }
        }
    }

    private static void dfs(int v) {
        if (visit[v]) return;

        visit[v] = true;
        count++;

        for (int vv : a[v]) {
            dfs(vv);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
