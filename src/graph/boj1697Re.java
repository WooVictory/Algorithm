package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/02
 * 숨바꼭질
 */
public class boj1697Re {
    private static final int MAX = 200000;
    private static boolean[] visit = new boolean[MAX + 1];
    private static int[] ways = {1, -1, 2}, from;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int n = toInt(in[0]), k = toInt(in[1]);

        from = new int[MAX + 1];

        bfs(n);
        System.out.println(from[k]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();
            for (int i = 0; i < ways.length; i++) {
                if (i == 2) next = now * ways[i];
                else next = now + ways[i];

                if (next < 0 || next > MAX) continue;

                if (visit[next]) continue;

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
