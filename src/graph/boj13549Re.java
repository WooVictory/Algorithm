package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/02
 * 숨바꼭질3
 */
public class boj13549Re {
    private static final int MAX = 200000;
    private static int[] distance = new int[MAX + 1];
    private static boolean[] visit = new boolean[MAX + 1];
    private static int[] ways = {1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int n = toInt(in[0]), k = toInt(in[1]);


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
            int fast = now * 2;

            // 순간 이동은 0초가 걸리기 때문에 먼저 계산해서 큐에 넣는다.
            while (fast < MAX && !visit[fast]) {
                visit[fast] = true;
                distance[fast] = distance[now];
                q.add(fast);
                fast *= 2;
            }

            // 나머지 두 방법에 대해서 같은 방식으로 bfs 탐색 진행.
            for (int i : ways) {
                next = now + i;

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
