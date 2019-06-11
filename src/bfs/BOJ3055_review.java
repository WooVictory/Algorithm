package bfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 11/06/2019
 * bfs : 탈출 다시 푸는 것!
 */
public class BOJ3055_review {
    private static int[][] map, distance;
    private static int r, c, resultX, resultY;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        r = parse(input[0]); // 행
        c = parse(input[1]); // 열

        map = new int[r + 2][c + 2];
        distance = new int[r + 2][c + 2];

        // 모두 -2로 채우고 시작한다.
        for (int i = 0; i < r + 2; i++) {
            Arrays.fill(map[i], -2);
            Arrays.fill(distance[i], -2);
        }

        // 해당 문제의 조건이 문자이기 때문에 이를 적절한 숫자로 매핑한다.
        for (int i = 1; i <= r; i++) {
            String str = br.readLine();
            for (int j = 1; j <= c; j++) {
                char ch = str.charAt(j - 1);
                switch (ch) {
                    case 'D': // 비버의 굴.
                        map[i][j] = 2;
                        distance[i][j] = 0;
                        resultX = i;
                        resultY = j;
                        break;
                    case 'S': // 고슴도치 시작지점.
                        map[i][j] = 1;
                        distance[i][j] = 0;
                        break;
                    case '.': // 갈 수 있는 곳.
                        map[i][j] = 0;
                        distance[i][j] = 0;
                        break;
                    case 'X': // 돌의 위치.
                        map[i][j] = -2;
                        distance[i][j] = -1;
                        break;
                    case '*': // 물.
                        map[i][j] = -1;
                        distance[i][j] = -1;
                        break;
                }
            }
        }

        bfs();
        int max = Integer.MIN_VALUE;
        boolean flag = true;

        // 도착지점인 2가 존재한다면 고슴도치가 도착하지 못했음을 의미한다.
        // 따라서 2일 경우 flag 를 false 로 설정함으로써 답을 출력할 때 사용한다.
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == 2) flag = false;
                max = Math.max(max, map[i][j]);
            }
        }

        // 이미 1을 체크하고 시작했으므로 1을 빼야 한다.
        if (flag)
            bw.write(distance[resultX][resultY] - 1 + "");
        else
            bw.write("KAKTUS");

        bw.flush();

    }

    private static void bfs() {
        LinkedList<Pair> q = new LinkedList<>();

        // 물인 경우, 큐에 먼저 넣는다.
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == -1) {
                    q.add(new Pair(i, j));
                    distance[i][j] = -1;
                }
            }
        }

        // 고슴도치인 경우, 다음으로 큐에 넣는다.
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == 1) {
                    q.add(new Pair(i, j));
                    distance[i][j] = 1;
                }
            }
        }

        while (!q.isEmpty()) {
            Pair pair = q.remove();
            int x = pair.x;
            int y = pair.y;

            // 물인 경우, 먼저 큐에서 빼고 물을 채운다.
            if (map[x][y] == -1) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 비어있는 곳이고 방문한 적이 없다면
                    if (map[nx][ny] == 0 && distance[nx][ny] == 0) {
                        q.add(new Pair(nx, ny)); // 큐에 넣는다.
                        map[nx][ny] = -1; // 물을 채운다.
                        distance[nx][ny] = -1;
                    }
                }
            }

            // 고슴도치인 경우, 위에서 먼저 물인 곳을 채웠으므로 고슴도치가 이동할 수 있는 경로를 찾아 이동한다.
            if (map[x][y] == 1) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 비어있는 곳이거나 도착지점이면서 방문한 적이 없다면
                    if (map[nx][ny] == 0 || map[nx][ny] == 2 && distance[nx][ny] == 0) {
                        q.add(new Pair(nx, ny)); // 큐에 넣는다.
                        map[nx][ny] = 1; // 이동했음을 표시한다.
                        distance[nx][ny] = distance[x][y] + 1;
                    }
                }
            }
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
