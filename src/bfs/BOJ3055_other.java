package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 12/06/2019
 * bfs : 탈출
 * 큐 두개를 이용하는 방법.
 */
public class BOJ3055_other {
    private static int r, c;
    private static char[][] map;
    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static LinkedList<Point> water = new LinkedList<>();
    private static LinkedList<Point> hedgehog = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        r = parse(in[0]);
        c = parse(in[1]);
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch;

                // 물인 경우. 물에 해당하는 큐에 넣는다.
                if (ch == '*') {
                    water.add(new Point(i, j));
                }

                // 고슴도치. 즉, 시작점인 경우. 고슴도치에 해당하는 큐에 넣는다.
                if (ch == 'S') {
                    hedgehog.add(new Point(i, j));
                }
            }
        }

        // 몇번 탐색을 했는지
        // 몇 번만에 원하는 답을 찾았는지
        // 답을 찾는데 걸리는 최소 시간을
        // answer 로 표현한다.
        int answer = 0;
        while (true) {
            ++answer;

            // 갈 수 있는 경로가 물로 막혀 있어서
            // 고슴도치 큐에서 빼서 아무리 탐색을 해도 도달할 수 없을 때이다.
            if (hedgehog.size() == 0) {
                System.out.println("KAKTUS");
                return;
            }

            // 물을 먼저 채운다. map 배열에!
            extendWater();
            if (extendHedgehog()) {
                System.out.println(answer);
                return;
            }
        }

    }

    // 물을 확장하는 경우.
    private static void extendWater() {
        int size = water.size();

        for (int i = 0; i < size; i++) {
            Point p = water.remove();
            int x = p.x;
            int y = p.y;

            // 4 방향을 검사한다.
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                // 범위에 맞는지 검사한다.
                if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                    // 물은 비어있는 곳으로만 확장이 가능하다.
                    if (map[nx][ny] == '.') {
                        water.add(new Point(nx, ny)); // 큐에 넣고
                        map[nx][ny] = '*'; // 비어있던 곳을 물로 채운다.
                    }
                }
            }
        }
    }

    // 고슴도치가 확장하는 경우.
    private static boolean extendHedgehog() {
        int size = hedgehog.size();

        for (int i = 0; i < size; i++) {
            Point p = hedgehog.remove();
            int x = p.x;
            int y = p.y;

            // 4 방향을 검사한다.
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                // 범위에 맞는지 검사한다.
                if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                    // D인 도착지점에 도달하면 큐에 넣고 true 를 리턴한다.
                    if (map[nx][ny] == 'D') {
                        hedgehog.add(new Point(nx, ny));
                        return true;
                    }

                    // .인 비어있는 곳에 도착하면 고슴도치는 확장을 한다.
                    if (map[nx][ny] == '.') {
                        hedgehog.add(new Point(nx, ny)); // 큐에 넣고
                        map[nx][ny] = 'S'; // 방문했다는 걸 표시한다.
                    }
                }
            }
        }
        return false;
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
