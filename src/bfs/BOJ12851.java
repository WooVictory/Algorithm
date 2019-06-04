package bfs;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 04/06/2019
 * bfs : 숨바꼭질2
 * distance : 가장 빠른 시간내에 도착할 수 있는지에 대한 최소 시간을 저장.
 * count : 최소 시간에 도착하는 경우의 수를 저장.
 * 다시 풀어보기!
 */
public class BOJ12851 {
    private static final int MAX = 1000000;
    private static boolean[] visited;
    private static int[] distance, count;
    private static int N, K;
    private static int[] state = {-1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        N = parse(input[0]);
        K = parse(input[1]);

        visited = new boolean[MAX];
        distance = new int[MAX];
        count = new int[MAX];


        bfs(N);
        bw.write(distance[K] - 1 + "\n" + count[K] + "");
        bw.flush();

    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        distance[start] = 1;
        count[start] = 1;

        while (!q.isEmpty()) {
            int now = q.remove();
            int next;

            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    next = now * state[i];
                } else {
                    next = now + state[i];
                }

                // 구한 값이 범위에 들어가는지 확인.
                if (-1 < next && next < MAX) {

                    // 다음 지점에 대한 시간이 현재까지 찾은 시간+1 인 경우. 즉, 최소 시간인 경우.
                    // 그리고 해당 조건에 대한 문장을 아래에서 실행한다.
                    // 그렇기에 중복 방문을 위한 문장이 된다.
                    // count 의 값을 증가시키는 것이 중복된다.
                    // 이유는 가장 빠른 시간으로 가는 방법의 수가 1개가 아니라 두개가 나올 수도 있는데,
                    // 여기서 바로 체크해버리면 그 다음 경우는 확인을 못하기 때문에
                    // 여기서는 값을 더해주고, 아래에서 다시 더해준 후, 방문했음을 체크한다.
                    if (distance[next] == distance[now] + 1) {
                        count[next] = count[next] + count[now];
                        //continue;
                    }

                    // 한번도 방문하지 않았거나, 최소값 갱신이 가능한 경우.
                    // 중복 방문을 하고 같은 코드인 count 의 값을 증가시켜준다.
                    if (!visited[next]) {
                        q.add(next);
                        visited[next] = true;

                        // 다음 위치를 최소값으로 갱신.
                        distance[next] = distance[now] + 1;
                        // 다음 위치를 최소값으로 갱신하기 때문에 현재 위치까지 최소값으로 온 경우의 수를 저장한다.
                        count[next] += count[now];
                    }
                }
            }
        }

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
