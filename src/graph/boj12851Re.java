package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/02
 * 다시 풀어보기!
 */
public class boj12851Re {
    private static final int MAX = 200000;
    private static int[] distance = new int[MAX + 1];
    private static int[] way = new int[MAX + 1];
    private static boolean[] visit = new boolean[MAX + 1];
    private static int[] state = {1, -1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int n = toInt(in[0]), k = toInt(in[1]);

        bfs(n);
        System.out.println(distance[k]);
        System.out.println(way[k]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        way[start] = 1;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < state.length; i++) {
                if (i == 2) next = now * state[i];
                else next = now + state[i];

                if (next < 0 || next > MAX) continue;

                if (distance[next] == distance[now] + 1) way[next] += way[now];

                if (!visit[next]) {
                    visit[next] = true;
                    distance[next] = distance[now] + 1;
                    way[next] += way[now];
                    q.add(next);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}