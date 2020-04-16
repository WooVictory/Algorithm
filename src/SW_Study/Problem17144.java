package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/16
 * 미세먼지 안녕!
 * 삼성 기출.
 * bfs + 시뮬레이션.
 * 3 3 1
 * 0 30 7
 * -1 10 0
 * -1 0 20
 */
public class Problem17144 {
    private static int r, c, t;
    private static int[][] map;
    private static Node[] robot;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        r = toInt(in[0]);
        c = toInt(in[1]);
        t = toInt(in[2]);

        map = new int[r][c];
        robot = new Node[2];
        int index = 0;
        for (int i = 0; i < r; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                map[i][j] = toInt(in[j]);
                // 로봇의 위치를 넣어준다.
                if (map[i][j] == -1) robot[index++] = new Node(i, j);
            }
        }

        solve();
    }

    private static void solve() {
        for (int i = 0; i < t; i++) {
            spread();
            cleanerSpread();
        }

        getResult();
        //print(map);
    }

    private static void spread() {
        LinkedList<Node> q = new LinkedList<>();
        int[][] copy = new int[r][c];

        // 미세 먼지가 있는 칸만 큐에 넣어준다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) q.add(new Node(i, j));
            }
        }

        while (!q.isEmpty()) {
            Node cur = q.remove();

            int amount = map[cur.x][cur.y] / 5;
            int count = 0;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                // 로봇 청소기가 있는 공간이 아니면 된다.
                // 미세먼지를 확장시킬 수 있다.
                if (map[nx][ny] != -1) {
                    count++;
                    copy[nx][ny] += amount;
                }

            }
            copy[cur.x][cur.y] += map[cur.x][cur.y] - amount * count;

        }

        copyDustMap(copy);

        //System.out.println("미세먼지 확장 후의 map");
        //print(map);
    }

    private static void copyDustMap(int[][] copy) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = copy[i][j];
            }
        }
    }

    private static void getResult() {
        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void cleanerSpread() {
        int[][] afterMap = new int[r][c];
        for (int i = 0; i < 2; i++) {
            Node cleaner = robot[i];
            int x = cleaner.x;
            int y = cleaner.y + 1;

            // 오른쪽으로 끝까지 이동한다.
            while (y < c - 1) {
                afterMap[x][y + 1] = map[x][y];
                y++;
            }

            // 반시계 방향의 경우.
            if (i == 0) {
                // 위로 끝까지 이동한다.
                while (x > 0) {
                    afterMap[x - 1][y] = map[x][y];
                    x--;
                }
            } else { // 시계 방향의 경우.
                while (x < r - 1) {
                    afterMap[x + 1][y] = map[x][y];
                    x++;
                }
            }

            // 왼쪽으로 끝까지 이동한다.
            while (y > 0) {
                afterMap[x][y - 1] = map[x][y];
                y--;
            }

            // 반시계 방향의 경우.
            if (i == 0) {
                // 공기 청정기의 위치까지 아래로 이동한다.
                while (x < cleaner.x - 1) {
                    afterMap[x + 1][y] = map[x][y];
                    x++;
                }
            } else { // 시계 방향의 경우.
                // 공기 청정기의 위치까지 위로 이동한다.
                while (x > cleaner.x + 1) {
                    afterMap[x - 1][y] = map[x][y];
                    x--;
                }
            }
        }

        //System.out.println("After Map");
        //print(afterMap);
        copyCleanerMap(afterMap);
    }

    // 저기서 조건문은 &&로 걸어버려서 20분 넘게 해맸다..
    // 공기 청정기의 바람에 의해서 순환되는 경로만 map 배열에 업데이트 시켜준다.
    private static void copyCleanerMap(int[][] copy) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1 || i == robot[0].x || i == robot[1].x) {
                    map[i][j] = copy[i][j];
                }
            }
        }
        map[robot[0].x][robot[0].y] = -1;
        map[robot[1].x][robot[1].y] = -1;

        //System.out.println("합치고 나서 map");
        //print(map);
    }

    private static void print(int[][] a) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
