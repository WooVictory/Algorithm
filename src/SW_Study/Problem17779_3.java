package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/20
 * 게리 멘더링2.
 * 삼성 기출.
 *
 * 이 방법이 시간이 덜 걸리지만, 직관적이지 않은 부분이 존재한다.
 * 왜냐하면 5를 채우는 과정이 재귀 호출을 사용하기 때문이다.
 */
public class Problem17779_3 {
    private static int min = Integer.MAX_VALUE;
    private static int n;
    private static int[][] map, mark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        solve();
    }

    private static void solve() {
        for (int x = 0; x <= n - 1; x++) {
            for (int y = 0; y <= n - 1; y++) {
                for (int d1 = 1; d1 < n * 2; d1++) {
                    for (int d2 = 1; d2 < n * 2; d2++) {
                        // 범위 확인.
                        if (0 <= y - d1 && x + d1 + d2 <= n - 1 && y + d2 <= n - 1) {
                            mark = new int[n][n];

                            fill(x, y, d1, d2);

                            int[] people = new int[6];
                            for (int i = 0; i < n; i++) {
                                for (int j = 0; j < n; j++) {
                                    people[mark[i][j]] += map[i][j];
                                }
                            }

                            int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
                            for (int i = 1; i <= 5; i++) {
                                minValue = Math.min(minValue, people[i]);
                                maxValue = Math.max(maxValue, people[i]);
                            }

                            min = Math.min(min, maxValue - minValue);

                            print();
                        }
                    }
                }
            }
        }

        System.out.println(min);
    }

    private static void fill(int x, int y, int d1, int d2) {
        // 1번 선거구역의 경계와 안쪽을 칠한다.
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                mark[i][j] = 1;
            }
        }

        // 2번 선거구역의 경계와 안쪽을 칠한다.
        for (int i = 0; i <= x + d2; i++) {
            for (int j = y + 1; j <= n - 1; j++) {
                mark[i][j] = 2;
            }
        }

        // 3번 선거구역의 경계와 안쪽을 칠한다.
        for (int i = x + d1; i <= n - 1; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                mark[i][j] = 3;
            }
        }

        // 4번 선거구역의 경계와 안쪽을 칠한다.
        for (int i = x + d2 + 1; i <= n - 1; i++) {
            for (int j = y - d1 + d2; j <= n - 1; j++) {
                mark[i][j] = 4;
            }
        }

        // 5번의 경계를 칠한다.
        for (int i = 0; i <= d1; i++) {
            mark[x + i][y - i] = 5;
            mark[x + d2 + i][y + d2 - i] = 5;
        }

        for (int i = 0; i <= d2; i++) {
            mark[x + i][y + i] = 5;
            mark[x + d1 + i][y - d1 + i] = 5;
        }

        // 이렇게 되면 5번 경계 안쪽도 다른 숫자들로 채워지기 때문에 5번 경계 안의 영역을 5번 선거구역으로 칠해줘야 한다.
        for (int i = 0; i < d1; i++) {
            fillInner(x + i + 1, y - i);
        }

        // 이렇게 되면 5번 경계 안쪽도 다른 숫자들로 채워지기 때문에 5번 경계 안의 영역을 5번 선거구역으로 칠해줘야 한다.
        for (int i = 0; i < d2; i++) {
            fillInner(x + i + 1, y + i);
        }
    }

    static int[][] delta = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };

    // 재귀 호출을 이용해서 5번의 안쪽 부분을 5번으로 채워준다.
    private static void fillInner(int x, int y) {
        mark[x][y] = 5;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + delta[i][1];
            ny = y + delta[i][0];

            // 범위 안에 존재하면서 5번으로 칠해져 있지 않으면 호출한다.
            if (0 <= nx && nx < n && 0 <= ny && ny < n && mark[nx][ny] != 5) {
                fillInner(nx, ny);
            }
        }
    }

    private static void print(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mark[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
