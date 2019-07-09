package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 09/07/2019
 * 스타트 링크
 * max 따위 필요없음.
 * 왜냐하면 f가 건물의 꼭대기 층이기 때문에 이 범위 안에만 들면됨.
 */
public class BOJ5014 {
    private static int f, s, g, u, d;
    private static int[] distance;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        f = parse(in[0]);
        s = parse(in[1]);
        g = parse(in[2]);
        u = parse(in[3]);
        d = parse(in[4]);

        distance = new int[f + 1];
        visited = new boolean[f + 1];

        distance[0] = 0;
        bfs(s);

        // distance[g]가 0이라는 말은 방문한 적도 없다는 뜻이기 때문에
        // 조건을 두개 같이 걸어줘야 한다.
        if(distance[g] == 0 && !visited[g]){
            System.out.println("use the stairs");
        }else {
            System.out.println(distance[g]);
        }
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();

            int next = now + u;
            if (next <= f && !visited[next]) {
                q.add(next);
                visited[next] = true;
                distance[next] = distance[now] + 1;
            }

            next = now - d;
            if (0 < next && !visited[next]) {
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