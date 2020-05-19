package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/19
 * 감시.
 */
public class sw15683 {
    private static int[][] map;
    private static int min = Integer.MAX_VALUE;
    private static int n, m;
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
        System.out.println(min);
    }

    // solve()라는 재귀 함수 호출을 통해서
    // 가능한 여러 방향으로 CCTV 를 돌려보면서 사각지대 영역의 크기가 최소가 되는 값을 찾아본다.
    // 즉, 재귀호출을 이용한 조합!
    private static void solve(int index, int[][] prev) {
        int[][] visit = new int[n][m];
        if (index == list.size()) {
            // 사각지대 영역의 크기를 계산.
            // 최소값 갱신.
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (prev[i][j] == 0) count++;
                }
            }

            min = Math.min(min, count);
        } else {
            Node node = list.get(index);
            int x = node.x, y = node.y;
            int num = node.cctv;

            switch (num) {
                case 1: // 1번 CCTV -> 4방향으로 회전할 수 있음.
                    for (int d = 0; d < 4; d++) {
                        copy(visit, prev);

                        detect(visit, x, y, d); // 한 방향씩 감시한다.
                        solve(index + 1, visit); // 재귀 호출한다.
                    }
                    break;
                case 2: // 2번 CCTV -> 2방향으로 회전 가능.
                    for (int d = 0; d < 2; d++) {
                        copy(visit, prev);

                        detect(visit, x, y, d);
                        detect(visit, x, y, (d + 2) % 4);
                        solve(index + 1, visit);
                    }
                    break;
                case 3: // 3번 CCTV -> 4방향으로 회전 가능.
                    for (int d = 0; d < 4; d++) {
                        copy(visit, prev);

                        detect(visit, x, y, d);
                        detect(visit, x, y, (d + 1) % 4);
                        solve(index + 1, visit);
                    }
                    break;
                case 4: // 4번 CCTV -> 4방향으로 회전 가능.
                    for (int d = 0; d < 4; d++) {
                        copy(visit, prev);

                        detect(visit, x, y, d);
                        detect(visit, x, y, (d + 1) % 4);
                        detect(visit, x, y, (d + 2) % 4);
                        solve(index + 1, visit);
                    }
                    break;
                case 5: // 5번 CCTV -> 1번만 해도 됨.
                    copy(visit, prev);

                    detect(visit, x, y, 0);
                    detect(visit, x, y, 1);
                    detect(visit, x, y, 2);
                    detect(visit, x, y, 3);
                    solve(index + 1, visit);
                    break;

            }
        }
    }

    // 해당 방향으로 빈 칸을 감시한다.
    // 0 : 왼쪽, 1 : 위쪽, 2 : 오른쪽, 3 : 아래쪽.
    private static void detect(int[][] copy, int x, int y, int d) {
        switch (d) {
            case 0:
                for (int col = y; col >= 0; col--) {
                    if (copy[x][col] == 6) break;
                    copy[x][col] = 9;
                }
                break;
            case 1:
                for (int row = x; row >= 0; row--) {
                    if (copy[row][y] == 6) break;
                    copy[row][y] = 9;
                }
                break;
            case 2:
                for (int col = y; col < m; col++) {
                    if (copy[x][col] == 6) break;
                    copy[x][col] = 9;
                }
                break;
            case 3:
                for (int row = x; row < n; row++) {
                    if (copy[row][y] == 6) break;
                    copy[row][y] = 9;
                }
                break;
        }
    }

    private static void copy(int[][] copy, int[][] og) {
        for (int i = 0; i < n; i++) {
            copy[i] = Arrays.copyOf(og[i], m);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int cctv;

        public Node(int x, int y, int cctv) {
            this.x = x;
            this.y = y;
            this.cctv = cctv;
        }
    }
}
