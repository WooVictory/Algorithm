package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 25/05/2019
 * 완탐 : DSLR
 * from 배열은 어떤 수에서 만들었는지
 * how 배열은 어떻게 만들었는지
 * A -> B 를 만드는 것이 문제의 핵심.
 * 다시 풀어보기.
 */
public class BOJ9019 {
    private static int A, B;
    private static final int MOD = 10000;
    private static boolean[] visited;
    private static int[] distance, from;
    private static char[] how;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = parse(br.readLine());

        while (test_case-- > 0) {
            String[] input = br.readLine().split(" ");
            A = parse(input[0]);
            B = parse(input[1]);
            visited = new boolean[10001];
            distance = new int[10001];
            from = new int[10001];
            how = new char[10001];

            distance[A] = 0;
            from[A] = -1;

            bfs();
        }
    }

    private static void bfs() {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(A);
        visited[A] = true;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next = (now * 2) % MOD; // D 연산.
            // 방문한 적이 없으면
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'D';
            }

            next = now - 1; // S 연산.
            if (next == -1) next = 9999;

            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'S';
            }

            next = (now % 1000) * 10 + (now / 1000); // L 연산.
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'L';
            }

            next = (now % 10) * 1000 + (now / 10); // R 연산.
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'R';
            }
        }

        StringBuilder sb = new StringBuilder();
        while (A != B) {
            sb.append(how[B]); // B 를 만드는 방법을 꺼내서 붙인다.
            B = from[B]; // from[B]는 B가 어떤 수에서 만들어졌는지를 담고 있다.
        }
        System.out.println(sb.reverse());
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
