package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/17
 * 카카오프렌즈 컬러링북.
 */
public class PGM1829 {
    public static void main(String[] args) {

        int[][] picture = {
                {1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};
        System.out.println(solution(6, 4, picture));
    }

    private static int M, N;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        map = picture;
        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && map[i][j] != 0) {
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j));
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        System.out.println(answer[0] + ", " + answer[1]);
        return answer;
    }

    private static int bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visit[x][y] = true;
        int area = 1; // 자기 자신.

        while (!q.isEmpty()) {
            Node cur = q.remove();
            x = cur.x;
            y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (visit[nx][ny]) continue;

                if (map[x][y] == map[nx][ny]) {
                    area++;
                    visit[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
        return area;

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
