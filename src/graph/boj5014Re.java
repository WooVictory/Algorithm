package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/11
 */
public class boj5014Re {
    private static int f, s, g, u, d;
    private static int[] distance;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        f = toInt(in[0]); // 건물의 총 높이.
        s = toInt(in[1]); // 상근이의 위치.
        g = toInt(in[2]); // 스타트링크의 위치.
        u = toInt(in[3]); // 위로 올라가는 버튼의 가중치.
        d = toInt(in[4]); // 아래로 내려가는 버튼의 가중치.

        // 건물의 높이가 f 층이기 때문에 f+1로 크기를 초기화한다.
        distance = new int[f + 1];
        visit = new boolean[f + 1];

        bfs(s);

        // 스타트 링크가 위치한 곳까지의 이동 거리가 0이고 방문한 적이 없는 경우는 스타트 링크에 가지 못한 경우.
        if (distance[g] == 0 && !visit[g]) System.out.println("use the stairs");
        else System.out.println(distance[g]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int now = q.remove();

            if (now == g) return;

            // 위로 올라가는 버튼 눌렀을 때.
            int next = now + u;

            // f 보다 작거나 같고 방문한 적이 없는지 확인한다.
            if (next <= f && !visit[next]) {
                q.add(next);
                visit[next] = true;
                distance[next] = distance[now] + 1;
            }

            // 아래로 내려가는 버튼을 눌렀을 때.
            next = now - d;

            // 버튼을 눌렀을 때, 0보다 크고 방문한 적이 없는지 확인한다.
            if (0 < next && !visit[next]) {
                q.add(next);
                visit[next] = true;
                distance[next] = distance[now] + 1;
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
