package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 27/05/2019
 * 완탐 : DSLR
 */
public class BOJ9019_review {
    private static final int MAX = 10001, MOD = 10000;
    private static int A, B;
    private static boolean[] visited;
    private static int[] from, distance;
    private static char[] how;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = parse(br.readLine());

        while (test_case-- > 0) {
            String[] input = br.readLine().split(" ");
            A = parse(input[0]);
            B = parse(input[1]);

            visited = new boolean[MAX];
            from = new int[MAX];
            distance = new int[MAX];
            how = new char[MAX];

            distance[A] = 0;
            bfs();

        }
    }

    private static void bfs() {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(A);
        visited[A] = true;

        while (!q.isEmpty()) {
            int now = q.remove();
            // D 연산.
            int next = (2 * now) % MOD;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'D';
            }
            // S 연산.
            next = now - 1;
            if (next == -1) next = 9999;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'S';
            }
            // L 연산.
            next = (now % 1000) * 10 + (now / 1000);
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'L';
            }
            // R 연산.
            next = (now % 10) * 1000 + (now / 10);
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
                from[next] = now;
                how[next] = 'R';
            }
        }

        StringBuilder sb = new StringBuilder();
        // B 가 어떻게 만들어졌는지, 그리고 어떤 수에서 만들어졌는지를
        // A 로 돌아가면서 뽑아내면서 출력한다.
        while (B != A) {
            sb.append(how[B]);
            B = from[B];
        }
        System.out.println(sb.reverse());
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
