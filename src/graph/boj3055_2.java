package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/02/02
 */
public class boj3055_2 {
    private static int r, c;
    private static char[][] a;
    private static LinkedList<Spot> water, hedgehog; // 2개의 큐를 사용.
    private static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        r = toInt(in[0]);
        c = toInt(in[1]);

        a = new char[r + 1][c + 1];
        water = new LinkedList<>();
        hedgehog = new LinkedList<>();

        for (int i = 1; i <= r; i++) {
            String input = br.readLine();
            for (int j = 1; j <= c; j++) {
                a[i][j] = input.charAt(j - 1);
                if (a[i][j] == '*') water.add(new Spot(i, j));
                if (a[i][j] == 'S') hedgehog.add(new Spot(i, j));
            }
        }


        int answer = 0;
        while (true) {
            answer++;
            if (hedgehog.size() == 0) {
                System.out.println("KAKTUS");
                return;
            }

            fillWater();
            if (fillHedgehog()) {
                System.out.println(answer);
                return;
            }

            //print();
        }

    }

    // 각 단계별로 물과 고슴도치가 있는 위치들을 보여준다.
    private static void print() {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void fillWater() {
        int size = water.size();

        for (int i = 0; i < size; i++) {
            Spot spot = water.remove();
            int x = spot.x, y = spot.y;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j], ny = y + dy[j];

                if (nx < 1 || ny < 1 || nx > r || ny > c) continue;

                if (a[nx][ny] == '.') {
                    a[nx][ny] = '*';
                    water.add(new Spot(nx, ny));
                }
            }
        }
    }

    private static boolean fillHedgehog() {
        int size = hedgehog.size();

        for (int i = 0; i < size; i++) {
            Spot spot = hedgehog.remove();
            int x = spot.x, y = spot.y;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j], ny = y + dy[j];

                if (nx < 1 || ny < 1 || nx > r || ny > c) continue;

                if (a[nx][ny] == '.') {
                    a[nx][ny] = 'S';
                    hedgehog.add(new Spot(nx, ny));
                }

                if (a[nx][ny] == 'D') {
                    hedgehog.add(new Spot(nx, ny));
                    return true;
                }
            }
        }
        return false;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Spot {
        int x;
        int y;

        Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
