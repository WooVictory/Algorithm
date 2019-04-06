package 그래프;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 05/04/2019
 * 그래프 : 단지 번호 붙이기.
 */
public class BOJ2667_REVIEW {
    // 네 방향에 대해서 상,하,좌,우를 체크하기 위해서 미리 선언해둔 배열.
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] a; // 2차원 배열로 행,열을 저장하는 배열.
    private static int[][] group; // 단지 번호를 저장하는 배열.
    private static final int NUM = 48;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        init(n);

        // 2차원 배열 입력받음.
        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            for (int j = 0; j < n; j++) {
                a[i][j] = input.charAt(j) - NUM;
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 집이 있고 방문한 적이 없다면 bfs 탐색.
                if (a[i][j] == 1 && group[i][j] == 0) {
                    bfs(i, j, ++count, n);
                }
            }
        }
        // 단지 번호마다 몇개인지를 담는 배열.
        int[] answer = new int[count + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (group[i][j] != 0) { // 방문한 적이 있다면.
                    answer[group[i][j]] += 1;
                }
            }
        }

        // 오름차순으로 정렬.
        Arrays.sort(answer);
        System.out.println(count);
        for (int i = 1; i < answer.length; i++) {
            sb.append(answer[i]).append("\n");
        }

        bw.write(sb.toString() + "");
        bw.flush();
    }

    // 초기화 함수.
    private static void init(int n) {
        a = new int[n][n];
        group = new int[n][n];
    }

    private static void bfs(int x, int y, int count, int n) {
        LinkedList<Point> q = new LinkedList<>();
        q.add(new Point(x, y)); // 큐에 집(정점)을 넣는다.
        group[x][y] = count;

        while (!q.isEmpty()) {
            Point p = q.poll();
            x = p.x;
            y = p.y;

            // 상,하,좌,우 네 방향에 대해서 검사한다.
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (a[nx][ny] == 1 && group[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        group[nx][ny] = count;
                    }
                }
            }
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
