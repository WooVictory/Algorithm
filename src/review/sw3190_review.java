package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 21/07/2019
 * SW 역량 기출 뱀 Review.
 */
public class sw3190_review {
    private static final String SPACE = " ";
    private static int n, k, l;
    private static int[][] map;
    private static int[] time;
    private static int[] dir;
    private static ArrayDeque<Node> snake;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    // 북동남서.


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        snake = new ArrayDeque<>();
        n = parse(br.readLine()); // 보드의 크기.
        k = parse(br.readLine()); // 사과의 개수.

        map = new int[n + 1][n + 1];

        while (k-- > 0) {
            String[] in = br.readLine().split(SPACE);
            int x = parse(in[0]);
            int y = parse(in[1]);

            map[x][y] = 1; // 사과의 위치를 1로 표시한다.
        }

        l = parse(br.readLine());
        time = new int[l];
        dir = new int[l];

        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(SPACE);
            time[i] = parse(in[0]);
            dir[i] = getDirection(in[1]);
        }

        bfs();
    }

    private static void bfs() {
        int second = 0;
        int snakeDir = 1; // 처음 시작 방향은 오른쪽.
        int timeIndex = 0;
        map[1][1] = 2; // 뱀의 시작 위치는 (1,1)
        snake.add(new Node(1, 1));

        while (true) {

            // 시간이 지나면 방향을 바꿔준다.
            if (timeIndex < l && time[timeIndex] == second) {
                snakeDir = (snakeDir + dir[timeIndex]) % 4;
                timeIndex++;
            }

            // 뱀이 다음에 갈 위치를 구한다.
            int nx = snake.getFirst().x + dx[snakeDir];
            int ny = snake.getFirst().y + dy[snakeDir];

            // 벽에 부딪히면 종료.
            if (nx <= 0 || nx > n || ny <= 0 || ny > n) {
                System.out.println(second + 1);
                return;
            }

            // 뱀이 자기 자신 몸에 부딪히면 종료.
            if (map[nx][ny] == 2) {
                System.out.println(second + 1);
                return;
            }

            // 뱀이 이동할 다음 칸에 사과가 있다면, 꼬리는 냅두고 몸의 길이를 늘린다.
            if (map[nx][ny] == 1) {
                map[nx][ny] = 2;
                snake.addFirst(new Node(nx, ny));
            } else if (map[nx][ny] == 0) {
                map[nx][ny] = 2;
                snake.addFirst(new Node(nx, ny));

                Node tail = snake.removeLast();
                map[tail.x][tail.y] = 0;
            }

            second++;
        }
    }

    // 우측과 좌측의 방향을 숫자로 바꿔 반환한다.
    private static int getDirection(String d) {
        if (d.equals("D")) {
            return 1;
        } else {
            return 3;
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
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
