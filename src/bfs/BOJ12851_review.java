package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 05/06/2019
 * bfs : 숨바꼭질2 리뷰
 */
public class BOJ12851_review {
    private static final int MAX = 300000;
    private static int[] distance, count, state = {-1, 1};
    private static boolean[] visited;
    private static int n,k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = parse(input[0]);
        k = parse(input[1]);

        distance = new int[MAX + 1];
        count = new int[MAX + 1];
        visited = new boolean[MAX + 1];

        distance[n] = 1;
        count[n] = 1;

        bfs(n);
        // 위에서 시작할 때 distance[n]에 1을 세팅하고 시작했기 때문이다.
        System.out.println(distance[k]-1);
        System.out.println(count[k]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;


            for (int i = 0; i < 3; i++) {
                if (i == 2)
                    next = now * 2;
                else
                    next = now + state[i];

                // 범위에 들어오는지 확인.
                if (-1 < next && next < MAX) {
                    if (distance[next] == distance[now] + 1) {
                        count[next] += count[now];
                    }

                    if (!visited[next]) {
                        q.add(next);
                        visited[next] = true;
                        distance[next] = distance[now] + 1;
                        count[next] += count[now];
                    }
                }
            }
        }

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
