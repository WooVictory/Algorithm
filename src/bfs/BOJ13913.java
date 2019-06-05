package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 04/06/2019
 * bfs : 숨바꼭질4
 * 경로 찾기.
 * 어렵네.. 내일 풀자...
 *
 */
public class BOJ13913 {
    private static final int MAX = 300000;
    private static boolean[] visited;
    private static int[] distance, from;
    private static int[] state = {-1, 1, 2}; // 수빈이의 3가지 상태를 체크하기 위한 배열.
    private static StringBuilder sb = new StringBuilder();
    private static int[] result;
    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = parse(input[0]);
        k = parse(input[1]);

        visited = new boolean[MAX + 1];
        distance = new int[MAX + 1];
        from = new int[MAX + 1];
        result = new int[MAX + 1];

        distance[0] = 0;
        if (n != k) {
            bfs(n);

            int value = distance[k];

            sb.append(value).append("\n");

            for (int i = value - 1, current = k; i >= 0; i--) {
                result[i] = from[current];
                current = from[current];
            }

            for (int i = 0; i < value; i++) {
                sb.append(result[i]).append(" ");
            }

            sb.append(k);
        } else {
            sb.append(0).append("\n").append(n);
        }

        System.out.println(sb);
        System.out.println(from.length);

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
                    from[next] = now;
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
