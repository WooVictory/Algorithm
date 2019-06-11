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
    private static int[][] arr;
    private static int[][] distance;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 1};
    private static int r, c, answerX, answerY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        r = parse(in[0]); // 행의 개수 -> 세로
        c = parse(in[1]); // 열의 개수 -> 가로

        // 범위를 벗어나지 못하도록 외부를 -2로 채워준다.
        // 따라서 +2 만큼 범위가 늘어난다.
        arr = new int[r + 2][c + 2];
        distance = new int[r + 2][c + 2];

        for (int i = 0; i < r + 2; i++) {
            Arrays.fill(arr[i], -2);
            Arrays.fill(distance[i], -2);
        }

        // int 배열로 매핑해야 한다.
        for (int i = 1; i <= r; i++) {
            String str = br.readLine();
            for (int j = 1; j <= c; j++) {
                char ch = str.charAt(j - 1);

                switch (ch) {
                    case 'D': // 도착 지점. 즉, 비버의 굴.
                        arr[i][j] = 2;
                        distance[i][j] = 0;
                        answerX = i;
                        answerY = j;
                        break;
                    case 'S': // 시작 지점. 즉, 고슴도치 위치.
                        arr[i][j] = 1;
                        distance[i][j] = 0;
                        break;
                    case '.': // 비어있는 곳. 즉, 이동할 수 있는 위치.
                        arr[i][j] = 0;
                        distance[i][j] = 0;
                        break;
                    case '*': // 물이 차 있는 곳.
                        arr[i][j] = -1;
                        distance[i][j] = -1;
                        break;
                    case 'X': // 돌이 있는 곳. 즉, 이동할 수 없음.
                        arr[i][j] = -2;
                        distance[i][j] = -1;
                        break;
                }
            }
        }

        bfs();
        int max = Integer.MIN_VALUE;
        boolean flag = true;

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (arr[i][j] == 2) {
                    flag = false;
                }
                max = Math.max(max, distance[i][j]);
            }
        }

        if (flag) {
            System.out.println(distance[answerX][answerY] - 1);

        } else {
            System.out.println("KAKTUS");
        }
    }

    private static void bfs() {
        LinkedList<Point> q = new LinkedList<>();

        // 물일 경우, 먼저 큐에 넣어준다.
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (arr[i][j] == -1) {
                    q.add(new Point(i, j));
                    distance[i][j] = -1;
                }
            }
        }

        // 그 다음으로, 고슴 도치일 경우 큐에 넣는다.
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (arr[i][j] == 1) {
                    q.add(new Point(i, j));
                    distance[i][j] = 1;
                }
            }
        }

        while (!q.isEmpty()) {
            Point point = q.remove();
            int x = point.x;
            int y = point.y;

            // 물일 경우.
            if (arr[x][y] == -1) {
                //arr[x][y] = -1;
                distance[x][y] = -1;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 다음 칸이 비어있는 곳이고, 방문한 적이 없다면 이동.
                    if (arr[nx][ny] == 0 && distance[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        arr[nx][ny] = -1; // 물을 채운다.
                        distance[nx][ny] = -1;
                    }
                }
            }

            // 시작 지점일 경우, 즉, 고슴도치일 경우.
            if (arr[x][y] == 1) {
                //arr[x][y] = 1;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 다음 칸이 비어있거나 도착 지점이면서 방문한 적이 없다면 이동.
                    if (arr[nx][ny] == 0 || arr[nx][ny] == 2 && distance[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        arr[nx][ny] = 1; // 고슴도치가 이동했음을 표시한다.
                        distance[nx][ny] = distance[x][y] + 1;
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
