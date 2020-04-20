package SW_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/04/20
 * 게리 멘더링2.
 * 삼성 기출.
 * 완탐 + 시뮬레이션.
 */
public class Problem17779Re {
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

    // 모든 x,y,d1,d2에 대해서 탐색하면서 조건을 검사해서 거른다.
    private static void solve() {
        for (int x = 0; x <= n - 3; x++) {
            for (int y = 1; y <= n - 2; y++) {
                for (int d1 = 1; x + d1 <= n - 2 && 0 <= y - d1; d1++) {
                    for (int d2 = 1; x + d1 + d2 <= n - 1 && y + d2 <= n - 1; d2++) {
                        mark = new int[n][n];

                        fillBoundaries(x, y, d1, d2);
                        fill();

                        // 각 선거구 번호에 맞게 인원수를 people 배열에 누적한다.
                        // 1 : 1번 선거구의 총 인원.
                        // 2 : 2번 선거구의 총 인원.
                        // 이와 같은 형식으로 저장되며, 5번 선거구 인원은 0에 저장된다.
                        int[] people = new int[6];
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                people[mark[i][j]] += map[i][j];
                            }
                        }

                        // 5번 경계 안의 선거구 인원은 0에 저장되기 때문에 다시 담아준다.
                        people[5] += people[0];

                        // min, max 값을 구해 차이를 구한 뒤, 차이값 중 최소를 구한다.
                        findAbsMinValue(people);
                        //print();
                    }
                }
            }
        }

        System.out.println(min);
    }

    // 각 선거구의 경계를 칠해준다.
    private static void fillBoundaries(int x, int y, int d1, int d2) {
        for (int i = 0; i <= d1; i++) {
            mark[x + i][y - i] = 5;
            mark[x + d2 + i][y + d2 - i] = 5;
        }

        for (int i = 0; i <= d2; i++) {
            mark[x + i][y + i] = 5;
            mark[x + d1 + i][y - d1 + i] = 5;
        }

        // 각 선거구의 경계를 칠해준다.
        for (int r = x - 1; r >= 0; r--) mark[r][y] = 1;
        for (int c = y + d2 + 1; c <= n - 1; c++) mark[x + d2][c] = 2;
        for (int c = y - d1 - 1; c >= 0; c--) mark[x + d1][c] = 3;
        for (int r = x + d1 + d2 + 1; r <= n - 1; r++) mark[r][y - d1 + d2] = 4;
    }

    private static void fill() {
        fillArea(0, 0, 1);
        fillArea(0, n - 1, 2);
        fillArea(n - 1, 0, 3);
        fillArea(n - 1, n - 1, 4);
    }

    private static void fillArea(int r, int c, int value) {
        if (r < 0 || c < 0 || r >= n || c >= n) return;

        // 0이 아닌 값이 있다는 것은 이미 각 선거구의 번호를 채웠거나 각 선거구를 구별하는 경계선을 의미하기 때문에
        // 더 이상 진행하지 않는다.
        if (mark[r][c] != 0) return;

        mark[r][c] = value; // 선거구 번호인 value 를 놓는다.
        fillArea(r - 1, c, value); // 상.
        fillArea(r + 1, c, value); // 하.
        fillArea(r, c - 1, value); // 좌.
        fillArea(r, c + 1, value); // 우.
    }

    // people 배열에 각 선거구에 맞는 인워수가 누적되어 들어간다.
    // 여기서 최소 인원과 최대 인원을 구한다.
    // 두 값의 차이를 구하고 차이 값 중에서도 최소를 찾아 min 값을 갱신시킨다.
    private static void findAbsMinValue(int[] people) {
        int minPeople = Integer.MAX_VALUE, maxPeople = Integer.MIN_VALUE;
        for (int i = 1; i < people.length; i++) {
            minPeople = Math.min(minPeople, people[i]);
            maxPeople = Math.max(maxPeople, people[i]);
        }

        min = Math.min(min, maxPeople - minPeople);
    }

    // 확인용.
    private static void print() {
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
