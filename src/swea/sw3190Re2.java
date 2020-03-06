package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * created by victory_woo on 2020/03/06
 * 뱀.
 * 시뮬레이션.
 * 다시 풀어보기!
 */
public class sw3190Re2 {
    private static int n, k, l;
    private static int[][] map;
    private static int[] times, dirs;
    private static ArrayDeque<Dot> snake;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // dx, dy 의 순서는 북동남서 방향이다.
    // 행렬의 좌표를 기준으로 한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n + 1][n + 1]; // (1,1) 뱀이 놓이기 때문에.
        snake = new ArrayDeque<>();

        // 사과에 대한 정보를 입력받아 map 배열에 저장한다.
        k = toInt(br.readLine());
        while (k-- > 0) {
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]), y = toInt(in[1]);

            map[x][y] = 1;
        }

        // 시간과 방향에 대한 정보를 times, dirs 배열에 저장한다.
        l = toInt(br.readLine());
        times = new int[l];
        dirs = new int[l];

        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(" ");
            int t = toInt(in[0]), d = getDirection(in[1]);

            times[i] = t;
            dirs[i] = d;
        }

        solve();
    }

    // 2 : 뱀의 몸을 표현한다.
    // 0 : 빈 공간.
    // 1 : 사과가 있는 공간.
    private static void solve() {
        map[1][1] = 2;
        snake.add(new Dot(1, 1));

        int second = 0, timeIndex = 0, snakeDir = 1;

        while (true) {

            // 시간이 지나면 방향을 바꿔줘야 한다.
            // 매번 뱀이 움직이기 전 처음에 시간에 대해서 확인한다.
            if (timeIndex < l && times[timeIndex] == second) {
                snakeDir = (snakeDir + dirs[timeIndex]) % 4;
                timeIndex++;
            }

            // 1. 뱀은 다음으로 이동할 자리에 머리를 먼저 놓는다.
            int nx = snake.getFirst().x + dx[snakeDir];
            int ny = snake.getFirst().y + dy[snakeDir];

            // 뱀이 벽과 부딪히면 게임이 끝나므로 결과를 출력하고 종료한다.
            // 벽은 아예 바깥쪽에 있다.
            // <=0 이 조건은 (1,1)부터 시작하기 때문에!
            // 그래서 마지막 조건은 n 보다 크면 벽에 부딪히는 것이다.
            // (1,1)이 뱀의 위치이기 때문에 위로부터 한칸, 왼쪽으로부터 한칸 밀었을 뿐이다.
            if (nx <= 0 || ny <= 0 || nx > n || ny > n) {
                System.out.println(second + 1);
                break;
            }

            // 뱀이 자신의 몸과 부딪히면 게임이 끝나므로 출력하고 종료한다.
            if (map[nx][ny] == 2) {
                System.out.println(second + 1);
                break;
            }

            // 2. 뱀이 다음칸으로 이동했을 때, 사과가 있는 경우.
            // 뱀이 자신의 꼬리는 그대로 둔 채, 머리를 다음칸으로 이동시킨다. 즉, 몸의 길이를 확장시킨다.
            if (map[nx][ny] == 1) {
                map[nx][ny] = 2;
                snake.addFirst(new Dot(nx, ny));
            } else if (map[nx][ny] == 0) {
                // 3. 뱀이 다음칸으로 이동했을 때, 사과가 없는 경우.
                // 뱀은 자신의 머리를 다음칸으로 이동시킨다.
                // 이 경우, 몸의 길이를 확장시키는 게 아니기 때문에 뱀은 자신의 꼬리를 자른다. -> 0으로 만든다.
                map[nx][ny] = 2;
                snake.addFirst(new Dot(nx, ny));

                Dot tail = snake.removeLast();
                map[tail.x][tail.y] = 0;
            }

            second++; // 한번의 이동이 끝날 때마다 시간을 증가시켜준다.
        }

    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    private static int getDirection(String dir) {
        if (dir.equals("D")) return 1;
        else return 3;
    }

    static class Dot {
        int x;
        int y;

        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
