package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 03/06/2019
 * bfs : 숨바꼭질.
 * 범위에 맞는지 체크해야됨.
 */
public class BOJ1697 {
    private static final int MAX = 300000;
    private static boolean[] visited;
    private static int[] distance;
    private static int[] state = {-1, 1, 2}; // 수빈이의 3가지 상태를 체크하기 위한 배열.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = parse(input[0]);
        int k = parse(input[1]);

        visited = new boolean[MAX + 1];
        distance = new int[MAX + 1];

        distance[0] = 0;

        bfs(n);

        System.out.println(distance[k]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();
            // for 문으로 3가지 경우를 체크할 때, 한번에 묶을 수 있어 효율적이다.
            for (int i = 0; i < state.length; i++) {
                if (i == 2) {
                    next = now * state[i];
                } else {
                    next = now + state[i];
                }

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
