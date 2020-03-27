package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/27
 * 숨바꼭질.
 * bfs 탐색을 사용하는 문제이다.
 * 어렵지 않다. 최소 비용을 구하는 문제 유형 중 하나이다.
 * 하지만, 이 문제는 dfs 를 사용해 풀리지 않는 것 같다.
 * 왜냐하면 종료 조건을 명확하게 걸기 어렵기 때문이다.
 */
public class boj1697 {
    private static final int MAX = 100000;
    private static int[] state = {1, -1, 2};
    private static boolean[] visit;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int n = toInt(in[0]);
        int k = toInt(in[1]);

        visit = new boolean[MAX + 1];
        distance = new int[MAX + 1];

        bfs(n);
        System.out.println(distance[k]);
    }

    private static void bfs(int v) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(v);
        visit[v] = true;

        while (!q.isEmpty()) {
            int cur = q.remove();
            int next;
            // 수빈이가 이동할 수 있는 경우의 수를 계산하여 큐에 넣는다.
            // x+1, x-1, x*2 를 계산해서 큐에 넣고 해당 정점에 갔을 때의 거리를 계산해서 넣어준다.
            for (int i = 0; i < 3; i++) {
                if (i == 2) next = cur * state[i];
                else next = cur + state[i];

                if (next < 0 || next > MAX) continue;

                if (!visit[next]) {
                    visit[next] = true;
                    distance[next] = distance[cur] + 1;
                    q.add(next);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
