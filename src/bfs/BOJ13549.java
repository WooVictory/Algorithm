package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 04/06/2019
 * bfs : 숨바꼭질3
 */
public class BOJ13549 {
    private static final int MAX = 100000;
    private static int N, K, state[] = {1, -1};
    private static boolean[] visited;
    private static int[] distance;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = parse(input[0]);
        K = parse(input[1]);

        visited = new boolean[MAX + 1];
        distance = new int[MAX + 1];

        bfs(N);

        System.out.println(distance[K]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        distance[start] = 0;
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();

            // 수빈이가 동생을 찾으면 return. 그 후에 탐색은 진행하지 않는다.
            // 없어도 되는 문장인데, 그러면 시간이 조금 더 걸린다. 끝까지 탐색을 하기 때문.
            if (now == K) {
                return;
            }

            /*
            * 0초 후에 x->2*x 로 이동할 수 있다.
            * 이 문장이 의미하는 바는 내가 현재 위치가 5일 때, 0초 후에 10, 0초 후에 20,40,80... 이 된다는 말이다.
            * 즉, 순간 이동은 0초 안에 이루어지기 때문에 시간 소비가 없이 동생을 찾을 수도 있다.
            * 따라서 순간 이동 즉, *2인 거리 중 방문하지 않은 곳을 먼저 체크하고 계산한 다음에 -1,+1에 대한 처리를 해줘야 한다.
            * 큐에 먼저 넣어 놓는다.
            * */
            int tmp = now * 2;
            while (tmp <= MAX && !visited[tmp]) {
                distance[tmp] = distance[now];
                visited[tmp] = true;
                q.add(tmp);
                tmp *= 2;
            }

            // -1, +1에 대한 처리.
            for (int i = 0; i < 2; i++) {
                int next;
                next = now + state[i];

                if (-1 < next && next <= MAX && !visited[next]) {
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
