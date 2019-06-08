package bfs;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 07/06/2019
 * bfs : DSLR
 * 해당 문제에서는 distance 배열을 사용하지 않아도 됨.
 */
public class BOJ9019 {
    private static int A, B;
    private static final int MAX = 10000;
    private static int[] from;
    private static boolean[] visited;
    private static char[] how;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = parse(br.readLine());
        while (t-- > 0) {
            String[] input = br.readLine().split(" ");
            A = parse(input[0]);
            B = parse(input[1]);

            //distance = new int[MAX + 1];
            visited = new boolean[MAX + 1];
            how = new char[MAX + 1];
            from = new int[MAX + 1];

            bfs(A);
        }

        bw.flush();
    }

    private static void bfs(int start) throws IOException {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        //distance[start] = 0;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;

            // D 연산.
            next = (2 * now) % MAX;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                from[next] = now;
                //distance[next] = distance[now] + 1;
                how[next] = 'D';
            }

            // S 연산.
            next = now - 1;
            if (next == -1) next = MAX - 1;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                from[next] = now;
                //distance[next] = distance[now] + 1;
                how[next] = 'S';
            }

            // L 연산.
            next = (now % 1000) * 10 + now / 1000;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                from[next] = now;
                //distance[next] = distance[now] + 1;
                how[next] = 'L';
            }

            // R 연산.
            next = (now % 10) * 1000 + now / 10;
            if (!visited[next]) {
                q.add(next);
                visited[next] = true;
                from[next] = now;
                //distance[next] = distance[now] + 1;
                how[next] = 'R';
            }
        }

        StringBuilder sb = new StringBuilder();
        while (B != A) {
            sb.append(how[B]);
            B = from[B];
        }
        bw.write(sb.reverse() + "\n");
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }


}
