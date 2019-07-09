package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 09/07/2019
 * 숨바꼭질.
 */
public class BOJ1697 {
    private static final int MAX = 300000;
    private static int n, k;
    private static int[] a, distance;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]); // 수빈이의 위치.
        k = parse(in[1]); // 동생의 위치.

        a = new int[MAX + 1];
        distance = new int[MAX + 1];
        visited = new boolean[MAX + 1];

        distance[0] = 0;
        bfs(n);
        System.out.println(distance[k]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;


        while (!q.isEmpty()) {
            int now = q.remove();

            int next = now - 1;
            if (next < 0 || next > MAX)
                continue;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
            }

            next = now + 1;
            if (next < 0 || next > MAX)
                continue;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
            }

            next = now * 2;
            if (next < 0 || next > MAX)
                continue;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
            }
        }
    }


    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
