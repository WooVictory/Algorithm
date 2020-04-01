package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/01
 * 네트워크
 * dfs/bfs.
 */
public class PGM43162 {
    private static boolean[] visit;

    public static void main(String[] args) {
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
        System.out.println(solution(3, new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                answer++;
                //bfs(i, n, computers);
                dfs(i, n, computers);
            }
        }

        return answer;
    }

    // dfs.
    private static void dfs(int index, int n, int[][] computers) {
        if (visit[index]) return;
        visit[index] = true;

        for (int i = 0; i < n; i++) {
            if (!visit[i] && computers[index][i] == 1) dfs(i, n, computers);
        }
    }

    // bfs.
    private static void bfs(int index, int n, int[][] computers) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(index);
        visit[index] = true;

        while (!q.isEmpty()) {
            int current = q.remove();
            for (int i = 0; i < n; i++) {
                if (!visit[i] && computers[current][i] == 1) {
                    visit[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
