package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/11
 * 스타트 링크.
 * bfs
 * 다시 풀어보기!
 */
public class boj5014 {
    private static int f, g, s, u, d;
    private static boolean[] visit;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        f = toInt(in[0]);
        s = toInt(in[1]);
        g = toInt(in[2]);
        u = toInt(in[3]);
        d = toInt(in[4]);

        visit = new boolean[f + 1];
        distance = new int[f + 1];

        bfs(s);
        if (distance[g] == 0 && !visit[g]) System.out.println("use the stairs");
        else System.out.println(distance[g]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        distance[start] = 0;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;

            if (now == g) return;

            next = now + u;
            if (next <= f && !visit[next]) {
                q.add(next);
                visit[next] = true;
                distance[next] = distance[now] + 1;
            }

            next = now - d;
            if (0 < next && !visit[next]) {
                q.add(next);
                visit[next] = true;
                distance[next] = distance[now] + 1;
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
