package 그래프;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 09/04/2019
 * 그래프 : 다리 만들기.
 */
public class BOJ2146 {
    private static int[][] arr;
    private static boolean[][] visit;
    private static int[][] distance;
    private static ArrayList<Integer> list = new ArrayList<>();
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visit = new boolean[n][n];
        distance = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 연결된 섬에 번호를 지정하는 과정이다.
                if (arr[i][j] == 1 && !visit[i][j]) {
                    numberSave(i, j, ++count, n);
                }
            }
        }

        // 번호가 지정된 섬들을 큐에 넣는다.
        LinkedList<Bridge> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) {
                    q.add(new Bridge(i, j));
                }
            }
        }

        // bfs 진행.
        bfs(q, n);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (result > list.get(i)) {
                result = list.get(i);
            }
        }

        bw.write(result+"");
        bw.flush();
    }

    // 연결된 각 섬에 번호를 붙이는 단계.
    // 단지 번호 붙이기와 같은 과정이다.
    private static void numberSave(int x, int y, int count, int n) {
        LinkedList<Bridge> q = new LinkedList<>();
        q.add(new Bridge(x, y));
        arr[x][y] = count;
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Bridge bridge = q.poll();
            x = bridge.x;
            y = bridge.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (arr[nx][ny] == 1 && !visit[nx][ny]) {
                        q.add(new Bridge(nx, ny));
                        visit[nx][ny] = true;
                        arr[nx][ny] = count;
                    }
                }
            }
        }

    }

    // 번호가 지정된 섬들만 큐에 저장되고 이 큐를 기반으로 bfs 탐색을 진행한다.
    private static void bfs(LinkedList<Bridge> q, int n) {

        while (!q.isEmpty()) {
            Bridge b = q.poll();
            int x = b.x;
            int y = b.y;

            // 4방향을 검사해야 한다.
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    // next 정점이 0이 아니고 이전 정점과 다르다면
                    // 서로 다른 섬을 만났으므로 list에 두 섬의 경로를 저장한다.
                    if (arr[nx][ny] != 0 && arr[nx][ny] != arr[x][y]) {
                        list.add(distance[nx][ny] + distance[x][y]);
                    }

                    // 영역을 확장하기 위해 번호가 없는 섬들을 큐에 넣는 작업을 진행한다.
                    if (arr[nx][ny] == 0) {
                        q.add(new Bridge(nx, ny));
                        arr[nx][ny] = arr[x][y];
                        distance[nx][ny] = distance[x][y] + 1;
                    }
                }
            }
        }

    }
}


class Bridge {
    int x;
    int y;

    Bridge(int x, int y) {
        this.x = x;
        this.y = y;
    }
}