package bfs;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 11/06/2019
 * bfs : 벽 부수고 이동하기.
 * 현재 노드를 기준으로 시작한다.
 * 벽을 부수었는지 부수지 않았는지 판단.
 */
public class BOJ2206_review {
    private static int[][] map;
    private static boolean[][][] visited;
    private static int n, m, result = Integer.MAX_VALUE;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] in = br.readLine().split(" ");
        n = parse(in[0]);
        m = parse(in[1]);

        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();

        if (result == Integer.MAX_VALUE) {
            bw.write(-1 + "");
        } else {
            bw.write(result + "");
        }
        bw.flush();
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));

        while (!q.isEmpty()) {
            Node node = q.remove();
            int nowX = node.x;
            int nowY = node.y;
            int count = node.count;
            int destroy = node.destroy;

            // 마지막 지점인 (n,m)에 도달했을 경우.
            // 최단 경로를 찾아야 한다.
            if (nowX == n - 1 && nowY == m - 1) {
                result = Math.min(result, count);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                // 범위를 확인한다.
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    // 현재의 노드를 기준으로 판단한다.
                    // 벽을 부순 경우.
                    if (destroy == 1) {
                        //System.out.println("nx: "+nx+", ny: "+ny);
                        if(visited[nx][ny][destroy]){
                            System.out.println("nx: "+nx+", ny: "+ny);
                        }
                        // 방문한 적이 없고 다음 이동할 곳이 빈칸인 경우.
                        if (!visited[nx][ny][destroy] && map[nx][ny] == 0) {
                            q.add(new Node(nx, ny, count + 1, destroy));
                            visited[nx][ny][destroy] = true;
                        }
                    } else { // 벽을 부수지 않은 상태.
                        // 벽을 만난 경우.
                        // 시작 노드에서 벽을 부수지 않은 상태에서 벽을 만난 경우이기 때문에
                        // 만난 위치에서 벽을 부순 곳을 방문한 적이 있는지 확인한다. 부순 적이 없기 때문에
                        // 큐에 넣고 방문했음을 체크한다. 그리고 벽을 부수어야 하므로 destroy+1을 한다.
                        if (map[nx][ny] == 1) {
                            if (!visited[nx][ny][destroy + 1]) {
                                q.add(new Node(nx, ny, count + 1, destroy + 1));
                                visited[nx][ny][destroy + 1] = true;
                            }

                            // 벽이 아닌 빈칸을 만난 경우.
                            // 그대로 방문처리 하고 탐색을 진행한다.
                            // 벽을 부수지 않기 때문에 그냥 destroy 를 넣으면 된다.
                        } else if (map[nx][ny] == 0) {
                            if (!visited[nx][ny][destroy]) {
                                q.add(new Node(nx, ny, count + 1, destroy));
                                visited[nx][ny][destroy] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Node {
        int x;
        int y;
        int count;
        int destroy;

        Node(int x, int y, int count, int destroy) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.destroy = destroy;
        }
    }
}
