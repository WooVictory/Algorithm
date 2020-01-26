package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/01/26
 * bfs + group 이라는 배열을 통해 분리된 단지마다 번호를 부여.
 */
public class boj2667_2 {
    private static int[][] a;
    private static int[][] group;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        a = new int[n + 1][n + 1];
        group = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= n; j++) {
                a[i][j] = input.charAt(j - 1) - 48;
            }
        }

        find();
    }

    private static void find() {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i][j] == 1 && group[i][j] == 0) {
                    bfs(i, j, ++count);
                }
            }
        }

        int[] result = new int[count + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (group[i][j] != 0) {
                    result[group[i][j]] += 1;
                }
            }
        }

        System.out.println(count);
        Arrays.sort(result);
        for (int i = 1; i <= count; i++) System.out.println(result[i]);
    }

    private static void print() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(group[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs(int x, int y, int count) {
        LinkedList<Box> q = new LinkedList<>();
        q.add(new Box(x, y));
        group[x][y] = count;

        while (!q.isEmpty()) {
            Box b = q.remove();

            for (int i = 0; i < 4; i++) {
                int nx = b.x + dx[i];
                int ny = b.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > n) continue;

                if (a[nx][ny] == 1 && group[nx][ny] == 0) {
                    group[nx][ny] = count;
                    q.add(new Box(nx, ny));
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}

class Box {
    int x;
    int y;

    Box(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
