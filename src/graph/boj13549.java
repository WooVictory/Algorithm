package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/29
 * 숨바꼭질3.
 */
public class boj13549 {
    private static final int MAX = 100000;
    private static int[] distance;
    private static boolean[] visit;
    private static int[] state = {1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int k = toInt(in[1]);

        distance = new int[MAX + 1];
        visit = new boolean[MAX + 1];

        bfs(n);
        System.out.println(distance[k]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();
            int fastMoving = now * 2;

            // 0초가 걸리는 순간 이동을 미리 계산해서 큐에 먼저 넣는다.
            while (fastMoving < MAX && !visit[fastMoving]) {
                visit[fastMoving] = true;
                distance[fastMoving] = distance[now];
                q.add(fastMoving);
                fastMoving *= 2;
            }

            for (int move : state) {
                next = now + move;

                if (next < 0 || next > MAX) continue;

                if (!visit[next]) {
                    visit[next] = true;
                    distance[next] = distance[now] + 1;
                    q.add(next);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
