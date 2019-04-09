package 트리;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 09/04/2019
 * 트리 : 트리의 부모 찾기.
 */
public class BOJ11725 {
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = parse(br.readLine());

        a = new ArrayList[n + 1]; // 인접 리스트.
        visit = new boolean[n + 1]; // 방문 확인을 위한 배열.

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }


        for (int i = 0; i < n - 1; i++) {
            String[] input = br.readLine().split(" ");
            int u = parse(input[0]);
            int v = parse(input[1]);

            a[u].add(v);
            a[v].add(u);
        }

        LinkedList<Integer> q = new LinkedList<>();
        int[] from = new int[n + 1];
        from[1] = 0;
        q.add(1);
        visit[1] = true;

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int y : a[x]) {
                if (!visit[y]) {
                    q.add(y);
                    visit[y] = true;
                    from[y] = x; // y가 어디로부터 왔는지 즉, y의 부모인 x를 저장한다.
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            sb.append(from[i]).append(NEW_LINE);
        }

        System.out.println(sb.toString());

    }

    private static int parse(String data) {
        return Integer.parseInt(data);
    }
}
