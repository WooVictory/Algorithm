package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/17
 * 카카오프렌즈 컬러링북 - Level2
 * BFS.
 */
public class PGM1829_REVIEW {
    public static void main(String[] args) {
        int[][] picture = {
                {1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};
        solution(6, 4, picture);
    }

    private static int[][] map;
    private static boolean[][] visit;
    private static int M, N;

    public static int[] solution(int m, int n, int[][] picture) {
        map = picture;
        M = m;
        N = n;

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visit = new boolean[m][n];

        // 2중 for 반복문을 순회하며 탐색한다.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 방문한 적이 없고 0이 아니어야 한다.(0은 색칠하지 않는 영역이기 때문!)
                if (!visit[i][j] && map[i][j] != 0) {
                    numberOfArea++; // 몇개의 영역이 있는지 카운트.
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j));
                    // bfs 탐색을 통해 반환하는 영역의 크기와 max 값을 비교하여 최대값을 갱신한다.
                }
            }
        }

        // answer 배열에 담아서 반환한다.
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        System.out.println(answer[0] + ", " + answer[1]);
        return answer;
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    // bfs 탐색을 통해 이 영역과 같은 영역이 몇개 있는지 카운트하여 그 갯수를 반환한다.
    // 즉, 영역의 크기를 반환한다.
    private static int bfs(int x, int y) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visit[x][y] = true;
        int area = 1; // 처음에 자기 자신 1개부터 시작한다.

        // 큐에서 하나씩 꺼낸다.
        while (!q.isEmpty()) {
            Node cur = q.remove();
            x = cur.x;
            y = cur.y;

            // 상하좌우 방향을 탐색한다.
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나거나 이미 방분한 적이 있으면 건너뛴다.
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (visit[nx][ny]) continue;

                // 같은 영역이라면 큐에 넣고 방문 여부를 체크하고 영역의 크기를 늘린다.
                if (map[x][y] == map[nx][ny]) {
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                    area++;
                }
            }
        }
        // 영역의 크기를 반환한다.
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
