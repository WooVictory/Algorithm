package 완전탐색;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 10/05/2019
 * 완전 탐색 : 숨바꼭질.
 * 기초적인 bfs 라고 보면 된다.
 */
public class BOJ1697 {
    private static final int MAX = 1000000;
    private static boolean[] visited = new boolean[MAX];
    private static int[] distance = new int[MAX];
    private static int[] moveState = {-1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = parse(input[0]); // 수빈이 위치.
        int k = parse(input[1]); // 동생 위치.
        bfs(n);
        bw.write(distance[k] + "");
        bw.flush();
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    private static void bfs(int n) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(n);
        distance[n] = 0;
        visited[n] = true;
        int moveNumber;

        while (!q.isEmpty()) {
            int now = q.poll();
            // 수빈이가 이동할 수 있는 방법 3가지를 처리한다.
            for (int i = 0; i < moveState.length; i++) {
                if (i == 2)
                    moveNumber = now * moveState[i]; // 순간이동.
                else
                    moveNumber = now + moveState[i]; // 걷기.

                // moveNumber 는 수빈이가 이동할 수 있는 정점이라고 생각하면 된다.
                // moveNumber 가 아래 범위에 들어가는지 확인하고 방문한 적이 없는지 확인한다.
                // 조건을 만족하면 큐에 넣고 방문했음을 표시하고 distance 배열에 거리를 넣는다.
                if (-1 < moveNumber && moveNumber < MAX && !visited[moveNumber]) {
                    q.add(moveNumber);
                    visited[moveNumber] = true;
                    distance[moveNumber] = distance[now] + 1;
                }
            }
        }
    }
}
