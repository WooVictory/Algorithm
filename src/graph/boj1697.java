package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/28
 */
public class boj1697 {
    private static final int MAX = 200000;
    private static int[] from;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int k = toInt(in[1]);

        from = new int[MAX + 1];
        visit = new boolean[MAX + 1];

        bfs(n);
        System.out.println(from[k]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next = now + 1;

            if (next < 0 || next > MAX) continue;

            if (!visit[next]) {
                visit[next] = true;
                from[next] = from[now] + 1;
                q.add(next);
            }

            next = now - 1;

            if (next < 0 || next > MAX) continue;

            if (!visit[next]) {
                visit[next] = true;
                from[next] = from[now] + 1;
                q.add(next);
            }

            next = now * 2;

            if (next < 0 || next > MAX) continue;

            if (!visit[next]) {
                visit[next] = true;
                from[next] = from[now] + 1;
                q.add(next);
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}