package 트리;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * created by victory_woo on 10/04/2019
 * 트리 : 트리의 지름
 * dfs 풀기.
 */
public class BOJ1967_DFS {
    private static ArrayList<Tree>[] a;
    private static boolean[] visit;
    private static int[] distance;
    private static int maxDistance = 0; // 긴 거리를 저장하는 변수.
    private static int maxVertex = 0; // 긴 거리일 때의 정점을 저장하는 변수.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            a[x].add(new Tree(y, z));
            a[y].add(new Tree(x, z));
        }

        visit = new boolean[n + 1];
        distance = new int[n + 1];
        // 루트부터 dfs 탐색 시작.
        dfs(1, 0);

        maxDistance = 0;
        visit = new boolean[n + 1];
        distance = new int[n + 1];

        // 가장 긴 거리를 가지는 정점부터 다른 정점까지의 거리 중 가장 먼 거리를 다시 탐색한다.
        dfs(maxVertex, 0);

        // 그랬을 때의 maxDistance가 가장 긴 거리가 된다.
        System.out.println(maxDistance);

    }

    private static void dfs(int v, int d) {
        visit[v] = true;
        distance[v] = d;

        // 가장 긴 거리와 그 때의 정점을 저장한다.
        if (distance[v] > maxDistance) {
            maxDistance = distance[v];
            maxVertex = v;
        }

        for (Tree tree : a[v]) {
            int to = tree.to;
            int weight = tree.weight;

            // 방문한 적이 없으면 dfs 방문!
            if (!visit[to]) {
                dfs(to, d + weight);
            }
        }

    }
}

class Tree {
    int to;
    int weight;

    Tree(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
