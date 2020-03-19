package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/03/19
 * 감시.
 * 삼성 기출.
 * 재귀 호출 + 완탐.
 * 다시 풀어보기!
 */
public class sw15683 {
    private static int n, m, answer = 100;
    private static int[][] map;
    private static ArrayList<Node> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = toInt(in[j]);
                if (1 <= map[i][j] && map[i][j] <= 5) list.add(new Node(i, j, map[i][j]));
            }
        }

        solve(0, map);
        System.out.println(answer);
    }

    private static void solve(int index, int[][] prev) {
        int[][] visit = new int[n][m];
        // index == list.size()인 조건은 저장된 CCTV 를 꺼내서 모두 확인해 보았음을 뜻한다.
        // 따라서 0 즉, 사각지대의 영역을 구하고, 최소 값을 구한다.
        if (index == list.size()) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (prev[i][j] == 0) count++;
                }
            }
            if (answer > count) answer = count;

        } else {
            Node node = list.get(index);
            int x = node.x;
            int y = node.y;
            int idx = node.index;

            // prev 배열이 다음 탐색을 하기 전의 원본 배열 역할을 한다.
            // 따라서 CCTV 를 회전하기 전에 prev 배열을 visit 배열로 복사를 해서 visit 배열을 detect() 함수에서 바꾼다.
            // 그리고 바꾼 visit 배열을 함께 solve()를 함수를 재귀 호출하면서 넘긴다.
            // 다시 호출된 solve()에서는 visit 을 prev 로 받아서 확인을 하고 조건을 검사한다.
            // 결국, 여러 가지 경우의 수 동안 원본 배열이 계속 바뀌기 때문에 deep copy 를 이용해서 원본 배열을 복사해 복사본을 사용한다.
            switch (idx) {
                case 1: // 1번 CCTV -> 4번의 회전을 할 수 있다.
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < n; j++) visit[j] = Arrays.copyOf(prev[j], m); // deep copy

                        detect(visit, x, y, i);
                        solve(index + 1, visit);
                    }
                    break;
                case 2: // 2번 CCTV -> 2번의 회전을 할 수 있다.
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < n; j++) visit[j] = Arrays.copyOf(prev[j], m);

                        detect(visit, x, y, i);
                        detect(visit, x, y, i + 2);
                        solve(index + 1, visit);
                    }
                    break;

                case 3: // 3번 CCTV -> 4번의 회전을 할 수 있다.
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < n; j++) visit[j] = Arrays.copyOf(prev[j], m);

                        detect(visit, x, y, i);
                        detect(visit, x, y, (i + 1) % 4);
                        solve(index + 1, visit);
                    }
                    break;

                case 4: // 4번 CCTV -> 4번의 회전을 할 수 있다.
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < n; j++) visit[j] = Arrays.copyOf(prev[j], m);

                        detect(visit, x, y, i);
                        detect(visit, x, y, (i + 1) % 4);
                        detect(visit, x, y, (i + 2) % 4);
                        solve(index + 1, visit);
                    }
                    break;

                case 5: // 5번 CCTV -> 회전하지 않고 1번으로 가능하다.
                    for (int j = 0; j < n; j++) visit[j] = Arrays.copyOf(prev[j], m);

                    detect(visit, x, y, 0);
                    detect(visit, x, y, 1);
                    detect(visit, x, y, 2);
                    detect(visit, x, y, 3);
                    solve(index + 1, visit);
                    break;
            }
        }
    }

    // 7 : CCTV 에 의해서 감시 당하는 영역을 표현한다.
    private static void detect(int[][] visit, int x, int y, int d) {
        switch (d) {
            case 0: // 왼쪽.
                for (int i = y; i >= 0; i--) {
                    if (map[x][i] == 6) break;
                    visit[x][i] = 7;
                }
                break;
            case 1: // 위쪽.
                for (int i = x; i >= 0; i--) {
                    if (map[i][y] == 6) break;
                    visit[i][y] = 7;
                }
                break;
            case 2: // 오른쪽.
                for (int i = y; i < m; i++) {
                    if (map[x][i] == 6) break;
                    visit[x][i] = 7;
                }
                break;
            case 3: // 아래쪽.
                for (int i = x; i < n; i++) {
                    if (map[i][y] == 6) break;
                    visit[i][y] = 7;
                }
                break;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int index;

        Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}
