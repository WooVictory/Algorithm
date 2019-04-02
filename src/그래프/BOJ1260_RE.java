package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * created by victory_woo on 02/04/2019
 * DFS와 BFS 복습
 */
public class BOJ1260_RE {
    private static final String SPACE = " ";
    private static ArrayList<Integer>[] a;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(SPACE);
        int n = convert(input[0]); // 정점의 개수
        int m = convert(input[1]); // 간선의 개수
        int start = convert(input[2]); // 시작할 정점 번호

        // 배열 초기화.
        a = new ArrayList[n + 1];


        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int j = 0; j < m; j++) {
            String[] inputs = br.readLine().split(SPACE);
            int u = convert(inputs[0]);
            int v = convert(inputs[1]);

            // 양방향 그래프일 경우 양쪽 다 추가해준다.
            a[u].add(v);
            a[v].add(u);
        }

        // 방문할 정점이 여러 개인 경우 정점 번호가 가장 작은 것부터 탐색하기 위해서 정렬한다.
        for (int i = 1; i <= n; i++) {
            Collections.sort(a[i]);
        }

        visit = new boolean[n + 1];
        dfs(start);
        System.out.println();

        visit = new boolean[n + 1];
        bfs(start);
        System.out.println();


    }

    private static int convert(String command) {
        return Integer.parseInt(command);
    }

    private static void dfs(int x) {
        // 방문한 적이 있다면 종료한다.
        if (visit[x]) {
            return;
        }

        visit[x] = true;
        System.out.print(x + SPACE);

        for (int y : a[x]) {
            //System.out.println("a["+x+"]의 값들 : "+y);
            if (!visit[y]) {
                dfs(y);
            }
        }

    }

    private static void bfs(int start) {
        LinkedList<Integer> queue = new LinkedList<>();

        visit[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int x = queue.remove(); // 큐에서 정점을 뺀다.

            System.out.print(x + SPACE);

            for (int y : a[x]) {
                // 방문한 적이 있는지 체크한다.
                if (!visit[y]) {
                    // 해당 정점을 방문한 적이 없다면 방문했다고 true 로 체크한다.
                    // 그리고 해당 정점을 큐에 넣는다.
                    visit[y] = true;
                    queue.add(y);
                }
            }
        }
    }
}
