package 트리;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 09/04/2019
 * 트리 : 트리의 지름.
 * for문 2개를 1개로 통합함으로써
 * 시간 복잡도 1332 -> 680으로 줄임.
 */
public class BOJ1167 {
    private static ArrayList<Node>[] a;
    private static boolean[] visit;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int v = Integer.parseInt(br.readLine());
        a = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()); // 처음 정점.
            a[x] = new ArrayList<>();
            while (true) {
                int y = Integer.parseInt(st.nextToken());// 다음 정점.;

                if (y == -1) {
                    break;
                }

                int z = Integer.parseInt(st.nextToken());// 가중치.

                // 정점 x와 정점 y가 z 가중치를 갖는 간선으로 연결되어 있다는 정보를 저장한다.
                a[x].add(new Node(y, z));
            }
        }

        bfs(v, 1);

        // 루트 혹은 임의의 정점에서 모든 정점까지의 거리를 구했을 때, 가장 먼 거리를 가진 값을 start에 저장한다.
        int start = 1;
        for (int i = 2; i <= v; i++) {
            if (distance[i] > distance[start]) {
                start = i;
            }
        }

        // bfs를 한 번 더 진행한다.
        bfs(v, start);

        int max = distance[1];

        for (int i = 2; i <= v; i++) {
            if (max < distance[i]) {
                max = distance[i];
            }
        }

        bw.write(max + "");
        bw.flush();

    }

    private static void bfs(int v, int start) {
        visit = new boolean[v + 1];
        distance = new int[v + 1];

        LinkedList<Integer> q = new LinkedList<>();
        visit[start] = true; // 방문했음을 체크.
        q.add(start);

        while (!q.isEmpty()) {
            int x = q.poll();

            for (Node node : a[x]) {
                int to = node.to;
                int weight = node.weight;
                if (!visit[to]) { // 연결된 다음 정점을 방문하지 않았다면.
                    visit[to] = true;
                    q.add(to);
                    distance[to] = distance[x] + weight;
                }
            }
        }

    }
}

// 어떤 정점 A에 대해서 정점 A와 to라는 정점이 가중치가 weight인 간선으로 연결되어 있다.
// 여기서 다른 정점 to와 가중치 weight를 저장하는 Node 클래스이다.
class Node {
    int to;
    int weight;

    Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}