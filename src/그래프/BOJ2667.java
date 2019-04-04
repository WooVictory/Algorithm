package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * created by victory_woo on 04/04/2019
 * 그래프 : 단지 번호 붙이기
 */
public class BOJ2667 {
    private static int[][] a;
    private static int[][] group;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        a = new int[n][n];
        group = new int[n][n];

        // 2차원 배열에 지도를 입력받음.
        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            for (int j = 0; j < n; j++) {
                a[i][j] = input.charAt(j) - 48;
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 집이 존재하고, 방문한 적이 없다면 탐색.
                if (a[i][j] == 1 && group[i][j] == 0) {
                    bfs(i, j, ++count, n);
                }
            }
        }

        //System.out.println("count : "+count);
        int[] answer = new int[count+1];

        // 단지 번호의 개수를 담는 정답 배열.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (group[i][j] != 0) { // 방문을 했다면.
                    answer[group[i][j]] += 1;
                }
            }
        }

        Arrays.sort(answer);

        System.out.println(count);
        for (int i = 1; i <=count; i++) {
            System.out.println(answer[i]);
        }
    }

    private static void bfs(int x, int y, int count, int n) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        group[x][y] = count; // 단지의 개수인 count.

        while (!q.isEmpty()) {
            Pair p = q.poll(); // 큐에서 꺼낸다.
            x = p.x;
            y = p.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k]; // 다음 정점의 행 : nx
                int ny = y + dy[k]; // 다음 정점의 열 : ny

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (a[nx][ny] == 1 && group[nx][ny] == 0) {
                        q.add(new Pair(nx, ny));
                        group[nx][ny] = count;
                    }
                }
            }
        }
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
