package 트리;

import java.util.*;

/**
 * created by victory_woo on 10/04/2019
 * 트리 : 트리의 지름.
 * 다른 문제.
 * 무방향 그래프 -> 양방향.
 * bfs -> 메모리 초과...?
 * import로 인한 메모리 초과...ㅜ
 */
public class BOJ1967 {
    private static ArrayList<Pair>[] a;
    private static boolean[] visit = new boolean[10001];
    private static int[] distance = new int[10001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = (ArrayList<Pair>[]) new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            a[x].add(new Pair(y, z));
            a[y].add(new Pair(x, z));
        }

        bfs(1);

        // 가장 긴 지름을 갖는 정점을 찾는다.
        int start = 1;
        for (int i = 2; i <= n; i++) {
            if (distance[start] < distance[i]) {
                start = i;
            }
        }

        // 가장 긴 지름을 갖는 정점으로 다시 bfs를 해서 가장 먼 거리를 찾는다.
        bfs(start);
        int answer = distance[1];
        for (int i = 2; i <= n; i++) {
            answer = Math.max(answer, distance[i]);
        }

        System.out.println(answer);
    }

    private static void bfs(int start) {
        Arrays.fill(visit, false);
        Arrays.fill(distance, 0);
        LinkedList<Integer> q = new LinkedList<>();
        visit[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int x = q.poll();

            for (Pair pair : a[x]) {
                int to = pair.to;
                int weight = pair.weight;

                if (!visit[to]) {
                    visit[to] = true;
                    q.add(to);
                    distance[to] = distance[x] + weight;
                }
            }
        }
    }
}

class Pair {
    int to;
    int weight;

    Pair(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}