package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 20/07/2019
 * SW 역량 기출 뱀 문제.
 */
public class sw3190_1 {
    private static final String SPACE = " ";
    private static int n, k, l;
    private static int[][] map;
    private static int[] time;
    private static int[] direction;
    private static int[] dx = {-1, 0, 1, 0}; // 북동남서 순서.
    private static int[] dy = {0, 1, 0, -1};
    private static ArrayDeque<Node> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        snake = new ArrayDeque<>();
        n = parse(br.readLine());
        k = parse(br.readLine());

        map = new int[n + 1][n + 1];
        while (k-- > 0) {
            String[] in = br.readLine().split(SPACE);
            int x = parse(in[0]);
            int y = parse(in[1]);

            map[x][y] = 1; // 사과 있는 곳은 1로 표시한다.
        }

        l = parse(br.readLine());
        time = new int[l];
        direction = new int[l];

        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(SPACE);
            time[i] = parse(in[0]);
            direction[i] = getDirection(in[1]);
        }

        solution();
    }

    private static void solution() {
        int second = 0;
        int snakeDirection = 1; // 처음 방향은 오른쪽.
        int timeIndex = 0;
        map[1][1] = 2;
        snake.add(new Node(1, 1));

        while (true) {
            // 시간이 지나면 방향 이동하기.
            // % 4를 이용해서 다음 방향을 구한다.
            // 북동남서를 0,1,2,3으로 생각한다. (1,1)이 좌측 맨 위에 있다고 생각을 하면 된다.
            if (timeIndex < l && time[timeIndex] == second) {
                snakeDirection = (snakeDirection + direction[timeIndex]) % 4; // 이동할 방향을 회전시켜 구한다.
                timeIndex++;
            }

            // 1. 몸 길이를 늘려 다음 칸에 위치시킨다.
            int nx = snake.getFirst().x + dx[snakeDirection];
            int ny = snake.getFirst().y + dy[snakeDirection];

            // 벽에 부딪히면 종료한다.
            if (nx <= 0 || nx > n || ny <= 0 || ny > n) {
                System.out.println(second + 1);
                break;
            }

            // 뱀이 자기 자신의 몸에 부딪히면 종료한다.
            if (map[nx][ny] == 2) {
                System.out.println(second + 1);
                break;
            }

            // 2. 만약 이동한 칸에 사과가 있다면, 사과는 없어지고 꼬리는 그대로, 머리를 추가한다.
            if (map[nx][ny] == 1) {
                map[nx][ny] = 2;
                snake.addFirst(new Node(nx, ny));
            } else if (map[nx][ny] == 0) {
                // 3. 만약 이동한 칸에 사과가 없다면, 꼬리를 잘라 몸길이를 줄인다.
                // 꼬리가 위치한 칸 비우기.
                map[nx][ny] = 2;
                snake.addFirst(new Node(nx, ny));

                // 꼬리를 자른다. => 덱에서 꼬리에 해당하는 마지막 요소를 제거.
                Node tail = snake.removeLast();
                map[tail.x][tail.y] = 0; // 뱀의 꼬리를 자르고 길이를 원래대로 유지한다.
            }
            second++;
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    // 오른쪽 : 1
    // 왼쪽 : 3
    private static int getDirection(String dir) {
        if (dir.equals("D")) {
            return 1;
        } else {
            return 3;
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