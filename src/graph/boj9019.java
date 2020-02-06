package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/06
 * DSLR
 * dfs.
 * 다시 풀어보기!
 */
public class boj9019 {

    private static final int MAX = 10000;
    private static boolean[] visit;
    private static char[] command; // 해당 값이 어떤 명령어를 수행해서 나온 결과인지 저장한다.
    private static int[] from; // 해당 값이 이전에 어떤 값으로부터 만들어진 결과인지 저장한다.
    private static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            a = toInt(in[0]);
            b = toInt(in[1]);

            visit = new boolean[MAX + 1];
            command = new char[MAX + 1];
            from = new int[MAX + 1];

            bfs();
        }
    }

    private static void bfs() {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(a);
        visit[a] = true;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;

            // 큐에서 꺼낸 값이 b와 같을 경우, while 문 탈출.
            // 4000ms -> 3000ms 로 단축.
            if (now == b) break;

            // 1. D 연산.
            next = (now * 2);

            // D 연산을 한 뒤, next 에 대한 조건 검사.
            if (next > MAX - 1) next %= MAX;
            if (!visit[next]) {
                q.add(next);
                visit[next] = true;
                from[next] = now;
                command[next] = 'D';
            }

            // 2. S 연산.
            // now 에서 1을 뺀 결과를 next 에 저장한다.
            // 여기서 now 가 0이라면 next 에는 9999가 저장된다.
            // 따라서 조건 검사는 now 가 0일 경우, next = 0 - 1이므로 <next == -1>로 조건 검사를 해줘야 한다.
            next = now - 1;
            if (next == -1) next = MAX - 1;
            if (!visit[next]) {
                q.add(next);
                visit[next] = true;
                from[next] = now;
                command[next] = 'S';
            }

            // 3. L 연산.
            next = (now % 1000) * 10 + now / 1000;
            if (!visit[next]) {
                q.add(next);
                visit[next] = true;
                from[next] = now;
                command[next] = 'L';
            }

            // 4. R 연산.
            next = (now % 10) * 1000 + now / 10;
            if (!visit[next]) {
                q.add(next);
                visit[next] = true;
                from[next] = now;
                command[next] = 'R';
            }
        }

        StringBuilder sb = new StringBuilder();
        while (a != b) {
            sb.append(command[b]);
            b = from[b];
        }

        System.out.println(sb.reverse().toString());
    }

    static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
