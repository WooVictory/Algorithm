package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/06/01
 * 숨바꼭질.
 */
public class boj1697 {
    private static final int MAX = 200000;
    private static boolean[] visit;
    private static int[] distance;
    private static int[] state = {1, -1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]), k = toInt(in[1]);

        visit = new boolean[MAX];
        distance = new int[MAX];

        bfs(n);
        System.out.println(distance[k]);
    }

    private static void bfs(int n) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(n);
        visit[n] = true;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;
            for (int i = 0; i < 3; i++) {
                if (i == 2) next = now * state[i];
                else next = now + state[i];

                if (next < 0 || next >= MAX) continue;
                if (visit[next]) continue;

                visit[next] = true;
                distance[next] = distance[now] + 1;
                q.add(next);
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
