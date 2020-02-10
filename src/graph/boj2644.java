package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/10
 */
public class boj2644 {
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());
        String[] in = br.readLine().split(" ");
        int x = toInt(in[0]), y = toInt(in[1]);

        a = (ArrayList<Integer>[]) new ArrayList[n + 1];
        visit = new boolean[n + 1];
        distance = new int[n + 1];

        for (int i = 1; i <= n; i++) a[i] = new ArrayList<>();

        int m = toInt(br.readLine());

        while (m-- > 0) {
            String[] input = br.readLine().split(" ");
            int v1 = toInt(input[0]), v2 = toInt(input[1]);

            a[v1].add(v2);
            a[v2].add(v1);
        }

        bfs(x);
        System.out.println(distance[y] == 0 ? -1 : distance[y]);
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int x = q.remove();

            for (int v : a[x]) {
                if (!visit[v]) {
                    visit[v] = true;
                    distance[v] = distance[x] + 1;
                    q.add(v);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}