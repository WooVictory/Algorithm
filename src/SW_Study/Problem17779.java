package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/04/20
 * 게리멘더링 2
 * 삼성 기출.
 */
public class Problem17779 {
    private static final int INF = Integer.MAX_VALUE;
    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        // map 배열에는 각 칸의 인구 수를 저장한다.
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = toInt(in[j]);
            }
        }

        solve();
    }

    // 선거구를 담아서 마킹할 배열.
    private static int mark[][] = new int[20][20];

    private static void solve() {
        int min = INF;

        // for 문에 조건을 걸어서 조건에 맞는 x,y,d1,d2만 만들어낸다.
        // 아니면, for 문을 진행하고 그 안에서 조건문을 걸어서 continue 등을 통해 skip 해도 된다.
        for (int x = 0; x <= n - 3; x++) {
            for (int y = 1; y <= n - 2; y++) {
                for (int d1 = 1; x + d1 <= n - 2 && 0 <= y - d1; d1++) {
                    for (int d2 = 1; x + d1 + d2 <= n - 1 && y + d2 <= n - 1; d2++) {
                        // for 문 안에서 재사용하기 때문에 매번 0으로 초기화 한다.
                        init();

                        fillBoundaries(x, y, d1, d2);

                        // (0,0)은 항상 1번 선거구역에 포함되기 때문!
                        // 이처럼 각 모서리는 어느 선거구에 속하게 되는지 정해져 있다.
                        // 따라서 모서리를 기준으로 해당 선거구의 값을 채우기 위해 fill() 함수를 호출한다.
                        fill(0, 0, 1);
                        fill(0, n - 1, 2);
                        fill(n - 1, 0, 3);
                        fill(n - 1, n - 1, 4);

                        // 각 선거구에 속하는 인원을 담을 배열 people.
                        int[] people = new int[6];

                        // 2중 for 문을 돌면서 mark[r][c]에 들어 있는 숫자는 선거구를 의미한다.
                        // people[mark[r][c]]는 1번 선거구라고 한다면 1번 선거구에 들어있는 사람의 수를 누적해서 구한다.
                        for (int r = 0; r < n; r++) {
                            for (int c = 0; c < n; c++) {
                                int num = mark[r][c];
                                people[num] += map[r][c];
                            }
                        }
                        // 0번은 가상의 공간이므로 5번 선거구의 인원에 더해준다.
                        people[5] += people[0];

                        // 가장 작은 값과 가장 큰 값을 구한다.
                        int minP = Integer.MAX_VALUE, maxP = Integer.MIN_VALUE;
                        for (int i = 1; i <= 5; i++) {
                            minP = Math.min(minP, people[i]);
                            maxP = Math.max(maxP, people[i]);
                        }

                        // 차이의 최소값을 찾아 min 값을 갱신한다.
                        min = Math.min(min, maxP - minP);
                    }
                }
            }
        }

        System.out.println(min);
    }

    // 5번 선거구를 칠한 꼭짓점을 기준으로 영역 구분을 위한 마킹을 진행한다.
    private static void fillBoundaries(int x, int y, int d1, int d2) {
        // 5라는 값을 이용해서 경계면을 마킹한다.
        // 경계면도 5번 선거구에 속하기 때문!
        for (int i = 0; i <= d1; i++) {
            mark[x + i][y - i] = 5;
            mark[x + d2 + i][y + d2 - i] = 5;
        }

        // 나머지 경계선을 5로 칠한다.
        for (int i = 0; i <= d2; i++) {
            mark[x + i][y + i] = 5;
            mark[x + d1 + i][y - d1 + i] = 5;
        }

        // 꼭짓점의 위쪽부터 경계면을 만날 때까지 1로 마킹한다.
        for (int r = x - 1; r >= 0; r--) mark[r][y] = 1;

        for (int c = y + d2 + 1; c <= n - 1; c++) mark[x + d2][c] = 2;

        for (int c = y - d1 - 1; c >= 0; c--) mark[x + d1][c] = 3;

        for (int r = x + d1 + d2 + 1; r <= n - 1; r++) mark[r][y - d1 + d2] = 4;
    }

    // 1차원 배열만 초기화 하는 메소드가 존재하므로
    // 반복문을 돌며, 각각의 행에 대해서 초기화를 진행한다.
    private static void init() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(mark[i], 0);
        }
    }

    // 해당 공간을 채운다.
    private static void fill(int r, int c, int value) {
        // 범위를 벗어나면 더 이상 진행하지 않는다.
        if (r < 0 || c < 0 || r >= n || c >= n) return;

        // 0이 아니라면 채운 값이 있거나 각 선거구를 나누는 경계라는 뜻이므로 더 이상 진행하지 않는다.
        if (mark[r][c] != 0) return;

        // 해당 좌표에 value 값을 채우고
        // 상,하,좌,우 순서대로 value 를 채우기 위해 재귀 호출을 이용한다.
        mark[r][c] = value;
        fill(r - 1, c, value);
        fill(r + 1, c, value);
        fill(r, c - 1, value);
        fill(r, c + 1, value);
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
