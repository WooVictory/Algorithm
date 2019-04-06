package 그래프;

import java.io.*;
import java.util.LinkedList;

/**
 * created by victory_woo on 06/04/2019
 * 그래프 : 섬의 개수
 * 연결요소의 개수를 찾는 문제
 * count가 핵심!
 */
public class BOJ4963 {
    private static int[][] a;
    private static int[][] d;
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] input = br.readLine().split(SPACE);
            int w = parse(input[0]);
            int h = parse(input[1]);

            a = new int[h][w];
            d = new int[h][w];
            if (w == 0 && h == 0) {
                break;
            }

            for (int i = 0; i < h; i++) {
                String[] inputs = br.readLine().split(SPACE);
                for (int j = 0; j < w; j++) {
                    a[i][j] = parse(inputs[j]);
                }
            }

            int count = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (a[i][j] == 1 && d[i][j] == 0) {
                        bfs(i, j, ++count, h, w);
                    }
                }
            }
            bw.write(count + NEW_LINE);
        }
        bw.flush();
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    private static void bfs(int x, int y, int count, int w, int h) {
        LinkedList<House> q = new LinkedList<>();
        q.add(new House(x, y));
        d[x][y] = count;
        while (!q.isEmpty()) {
            House house = q.poll();
            x = house.x;
            y = house.y;

            for (int k = 0; k < 8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (0 <= nx && nx < w && 0 <= ny && ny < h) {
                    if (a[nx][ny] == 1 && d[nx][ny] == 0) {
                        q.add(new House(nx, ny));
                        d[nx][ny] = count;
                    }
                }
            }
        }
    }
}

class House {
    int x;
    int y;

    House(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
