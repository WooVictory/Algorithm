package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 11/06/2019
 * 이 방법은 살짝 이해가 잘 안간다.
 * 다음 방문 노드를 기준으로 벽인지 빈 방인지 판단한다.
 * <p>
 * 1. 벽을 만난 경우
 * 벽을 부수고 탐색하거나 이전에 벽을 부수었다면 더 이상 부술 벽이 없기 때문에 해당 지점에서 탐색을 종료하는 방법 두가지가 있다.
 * 여기서는 부순 적이 없고 벽을 부수어서 다음 좌표로 이동한 적이 없다면 벽을 부수고 큐에 넣는다. 그리고 방문 체크를 한다.
 * <p>
 * 2. 빈 방을 만난 경우
 * 방문 체크하고 그냥 그대로 탐색을 진행하면 된다.
 * 그렇기 때문에 다음 좌표 (nx,ny)에 대해서 방문한 적이 있는지 체크하면 된다.
 * destroy 를 그대로 넘기는 이유는 어차피 이전에 벽을 부수었으면 1일 넘어오고 방을 만났으니까 destroy 값은 변하지 않기 때문이다.
 * 벽을 부수지 않았더라도 (nx,ny)가 방이기 때문에 부술 벽이 없다. 그래서 destroy 값은 변하지 않는다.
 * 그대로 넘긴다.
 */
public class BOJ2206_other {
    private static int n, m, step = 0;
    private static int[][] map;
    private static boolean[][][] visited;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 1};
    private static boolean isSuccess = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = parse(input[0]);
        m = parse(input[1]);

        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty() && !isSuccess) {
            step++;

            // 근데 for 문으로 감싸는 이유는 잘 모르겠다.
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.remove();
                int x = node.x;
                int y = node.y;
                int destroy = node.destroy;

                if (x == n - 1 && y == m - 1) {
                    isSuccess = true;
                    break;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (0 > nx || nx >= n || 0 > ny || ny >= m) {
                        continue;
                    }

                    // 다음 좌표가 벽이라면?
                    if (map[nx][ny] == 1) {
                        // 부순 적이 없고 벽을 부수어서 다음 좌표인 (nx,ny)로 간적이 없는지 확인한다.
                        if (destroy < 1 && !visited[nx][ny][destroy + 1]) {
                            q.add(new Node(nx, ny, destroy + 1));
                            visited[nx][ny][destroy + 1] = true;
                        }
                    } else if (map[nx][ny] == 0) { // 빈 방이라면?
                        // 방문한 적 있는지 없는지만 확인한다.
                        // 왜냐하면 방을 만났으면 방문을 체크하고 그대로 탐색을 하면 되기 때문이다.
                        // 이유는 방을 만났으면 이전 좌표의 destroy 변수가 0인지 1인지 관심이 없다.
                        // 0이면 그대로 탐색을 하면 되는거고 1이면 이 다음 턴에서 확인이 되기 때문에 여기서는 상관이 없다.
                        if (!visited[nx][ny][destroy]) {
                            q.add(new Node(nx, ny, destroy));
                            visited[nx][ny][destroy] = true;
                        }
                    }
                }
            }

        }
        System.out.println(isSuccess ? step : -1);
    }

    private static void bfs() {

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Node {
        int x;
        int y;
        int destroy;

        Node(int x, int y, int destroy) {
            this.x = x;
            this.y = y;
            this.destroy = destroy;
        }

    }

}
