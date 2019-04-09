package 그래프;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 08/04/2019
 * 그래프 : 토마토
 * BFS
 */
public class BOJ7576 {
    private static int[][] arr; // 입력 배열이자, 토마토가 익는데 얼마나 걸리는지 일수를 저장하는 배열.
    private static boolean[][] visit; // 방문 체크 배열.
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int m = Integer.parseInt(input[0]); // 세로 칸의 수
        int n = Integer.parseInt(input[1]); // 가로 칸의 수

        arr = new int[n][m];
        visit = new boolean[n][m];

        // 2차원 배열 입력.
        for (int i = 0; i < n; i++) {
            String[] numbers = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int number = Integer.parseInt(numbers[j]);
                arr[i][j] = number;
            }
        }

        bw.write(bfs(n, m) + "");
        bw.flush();
    }

    private static int bfs(int n, int m) {
        LinkedList<TomatoBox> q = new LinkedList<>();

        // 토마토가 익었는지(==1) 확인하여 큐에 먼저 넣는다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) { // 익은 토마토는 큐에 넣는다.
                    q.add(new TomatoBox(i, j));
                }
            }
        }

        // 모든 토마토에 대해서 BFS 탐색
        // 익는데 얼마나 걸리는지 arr 배열에 저장한다.
        // 배열 arr에 익는데 걸리는 일수가 저장된다.
        // BFS는 단계별로 진행된다.
        while (!q.isEmpty()) {
            TomatoBox tomatoBox = q.poll();

            for (int k = 0; k < 4; k++) {
                int nextX = tomatoBox.x + dx[k];
                int nextY = tomatoBox.y + dy[k];

                if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m) {
                    // 익지 않았고, 방문한 적이 없으면 다음에는 익는다는 의미.
                    if (arr[nextX][nextY] == 0 && !visit[nextX][nextY]) {
                        q.add(new TomatoBox(nextX, nextY));
                        visit[nextX][nextY] = true;
                        arr[nextX][nextY] = arr[tomatoBox.x][tomatoBox.y] + 1;
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) { // 익지 않은 토마토가 있는 것은 모든 토마토가 익지 못한 경우이므로 종료한다.
                    return -1;
                }
                max = Math.max(max, arr[i][j]);
            }
        }

        // 1일 ~ 종료일자 까지이므로 기간만을 반환하기 때문에 -1을 해준다.
        return max - 1;
    }
}

class TomatoBox {
    int x;
    int y;

    TomatoBox(int x, int y) {
        this.x = x;
        this.y = y;
    }
}