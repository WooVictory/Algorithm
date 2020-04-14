package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/14
 * 원판 돌리기.
 * 삼성 기출.
 */
public class Problem17822Re {
    private static final String SPACE = " ";
    private static LinkedList<Node> q;
    private static int n, m, t;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);
        n = toInt(in[0]);
        m = toInt(in[1]);
        t = toInt(in[2]);

        map = new int[n][m];
        q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        int x, d, k;
        for (int i = 0; i < t; i++) {
            in = br.readLine().split(SPACE);
            x = toInt(in[0]); // x의 배수만 회전시켜야 한다.
            d = toInt(in[1]); // 0 : 시계 방향, 1 : 반시계 방향.
            k = toInt(in[2]); // 몇 칸 회전할 지.

            for (int j = 1; j <= n; j++) {
                if (j % x == 0) rotate(j - 1, d, k);
            }

            check();
        }

        calculateSum();
    }

    // x의 배수인 원판만 회전한다.
    private static void rotate(int x, int d, int k) {
        int[] rows = new int[m];
        int index;
        for (int i = 0; i < m; i++) {
            index = calculateIndex(i, d, k);
            rows[index] = map[x][i];
        }

        map[x] = rows;
    }

    // 시계 방향과 반시계 방향에 따라서 회전한 인덱스를 반환한다.
    // 1 <= k < m
    // 1. 시계 방향인 경우, i 위치에서 이동할 k만큼 더하고 나머지 연산을 진행.
    // 2. 반시계 방향인 경우, 두 가지로 나뉜다.
    // 2-1. i의 위치에서 더 많이 회전해서 음수가 발생한 경우. (m+(i-k))
    // 2-2. i의 위치에서 음수 전까지만 이동하는 경우. (i-k)
    private static int calculateIndex(int i, int d, int k) {
        if (d == 0) return (i + k) % m;

        if (i - k < 0) return m + (i - k);

        return i - k;
    }

    // 회전이 끝난 뒤, 인접한 수 중 같은 수가 있는지 확인한다.
    // 0이 아닌 수를 큐에 미리 넣어준다.
    // 인접한 수 중 같은 수가 없을 것을 대비해 합과, 갯수를 구한다.
    private static void check() {
        int sum = 0, length = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    q.add(new Node(i, j, map[i][j]));
                    sum += map[i][j];
                    length++;
                }
            }
        }

        if (!bfs()) updateSum(sum, length);
        print();
    }

    // 인접한 수 중 같은 수가 있는 지 확인한다.
    // 같은 수는 0으로 변경.
    private static boolean bfs() {
        boolean flag = false;
        while (!q.isEmpty()) {
            Node cur = q.remove();
            int x = cur.x, y = cur.y;
            int value = cur.value;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = (y + dy[i]) % m;
                // ny 값이 끝에 도달하면 마지막 요소에 인접한 수는 첫 번째 요소가 되기 때문에 인덱스를 0으로 교체한다.

                // ny 값이 0보다 작아지면, 첫 번째 요소에 인접한 수는 마지막 요소가 되기 때문에 인덱스를 m - 1로 교체한다.
                if (ny < 0) ny = m - 1;

                // 범위를 확인 한 뒤, 인접한 수가 value 와 같으면 flag 를 바꾸고, 0으로 교체한다.
                if (isRange(nx, ny)) {
                    if (map[nx][ny] == value) {
                        flag = true;
                        map[x][y] = map[nx][ny] = 0;
                    }
                }
            }
        }
        return flag;
    }

    // 원판에서 인접한 수 중 같은 수를 못찾은 경우 평균을 구한 뒤,
    // 평균보다 큰 값은 -1, 평균보다 작은 값은 +1을 해준다.
    private static void updateSum(int sum, int length) {
        double average = sum * (1.0) / length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;

                if (map[i][j] < average) map[i][j] += 1;
                else if (average < map[i][j]) map[i][j] -= 1;
            }
        }
    }

    // 최종적인 sum 값을 계산한다.
    private static void calculateSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) sum += map[i][j];
            }
        }

        System.out.println(sum);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    // 확인용.
    private static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
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
        int value;

        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
