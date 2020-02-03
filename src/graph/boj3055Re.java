package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/03
 * 탈출.
 * 해당 풀이 방법이 이해하기 훨씬 수월하다.
 */
public class boj3055Re {
    private static int r, c, result = Integer.MAX_VALUE;
    private static char[][] a;
    private static boolean[][] visit;
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    private static LinkedList<Spot> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        r = toInt(in[0]);
        c = toInt(in[1]);

        a = new char[r + 1][c + 1];
        visit = new boolean[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            String input = br.readLine();
            for (int j = 1; j <= c; j++) {
                a[i][j] = input.charAt(j - 1);
            }
        }

        fillWater();
        fillHedgehog();
        bfs();

        System.out.println(result == Integer.MAX_VALUE ? "KAKTUS" : result);
    }

    // 물을 먼저 큐에 넣는다. 어차피 물이 있는 곳은 하나이기 때문에 다음은 고슴도치가 된다.
    private static void fillWater() {
        for (int i = 1; i <= r; i++)
            for (int j = 1; j <= c; j++)
                if (a[i][j] == '*') q.add(new Spot(i, j, 1, '*'));
    }

    // 고슴도치를 큐에 넣는다. 물이 있는 곳을 큐에서 뺀 뒤에 고슴도치에 대해 탐색이 시작된다.
    private static void fillHedgehog() {
        for (int i = 1; i <= r; i++)
            for (int j = 1; j <= c; j++)
                if (a[i][j] == 'S') q.add(new Spot(i, j, 1, 'S'));
    }

    // 물 -> 고슴도치 -> 물 -> 고슴도치 번갈아가면서 탐색이 된다.
    // 큐의 특성 때문에.
    private static void bfs() {
        while (!q.isEmpty()) {
            Spot spot = q.remove();
            int x = spot.x, y = spot.y, distance = spot.distance, ch = spot.c;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx < 1 || ny < 1 || nx > r || ny > c) continue;

                if (visit[nx][ny]) continue;

                // 이전 좌표가 물일 경우.
                // 다음 좌표는 돌이면 안되고, 비버의 굴이면 안되고, 물이면 안된다.
                if (ch == '*') {
                    if (a[nx][ny] != 'X' && a[nx][ny] != 'D' && a[nx][ny] != '*') {
                        visit[nx][ny] = true;
                        a[nx][ny] = '*';
                        q.add(new Spot(nx, ny, distance, '*'));
                    }
                }

                // 이전 좌표가 고슴도치의 위치인 경우.
                // 다음 좌표는 비어있는 곳이거나 비버의 굴이고 돌이면 안된다.
                if (ch == 'S') {
                    if ((a[nx][ny] == '.' || a[nx][ny] == 'D') && a[nx][ny] != 'X') {
                        visit[nx][ny] = true;
                        if (a[nx][ny] == 'D') { // 비버의 굴을 만났으면, 고슴도치는 탈출할 수 있으므로 return 하면서 빠져나온다.
                            result = distance;
                            return;
                        }
                        a[nx][ny] = 'S';
                        q.add(new Spot(nx, ny, distance + 1, 'S'));
                    }
                }
            }
            print();
        }
    }

    // 물이 어떻게 차는지와 고슴도치의 위치가 어디로 이동하는지를 찍기 위한 임시 함수.
    private static void print() {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Spot {
        int x;
        int y;
        int distance; // 고슴도치가 이동하는 데 얼마나 걸렸는지 저장하는 변수.
        char c; // 이전 좌표가 어떤 상태였는지를 문자로 저장.

        Spot(int x, int y, int distance, char c) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.c = c;
        }
    }
}