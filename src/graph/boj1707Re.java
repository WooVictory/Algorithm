package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/03
 * 이분 그래프.
 * 다시 풀어보기!
 */
public class boj1707Re {
    private static ArrayList<Integer>[] a;
    private static int[] discovered;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = toInt(br.readLine());

        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            int v = toInt(in[0]), e = toInt(in[1]); // v : 정점의 개수, e : 간선의 개수.

            // 정점의 개수만큼 초기화.
            a = (ArrayList<Integer>[]) new ArrayList[v + 1];
            discovered = new int[v + 1];

            for (int i = 1; i <= v; i++) a[i] = new ArrayList<>();

            for (int i = 1; i <= e; i++) {
                String[] input = br.readLine().split(" ");
                int x = toInt(input[0]), y = toInt(input[1]);

                // 양방향 그래프이므로, 모두 넣어줌.
                a[x].add(y);
                a[y].add(x);
            }

            // 방문한 적이 없는 정점에 대해 bfs.
            for (int i = 1; i <= v; i++)
                if (discovered[i] == 0) bfs(i);

            boolean isOkay = true;

            for (int i = 1; i <= v; i++) {
                for (int x : a[i]) {
                    if (discovered[i] == discovered[x]) isOkay = false;
                    // 해당 정점과 연결된 정점 중, 상태 값이 같은 정점이 하나라도 있다면 이분 그래프가 될 수 없다.
                }
            }

            System.out.println(isOkay ? "YES" : "NO");
        }
    }

    private static void bfs(int start) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        discovered[start] = 1;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int x : a[now]) {
                if (discovered[x] == 0) {
                    discovered[x] = -discovered[now]; // 이전 정점과 다른 상태 값을 저장한다.
                    q.add(x);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
