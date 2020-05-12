package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/12
 * 뱀.
 */
public class SW3190_REVIEW {
    private static int n, k, l;
    private static int[][] map;
    private static HashMap<Integer, String> hm;
    private static LinkedList<Snake> snakes = new LinkedList<>();
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북,동,남,서 방향.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        // 1. 배열을 확장한다.
        map = new int[n + 1][n + 1];
        hm = new HashMap<>();

        // 2. 사과를 놓는다.
        k = toInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]), y = toInt(in[1]);
            map[x][y] = 1;
        }

        // 3. map 에 시간별로 수행해야 할 명령어를 저장한다.
        l = toInt(br.readLine());
        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(" ");
            int time = toInt(in[0]);
            String command = in[1];
            hm.put(time, command);
        }

        solve();

    }

    private static void solve() {
        // 4. 뱀을 좌측 끝에 놓고 게임을 시작한다.
        map[1][1] = 9;
        snakes.add(new Snake(1, 1));

        int second = 0; // 시간.
        int dir = 1; // 첫 방향이 오른쪽이기 때문에 1.

        // 5. 루프를 돌린다.
        while (true) {
            // 6. second 초에 수행할 명령이 있는지 확인한다.
            if (hm.containsKey(second)) {
                if (hm.get(second).equals("D")) dir = (dir + 1) % 4;
                else dir = (dir + 3) % 4;
            }

            // 7. 뱀이 이동할 다음 위치를 구한다.
            int nx = snakes.getFirst().x + dx[dir];
            int ny = snakes.getFirst().y + dy[dir];

            // 8. 다음 위치가 벽이라면 시간을 출력하고 게임을 종료한다.
            if (nx <= 0 || ny <= 0 || nx > n || ny > n) {
                System.out.println(second + 1);
                return;
            }

            // 9. 다음 위치가 뱀의 몸이라면 부딪혀서 시간을 출력하고 게임을 종료한다.
            if (map[nx][ny] == 9) {
                System.out.println(second + 1);
                return;
            }

            if (map[nx][ny] == 1) {
                // 10. 사과가 있는 곳이라면 사과를 먹고 뱀의 머리를 다음 칸으로 옮긴다.
                // 그리고 꼬리는 자르지 않고 유지한다.
                map[nx][ny] = 9;
                snakes.addFirst(new Snake(nx, ny));
            } else {
                // 11. 사과가 없는 곳이라면 뱀의 머리를 다음 칸으로 옮긴다.
                map[nx][ny] = 9;
                snakes.addFirst(new Snake(nx, ny));

                // 그리고 꼬리를 자른다.
                Snake tail = snakes.removeLast();
                map[tail.x][tail.y] = 0;
            }
            second++;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Snake {
        int x;
        int y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
