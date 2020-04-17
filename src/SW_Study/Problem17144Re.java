package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/17
 * 미세먼지 안녕!
 * 삼성 기출.
 * 시뮬레이션 + 구현.
 */
public class Problem17144Re {
    private static int r, c, t;
    private static int[][] map;
    private static Node[] cleaners;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        r = toInt(in[0]);
        c = toInt(in[1]);
        t = toInt(in[2]);

        map = new int[r][c];
        cleaners = new Node[2];
        int index = 0;
        for (int i = 0; i < r; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                map[i][j] = toInt(in[j]);
                // 공기 청정기의 위치를 저장한다.
                if (map[i][j] == -1) cleaners[index++] = new Node(i, j);
            }
        }

        solve();
    }

    private static void solve() {
        for (int i = 0; i < t; i++) {
            spreadDust(); // 미세 먼지를 확장한다.
            spreadCleaner();
        }

        getResult();
    }

    // 미세먼지를 확장시킨다.
    // 이를 위해서 copy 배열을 두고 큐에서 꺼내면서 미세 먼지가 동시에 확장되는 것을 구현한다.
    private static void spreadDust() {
        // 모든 미세먼지는 동시에 확장되기 때문에 copy 배열을 두어 map 배열에서 꺼내면서 확장시킨다.
        int[][] copy = new int[r][c];

        // 미세 먼지가 존재하는 칸을 큐에 넣는다.
        LinkedList<Node> q = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) q.add(new Node(i, j));
            }
        }

        // 큐에서 꺼내면서 인접한 네 방향을 검사하면서 미세 먼지를 확장한다.
        // 이 과정은 동시에 일어난다.
        while (!q.isEmpty()) {
            Node dust = q.remove();
            int x = dust.x, y = dust.y;

            // 확산되는 양.
            int amountOfDust = map[x][y] / 5;
            int count = 0;

            // 인접한 네 방향 검사.
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 검사.
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                // 공기 청정기가 아닌 곳은 미세먼지를 확장시킬 수 있으므로, 미세먼지를 확장시킬 수 있는 공간의 갯수를 구한다.
                // 그리고 미세먼지가 있는 곳에 확산되는 양을 더해준다.
                if (map[nx][ny] != -1) {
                    count++;
                    copy[nx][ny] += amountOfDust;
                }
            }

            copy[x][y] += map[x][y] - amountOfDust * count;
        }

        copyDust(copy);
    }

    // map <- copy
    private static void copyDust(int[][] copy) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = copy[i][j];
            }
        }

        /*System.out.println("== 미세 먼지 확장 ==");
        print();*/
    }

    // 공기 청정기가 바람을 통해서 순환한다.
    // !!! 여기는 t가 아니라 공기청정기가 2칸 차지하니까 2여야 함..
    private static void spreadCleaner() {
        int[][] copy = new int[r][c];
        for (int i = 0; i < 2; i++) {
            Node cleaner = cleaners[i];
            int x = cleaner.x;
            int y = cleaner.y + 1;

            // 오른쪽으로 끝까지 이동한다.
            while (y < c - 1) {
                copy[x][y + 1] = map[x][y];
                y++;
            }

            // 반시계 방향의 경우.
            if (i == 0) {
                // 위로 올라간다.
                while (x > 0) {
                    copy[x - 1][y] = map[x][y];
                    x--;
                }
            } else { // 시계 방향의 경우.
                while (x < r - 1) {
                    copy[x + 1][y] = map[x][y];
                    x++;
                }
            }

            // 왼쪽으로 끝까지 이동한다.
            while (y > 0) {
                copy[x][y - 1] = map[x][y];
                y--;
            }

            // 반시계 방향의 경우.
            if (i == 0) {
                while (x < cleaner.x - 1) {
                    copy[x + 1][y] = map[x][y];
                    x++;
                }
            } else { // 시계 방향의 경우.
                while (x > cleaner.x + 1) {
                    // !!! -1로 했었는데, 그게 아니라 +1로 해야 함
                    // 아래에서 위로 올라가기 때문이다.
                    copy[x - 1][y] = map[x][y];
                    x--;
                }
            }
        }

        copyCleaner(copy);
    }

    // 공기 청정기의 바람이 순환하는 경로만 map 배열로 업데이트 시켜준다.
    private static void copyCleaner(int[][] copy) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 || i == r - 1 || j == 0 || j == c - 1 || i == cleaners[0].x || i == cleaners[1].x)
                    map[i][j] = copy[i][j];
            }
        }

        /*System.out.println("== 공기청정기 동작 후 ==");
        print();*/
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

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    // 확인용.
    private static void print() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
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
