package SAMSUNG_READY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/12
 * ArrayDeque, LinkedList 둘 중 하나를 사용하면 된다.
 */
public class SW3190 {
    private static int n, k, l;
    private static int[][] map;
    private static HashMap<Integer, String> hm = new HashMap<>();
    private static LinkedList<Snake> snakes = new LinkedList<>();
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북,동,남,서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());
        map = new int[n + 1][n + 1];

        // 사과 표시.
        k = toInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]), y = toInt(in[1]);
            map[x][y] = 1;
        }

        l = toInt(br.readLine());
        for (int i = 0; i < l; i++) {
            String[] in = br.readLine().split(" ");
            int time = toInt(in[0]);

            hm.put(time, in[1]);
        }

        bfs();
    }

    private static void bfs() {
        map[1][1] = 9;
        snakes.add(new Snake(1, 1));

        int second = 0, dir = 1;
        int nx, ny;
        while (true) {

            if (hm.containsKey(second)) {
                if (hm.get(second).equals("D")) dir = (dir + 1) % 4;
                else dir = (dir + 3) % 4;
            }

            nx = snakes.getFirst().x + dx[dir];
            ny = snakes.getFirst().y + dy[dir];

            // 1. 범위를 벗어나는지 확인.
            if (nx <= 0 || ny <= 0 || nx > n || ny > n) {
                System.out.println(second + 1);
                return;
            }

            // 2. 뱀이 자신의 몸통과 부딪히는지 확인.
            if (map[nx][ny] == 9) {
                System.out.println(second + 1);
                return;
            }

            // 3. 사과인 경우.
            if (map[nx][ny] == 1) {
                map[nx][ny] = 9;
                snakes.addFirst(new Snake(nx, ny));
            } else {
                // 4. 사과가 없는 경우.
                map[nx][ny] = 9;
                snakes.addFirst(new Snake(nx, ny));

                // 꼬리를 자른다.
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
        int x, y;

        Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
