package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/29
 * 숨바꼭질2
 */
public class boj12851 {

    private static final int MAX = 100000;
    private static int[] time; // 동생을 찾는데 걸린 최소 시간을 저장하는 배열.
    private static int[] ways; // 동생을 찾는데 최소 시간이 걸리는 최단 방법의 수를 저장하는 배열.
    private static boolean[] visit;
    private static int[] state = {1, -1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int k = toInt(in[1]);

        time = new int[MAX + 1];
        ways = new int[MAX + 1];
        visit = new boolean[MAX + 1];

        bfs(n);

        System.out.println(time[k]);
        System.out.println(ways[k]);
    }

    private static void bfs(int n) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(n);
        visit[n] = true;
        ways[n] = 1;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < state.length; i++) {
                if (i == 2) next = now * state[i];
                else next = now + state[i];

                if (next < 0 || next > MAX) continue;

                if (time[next] == time[now] + 1) ways[next] += ways[now];

                if (!visit[next]) {
                    visit[next] = true;
                    time[next] = time[now] + 1;
                    ways[next] += ways[now];
                    q.add(next);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
