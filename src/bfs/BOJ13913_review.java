package bfs;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 05/06/2019
 * bfs : 숨바꼭질 4 리뷰.
 */
public class BOJ13913_review {
    private static final int MAX = 300000;
    private static final String SPACE = " ", NEW_LINE = "\n";
    private static int[] distance, from, result, state = {-1, 1};
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(SPACE);
        int n = parse(input[0]);
        int k = parse(input[1]);

        init();

        distance[0] = 0;

        if (n != k) {
            // 수빈이와 동생의 위치가 같지 않을 때 탐색을 진행한다.
            bfs(n);
            int value = distance[k];
            sb.append(value).append(NEW_LINE);

            // from 배열에 어디서부터 왔는지 담겨 있으므로 역 추적을 한다.
            // 역추적을 해서 result 배열에 담는다.
            for (int i = value - 1, current = k; i >= 0; i--) {
                result[i] = from[current];
                current = from[current];
            }

            // result 배열에서 원소를 차례대로 빼서 sb 객체를 이용해 append 한다.
            for (int i = 0; i < value; i++) {
                sb.append(result[i]).append(SPACE);
            }

            sb.append(k);
        } else {
            // n == k 인 경우.
            // 수빈이와 동생의 위치가 같다.
            // 즉, 걸리는 시간은 0초이며 경로는 해당 위치가 된다.
            sb.append(0).append("\n").append(n);
        }

        bw.write(sb.toString());
        bw.flush();

    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();

            // 걷기, 순간이동에 대해서 확인.
            for (int i = 0; i < 3; i++) {
                if (i == 2)
                    next = now * i;
                else
                    next = now + state[i];

                // 범위에 들어가는지, 방문한 적이 없는지 확인.
                if (-1 < next && next <= MAX && !visited[next]) {
                    q.add(next);
                    visited[next] = true;
                    distance[next] = distance[now] + 1;
                    from[next] = now; // 경로를 위해서 next 가 now 로부터 왔음을 저장한다.
                }
            }
        }
    }

    private static void init() {
        distance = new int[MAX + 1];
        from = new int[MAX + 1];
        visited = new boolean[MAX + 1];
        result = new int[MAX + 1];
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
