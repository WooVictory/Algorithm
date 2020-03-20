package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by victory_woo on 2020/03/20
 * 감시.
 * 삼성 기출 문제.
 * 재귀 호출 + 시뮬레이션.
 */
public class sw15683Re {
    private static int n, m, result = 100;
    private static int[][] map;
    private static List<Node> list = new ArrayList<>();

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
                // 1. CCTV 위치를 list 에 저장한다.
                if (1 <= map[i][j] && map[i][j] <= 5) list.add(new Node(i, j, map[i][j]));
            }
        }

        solve(0, map);
        System.out.println(result);
    }

    // 재귀 호출을 사용하는 함수.
    private static void solve(int cctvIndex, int[][] prev) {
        int[][] visit = new int[n][m];
        // cctvIndex == list.size() 는 list 에서 모든 CCTV 를 꺼내서 조회를 끝냈음을 의미한다.
        // 이떄의 사각지대의 갯수를 구하고 최소 값을 저장한다.
        if (cctvIndex == list.size()) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (prev[i][j] == 0) count++;
                }
            }

            if (result > count) result = count;
        } else {
            Node node = list.get(cctvIndex);
            int x = node.x;
            int y = node.y;
            int number = node.number;

            // number : CCTV 번호.
            switch (number) {
                case 1: // 1번 CCTV -> 4번 회전.
                    for (int i = 0; i < 4; i++) {
                        // 0 ~ n번 행까지 m열에 대해서 복사를 진행한다.
                        // deep copy
                        for (int j = 0; j < n; j++) {
                            visit[j] = Arrays.copyOf(prev[j], m);
                        }
                        monitor(visit, x, y, i);
                        solve(cctvIndex + 1, visit);
                    }
                    break;
                case 2: // 2번 CCTV -> 2번 회전.
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < n; j++) {
                            visit[j] = Arrays.copyOf(prev[j], m);
                        }
                        // 현재 방향과 반대 방향 감시.
                        monitor(visit, x, y, i);
                        monitor(visit, x, y, i + 2);
                        solve(cctvIndex + 1, visit);
                    }
                    break;
                case 3: // 3번 CCTV -> 4번 회전.
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < n; j++) {
                            visit[j] = Arrays.copyOf(prev[j], m);
                        }
                        // 현재 방향과 직각을 이루는 방향을 감시.
                        // monitor()에서 감시 순서를 좌,상,우,하 순서로 했기 때문에 직각 방향을 감시하기 위해서 아래와 같이 수행한다.
                        monitor(visit, x, y, i);
                        monitor(visit, x, y, (i + 1) % 4);
                        solve(cctvIndex + 1, visit);
                    }
                    break;
                case 4: // 4번 CCTV -> 4번 회전.
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < n; j++) {
                            visit[j] = Arrays.copyOf(prev[j], m);
                        }
                        monitor(visit, x, y, i);
                        monitor(visit, x, y, (i + 1) % 4);
                        monitor(visit, x, y, (i + 2) % 4);
                        solve(cctvIndex + 1, visit);
                    }
                    break;
                case 5: // 5번 CCTV -> 회전하지 않고 1번에 끝날 수 있다.
                    for (int j = 0; j < n; j++) {
                        visit[j] = Arrays.copyOf(prev[j], m);
                    }
                    // 5번 CCTV 는 모든 방향을 감시할 수 있기 때문에 한 번에 다 감시한다.
                    monitor(visit, x, y, 0);
                    monitor(visit, x, y, 1);
                    monitor(visit, x, y, 2);
                    monitor(visit, x, y, 3);
                    solve(cctvIndex + 1, visit);
                    break;
            }
        }
    }

    private static void monitor(int[][] visit, int x, int y, int d) {
        switch (d) {
            case 0: // 왼쪽 방향으로 감시.
                // x 좌표는 고정이고, y 좌표가 변하면서 왼쪽 방향으로 감시하면서 값을 바꿔줘야 한다.
                // CCTV 가 벽은 통과할 수 없기 때문에 벽을 만나면 빠져나온다. 벽 뒤쪽으로는 감시할 수 없기 때문이다.
                for (int i = y; i >= 0; i--) {
                    if (map[x][i] == 6) break;
                    visit[x][i] = 7;
                }
                break;
            case 1: // 위쪽.
                // y 좌표는 고정이고, x 좌표가 변하면서 위쪽 방향으로 감시하면서 값을 바꿔줘야 한다.
                // 마찬가지로 CCTV 가 벽을 통과할 수 없기 때문에 벽을 만나면 빠져나온다. 벽 뒤쪽으로는 감시할 수 없기 때문이다.
                for (int i = x; i >= 0; i--) {
                    if (map[i][y] == 6) break;
                    visit[i][y] = 7;
                }
                break;
            case 2: // 오른쪽.
                // x 좌표는 고정이고, y 좌표가 변한다. 이때는 오른쪽으로 가기 때문에 시작 y 부터 열의 끝인 m 보다 작을 때까지 반복문을 돌린다.
                for (int i = y; i < m; i++) {
                    if (map[x][i] == 6) break;
                    visit[x][i] = 7;
                }
                break;
            case 3: // 아래쪽.
                // y 좌표는 고정이고, x 좌표가 변한다. 이때는 아래쪽으로 가기 때문에 시작이 x 부터 행의 끝인 n보다 작을 때까지 반복한다.
                for (int i = x; i < n; i++) {
                    if (map[i][y] == 6) break;
                    visit[i][y] = 7;
                }
                break;
        }
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }

    static class Node {
        int x;
        int y;
        int number;

        Node(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }
}
