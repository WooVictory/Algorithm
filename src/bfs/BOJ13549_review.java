package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 05/06/2019
 * bfs : 숨바꼭질3 리뷰
 */
public class BOJ13549_review {
    private static final int MAX = 100000;
    private static int[] distance, state = {-1, 1};
    private static boolean[] visited;
    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = parse(input[0]);
        k = parse(input[1]);

        distance = new int[MAX + 1];
        visited = new boolean[MAX + 1];

        //distance[n] = 1;

        bfs(n);

        System.out.println(distance[k]);

    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;
            int temp = now * 2;

            // 수빈이가 동생을 찾은 경우. 탐색을 중지한다.
            if (now == k) {
                return;
            }

            // 0초 후에 순간이동하는 경우에 대해서 먼저 구해놓고 큐에 넣어놓는다.
            while (temp <= MAX && !visited[temp]) {
                q.add(temp);
                distance[temp] = distance[now];
                visited[temp] = true;
                temp *= 2;
            }

            for (int i = 0; i < 2; i++) {
                next = now + state[i];

                if (-1 < next && next < MAX && !visited[next]) {
                    q.add(next);
                    visited[next] = true;
                    distance[next] = distance[now] + 1;
                }
            }
        }

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
