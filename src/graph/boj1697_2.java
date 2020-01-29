package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/29
 * state 라는 배열을 통해서 수빈이가 움직이는 행동을 상태로 나눠서 관리한다.
 * 이렇게 함으로써 for 문을 통해 조건 검사에 대한 중복 코드를 제거할 수 있다.
 */
public class boj1697_2 {
    private static final int MAX = 200000;
    private static int[] from, state = {-1, 1, 2};
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int k = toInt(in[1]);

        from = new int[MAX + 1];
        visit = new boolean[MAX + 1];

        bfs(n);
        System.out.println(from[k]);
    }

    private static void bfs(int x) {
        LinkedList<Integer> q = new LinkedList<>();
        visit[x] = true;
        q.add(x);
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < state.length; i++) {
                if (i == 2) next = now * state[i];
                else next = now + state[i];

                if (next < 0 || next > MAX) continue;

                if (!visit[next]) {
                    visit[next] = true;
                    from[next] = from[now] + 1;
                    q.add(next);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}