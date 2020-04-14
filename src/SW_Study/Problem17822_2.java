package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/14
 * 원판 돌리기.
 * 시뮬레이션.
 * 조금 까다로움.
 */
public class Problem17822_2 {
    private static int n, m, t;
    private static int[][] map;
    private static LinkedList<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        t = toInt(in[2]);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        int x, d, k;
        for (int i = 0; i < t; i++) {
            in = br.readLine().split(" ");
            x = toInt(in[0]); // x의 배수여야 함.
            d = toInt(in[1]); // 0 : 시계, 1 : 반시계.
            k = toInt(in[2]); // 몇 칸 회전 할 지.

            for (int j = 1; j <= n; j++) {
                if (j % x == 0) rotate(j - 1, d, k);
            }

            check();
        }
        calculateSum();
    }

    // 회전이 끝난 뒤, 인접한 같은 수가 있는지 확인한다.
    // bfs.
    // 0이 아닌 값들을 큐에 먼저 넣어준다. 합과 길이도 미리 구한다.
    // 인접하면서 같은 수들이 없을 때, 평균을 구해야 하기 때문!
    private static void check() {
        int len = 0;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    q.add(new Node(i, j, map[i][j]));
                    sum += map[i][j];
                    len++;
                }
            }
        }

        // 인접한 같은 수가 없을 때.
        if (!bfs()) updateSum(sum, len);

        //println();
    }

    private static void println() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void calculateSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += map[i][j];
            }
        }

        System.out.println(sum);
    }

    // 인접한 같은 수가 하나라도 없는 경우, 예외 처리.
    // 1. 평균을 구한다.
    // 2. 평균보다 작은 값은 +1, 평균보다 큰 값은 -1, 같으면 변화 없음.
    private static void updateSum(double sum, int len) {
        double average = sum * (1.0) / len;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    if (average < map[i][j]) map[i][j] -= 1;
                    else if (map[i][j] < average) map[i][j] += 1;
                }
            }
        }
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    // 북동남서 방향.

    private static boolean bfs() {
        boolean flag = false;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Node cur = q.remove();
            int x = cur.x;
            int y = cur.y;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = (y + dy[j]) % m;
                // 마지막 요소의 경우, 오른쪽에 인접하는 수를 계산하기 위해서 나머지 연산을 사용한다.
                // 0 인덱스로 교체된다.

                // 처음 요소의 경우, 왼쪽에 인접하는 수를 계산하기 위해 m-1 인덱스로 교체한다.
                if (ny < 0) ny = m - 1;

                // 인접한 같은 수가 있는 경우.
                if (isRange(nx, ny)) {
                    if (map[nx][ny] == cur.value) {
                        flag = true;
                        map[nx][ny] = map[cur.x][cur.y] = 0;
                    }
                }
            }
        }
        return flag;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static void rotate(int x, int d, int k) {
        int[] rotatePlate = new int[m + 1];
        int index;
        for (int i = 0; i < m; i++) {
            index = calculateIndex(d, i, k);
            rotatePlate[index] = map[x][i];
        }

        map[x] = rotatePlate;
    }

    private static int calculateIndex(int d, int i, int k) {
        if (d == 0) return (i + k) % m;

        if (i - k < 0) return m + (i - k);

        return i - k;

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