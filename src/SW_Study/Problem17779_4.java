package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/22
 * 게리 멘더링2
 * 삼성 기출.
 */
public class Problem17779_4 {
    private static int n, min = Integer.MAX_VALUE;
    private static int[][] map, mark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        // 인구수를 담는 배열.
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
            }
        }
        solve();
    }

    // for 반복문을 돌면서 조건을 걸어준다.
    // 선거구를 나누기 위해 그리는 마름모가 범위에 잘 들어오도록 조건을 건다.
    private static void solve() {
        for (int x = 0; x <= n - 3; x++) {
            for (int y = 1; y <= n - 2; y++) {
                for (int d1 = 1; x + d1 <= n - 2 && 0 <= y - d1; d1++) {
                    for (int d2 = 1; x + d1 + d2 <= n - 1 && y + d2 <= n - 1 && x + d2 <= n - 2; d2++) {
                        mark = new int[n][n];

                        fillBoundaries(x, y, d1, d2);
                        fill();

                        // 위의 과정을 통해 선거구가 나눠지고 각 선거구역의 번호가 마킹된다.
                        // 따라서 선거
                        int[] people = new int[6];
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                people[mark[i][j]] += map[i][j];
                            }
                        }

                        // 5번 선거구는 경계만 칠하고 그 안의 영역을 칠하지 않았다.
                        // 따라서 people[0]에 담겨 있는 인구수는 5번 선거구의 인원의 일부이므로 people[5]에 누적해준다.
                        people[5] += people[0];

                        // people 배열에는 각 선거구역의 인구수가 담겨있으므로, 여기서 최소 인구수와 최대 인구수를 찾는다.
                        int minPeople = Integer.MAX_VALUE, maxPeople = Integer.MIN_VALUE;
                        for (int i = 1; i <= 5; i++) {
                            minPeople = Math.min(minPeople, people[i]);
                            maxPeople = Math.max(maxPeople, people[i]);
                        }

                        // min 값과 최대 인구수와 최소 인구수의 차이를 구해 더 작은 값을 저장한다.
                        min = Math.min(min, (maxPeople - minPeople));
                    }
                }
            }
        }
        System.out.println(min);
    }

    // 각 모서리는 어느 선거구역에 포함될지가 이미 정해져 있다.
    // 각 선거구를 알맞는 번호로 마킹한다.
    private static void fill() {
        fillArea(0, 0, 1);
        fillArea(0, n - 1, 2);
        fillArea(n - 1, 0, 3);
        fillArea(n - 1, n - 1, 4);
    }

    // 선거구를 value 값으로 마킹한다.
    private static void fillArea(int x, int y, int value) {
        // 범위를 벗어나게 되면 return.
        if (x < 0 || y < 0 || x >= n || y >= n) return;

        // 0이 아닌 값은 이미 마킹되어 있는 곳이거나 각 선거구를 나누는 경계이므로 return.
        if (mark[x][y] != 0) return;

        // 해당 선거구역을 value 값으로 마킹한다.
        mark[x][y] = value;

        // 상,하,좌,우를 탐색하며 마킹할 수 있는지 fillArea() 함수를 호출한다.
        fillArea(x - 1, y, value); // 상.
        fillArea(x + 1, y, value); // 하.
        fillArea(x, y - 1, value); // 좌.
        fillArea(x, y + 1, value); // 우.
    }

    // 선거구역을 나누기 위한 경계를 마킹한다.
    private static void fillBoundaries(int x, int y, int d1, int d2) {
        // 5의 경계선을 마킹한다.
        for (int i = 0; i <= d1; i++) {
            mark[x + i][y - i] = 5;
            mark[x + d2 + i][y + d2 - i] = 5;
        }

        // 5의 경계선을 마킹한다.
        for (int i = 0; i <= d2; i++) {
            mark[x + i][y + i] = 5;
            mark[x + d1 + i][y - d1 + i] = 5;
        }

        // 1~4번까지의 경계선을 마킹한다.
        for (int r = x - 1; r >= 0; r--) mark[r][y] = 1;
        for (int c = y + d2 + 1; c <= n - 1; c++) mark[x + d2][c] = 2;
        for (int c = y - d1 - 1; c >= 0; c--) mark[x + d1][c] = 3;
        for (int r = x + d1 + d2 + 1; r <= n - 1; r++) mark[r][y - d1 + d2] = 4;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
