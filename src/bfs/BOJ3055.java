package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 11/06/2019
 * bfs : 탈출
 */
public class BOJ3055 {
    private static int r, c, resultX, resultY;
    private static int[][] map, distance;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        r = parse(in[0]);
        c = parse(in[1]);

        // 주변을 -2로 둘러쌓기 위해 +2만큼 확보한다.
        map = new int[r + 2][c + 2];
        distance = new int[r + 2][c + 2];

        // 주변을 -2로 둘러 싼다. 그로 인해 범위 바깥으로 나가지 못하도록 한다.
        // 이 때문에 밑에서는 범위 체크를 따로 할 필요가 없다.
        for (int i = 0; i < r + 2; i++) {
            Arrays.fill(map[i], -2);
            Arrays.fill(distance[i], -2);
        }

        for (int i = 1; i <= r; i++) {
            String str = br.readLine();
            for (int j = 1; j <= c; j++) {
                char ch = str.charAt(j - 1);
                switch (ch) {
                    case 'D': // 도착 지점인 경우.
                        map[i][j] = 2; // 2로 표시한다.
                        distance[i][j] = 0; // 방문할 수 있음을 의미한다.
                        // 나중에 결과가 D인 위치에 표시될 것이기 때문에 D의 위치를 저장해둔다.
                        resultX = i;
                        resultY = j;
                        break;
                    case 'S': // 시작 지점인 경우.
                        map[i][j] = 1; // 1로 표시한다.
                        distance[i][j] = 0; // 방문할 수 있음을 의미한다.
                        break;
                    case '.': // 비어있는 곳인 경우.
                        map[i][j] = 0; // 0로 표시한다.
                        distance[i][j] = 0; // 방문할 수 있음을 의미한다.
                        break;
                    case '*': // 물인 경우.
                        map[i][j] = -1; // -1로 표시한다.
                        distance[i][j] = -1; // 방문할 수 없음을 의미한다.
                        break;
                    case 'X': // 돌인 경우.
                        map[i][j] = -2; // -2로 표시한다.
                        distance[i][j] = -1; // 방문할 수 없음을 의미한다.
                        break;
                }
            }
        }

        bfs();
        boolean flag = true;

        // 2인 위치가 존재한다는 사실은 고슴도치가 도착 지점에 도달하지 못했음을 의미한다.
        // 고슴도치가 도착하면 1로 바꾸는데 바뀌지 않았기 때문!
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == 2) {
                    flag = false;
                }
            }
        }

        // 2의 존재 유무에 따라 flag 값을 설정하고 그에 따라 결과를 출력한다.
        // 정답 출력에서 -1을 하는 이유는 고슴도치인지 확인할 때 이미 1을 저장하고 시작했기 때문!
        if (flag) {
            System.out.println(distance[resultX][resultY] - 1);
        } else {
            System.out.println("KAKTUS");
        }
    }

    private static void bfs() {
        LinkedList<Point> q = new LinkedList<>();

        // 물인 경우, 큐에 먼저 넣도록 한다.
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == -1) {
                    q.add(new Point(i, j));
                    distance[i][j] = -1; // 방문할 수 없음을 의미한다.
                }
            }
        }

        // 고슴도치인 경우, 그 다음으로 큐에 넣도록 한다.
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == 1) {
                    q.add(new Point(i, j));
                    distance[i][j] = 1; // 방문할 수 없음을 의미한다.
                }
            }
        }

        while (!q.isEmpty()) {
            // 큐에서 뺀다.
            Point p = q.remove();
            int x = p.x;
            int y = p.y;

            // 큐에서 뺀 노드가 물인 경우.
            if (map[x][y] == -1) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 물인 노드에서 다음 방문할 노드가 비어있는 곳이면서 방문할 수 있는 곳이면.
                    if (map[nx][ny] == 0 && distance[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        map[nx][ny] = -1; // 물을 채운다.
                        distance[nx][ny] = -1; // 방문할 수 없는 곳으로 표시한다.
                    }
                }
            }

            // 큐에서 뺀 노드가 시작 지점인 경우. 즉, 고슴도치인 경우.
            if (map[x][y] == 1) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (map[nx][ny] == 0 || map[nx][ny] == 2 && distance[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        map[nx][ny] = 1; // 방문했음을 표시한다.
                        distance[nx][ny] = distance[x][y] + 1; // 방문하고 얼마나 이동했는지 시간을 저장한다.
                    }
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
