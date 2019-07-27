package myrealtrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 27/07/2019
 * 4x4 미로 탈출하는 경로.
 * 0 : 벽
 * 1 : 길
 * 사실 distance 는 여기서 필요하지 않음.
 */
public class MazeEscape {
    private static final int MAX = 4;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static Node[][] from;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[MAX][MAX];
        visited = new boolean[MAX][MAX];
        from = new Node[MAX][MAX];

        for (int i = 0; i < MAX; i++) {
            String s = br.readLine();
            for (int j = 0; j < MAX; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        // 출력 확인용.
        /*for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }*/

        // 출발점인 (1,1) 부터 미로 탐색 시작.
        bfs();


        /*
         * 도착 지점부터 거꾸로 역추적을 한다.
         * 역추적을 하다가 rowIndex, colIndex 가 0일 때,
         * 탈출하는데, 이는 시작 지점에 왔을 때이다.
         * 이때, 시작 지점도 1을 표시하고 반복문을 탈출한다.
         * */
        int[][] result = new int[MAX][MAX];
        int row = MAX - 1, col = MAX - 1;

        while (true) {
            int rowIndex = row;
            int colIndex = col;

            // 탈출과 동시에 시작 지점 표시.
            if (rowIndex == 0 && colIndex == 0) {
                result[rowIndex][colIndex] = 1;
                break;
            }

            result[rowIndex][colIndex] = 1;

            row = from[rowIndex][colIndex].x;
            col = from[rowIndex][colIndex].y;
        }

        System.out.println("result");
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;
        from[0][0] = new Node(0, 0);

        while (!q.isEmpty()) {
            Node current = q.remove();
            int nowX = current.x;
            int nowY = current.y;

            // 도착 지점에 도착하면 종료.
            if (nowX == MAX - 1 && nowY == MAX - 1) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (0 <= nx && nx < MAX && 0 <= ny && ny < MAX) {
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        q.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                        if (from[nx][ny] == null) {
                            from[nx][ny] = new Node(nowX, nowY);
                        }
                    }
                }
            }
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}