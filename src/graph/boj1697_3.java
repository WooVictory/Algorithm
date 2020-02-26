package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/20
 */
public class boj1697_3 {
    private static final int MAX = 100000;
    private static int n, k;
    private static int[] distance;
    private static boolean[] visit;
    private static int[] state = {-1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        n = toInt(in[0]);
        k = toInt(in[1]);

        visit = new boolean[MAX + 1];
        distance = new int[MAX + 1];

        //dfs(n);
        bfs();
        System.out.println(distance[k]);
    }

    private static void bfs() {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(n);
        visit[n] = true;
        distance[n] = 0;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;

            for (int i = 0; i < 3; i++) {
                if (i == 2) next = now * state[i];
                else next = now + state[i];

                if (next < 0 || next > MAX) continue;

                if (!visit[next]) {
                    visit[next] = true;
                    q.add(next);
                    distance[next] = distance[now] + 1;
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
