package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/14
 * 연구소.
 * 삼성 기출.
 * dfs + bfs
 * 시간도 오래 걸리고 메모리 측면에서도 좀 더 많은 메모리를 사용한다.
 */
public class sw14502_other {
    private static int n, m, max;
    private static int[][] map, copyMap;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static LinkedList<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        // 2중 for 문이 아닌 1중 for 문을 사용함으로써 지도 위에서 열을 움직일 수 있다.
        // 즉, 다음 열에 대한 탐색을 하고 자연스럽게 열의 끝에 도달하면 다음 행을 탐색할 수 있다.
        for (int i = 0; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            // 빈칸일 경우, 벽을 놓는다. dfs()를 통해 벽을 3개까지 놓아본다.
            if (map[x][y] == 0) {
                map[x][y] = 1;
                dfs(i, 1);
            }
        }

        System.out.println(max);
    }

    // 1. 벽을 3개까지 놓는다.
    private static void dfs(int v, int count) {
        int x = v / m;
        int y = v % m;

        // 벽을 3개 놓았다면, 이제 바이러스를 퍼트릴 차례이다.
        if (count == 3) {
            // 3개의 벽을 놓은 map -> copyMap 으로 복사한다.
            // 이유는 원본 배열을 유지하기 위함이다.계속 해서 바뀌기 때문에 이를 유지하는 것은 어렵다.
            // 그래서 copyMap 배열을 벽을 3개 놓을 때마 바이러스를 퍼트려서 안전 영역의 개수를 구한다.
            // 또한, 백트래킹을 통해서 원본 배열을 초기화하기 때문에 copyMap 배열이 필요하다.
            copyMap = new int[n][m];
            copy();

            // 바이러스인 곳을 찾아 큐에 넣는다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (copyMap[i][j] == 2) q.add(new Node(i, j));
                }
            }
            // 바이러스를 퍼트린다. 퍼트리는 것이기 때문에 dfs 보다는 bfs 를 사용한다.
            spreadVirusBfs();

            getResult();
        } else {
            // 벽을 3개 다 놓지 못했다면 다음에 놓을 장소에 벽을 놓기 위해 시작한다.
            // 1중 for 문으로 인해 하나의 행에서 그 다음 열로 이동하면서 탐색을 할 수 있다.
            // 그리고 열의 끝에 도달하면 자연스럽게 다음 행으로 이동이 가능하다.
            for (int i = v + 1; i < n * m; i++) {
                int nx = i / m;
                int ny = i % m;

                // 빈칸일 경우, 원본 배열에 벽을 놓는다.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    dfs(i, count + 1);
                }
            }
        }

        // 원본 배열을 원래 상태로 초기화하기 위해 백트래킹을 한다.
        map[x][y] = 0;
        --count;
    }

    private static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) copyMap[i][j] = map[i][j];
        }
    }

    // 2. 바이러스를 퍼트린다. - bfs()
    // 이미 바이러스의 위치를 큐에 넣어놨기 때문에 큐에서 꺼내면서 bfs 를 통해 바이러스를 퍼트린다.
    private static void spreadVirusBfs() {
        while (!q.isEmpty()) {
            Node node = q.remove();
            int x = node.x;
            int y = node.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 3;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    // 3. 안전 영역의 개수를 구한다.
    private static void getResult() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) count++;
            }
        }

        max = Math.max(max, count);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
