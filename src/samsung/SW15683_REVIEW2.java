package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/05/25
 * 감시 다시 푸는 중.
 */
public class SW15683_REVIEW2 {
    private static int N, M;
    private static ArrayList<Node> list;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);

        int[][] map = new int[N][M];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = toInt(in[j]);
                if (1 <= map[i][j] && map[i][j] <= 5) list.add(new Node(i, j, map[i][j]));
            }
        }

        solve(0, map);
        System.out.println(min);
    }

    // 재귀를 통해 list 에 있는 CCTV 를 돌릴 수 있는 모든 방향으로 회전시켜 보면서
    // 사각 지대의 최소값을 구한다.
    private static void solve(int index, int[][] prev) {
        int[][] copy = new int[N][M];

        if (index == list.size()) {
            getResult(prev);
        } else {
            Node node = list.get(index);
            int x = node.x, y = node.y;
            int num = node.num;

            switch (num) {
                case 1: // 1 CCTV : 4번의 회전 가능.
                    for (int i = 0; i < 4; i++) {
                        copyArray(copy, prev);

                        detect(x, y, i, copy);
                        solve(index + 1, copy);
                    }
                    break;
                case 2: // 2 CCTV : 2번의 회전 가능.
                    for (int i = 0; i < 2; i++) {
                        copyArray(copy, prev);

                        detect(x, y, i, copy);
                        detect(x, y, i + 2, copy);
                        solve(index + 1, copy);
                    }
                    break;
                case 3: // 3 CCTV : 4번의 회전 가능.
                    for (int i = 0; i < 4; i++) {
                        copyArray(copy, prev);

                        detect(x, y, i, copy);
                        detect(x, y, (i + 1) % 4, copy);
                        solve(index + 1, copy);
                    }
                    break;
                case 4: // 4 CCTV : 4번의 회전 가능.
                    for (int i = 0; i < 4; i++) {
                        copyArray(copy, prev);

                        detect(x, y, i, copy);
                        detect(x, y, (i + 1) % 4, copy);
                        detect(x, y, (i + 2) % 4, copy);
                        solve(index + 1, copy);
                    }
                    break;
                case 5:
                    copyArray(copy, prev);

                    detect(x, y, 0, copy);
                    detect(x, y, 1, copy);
                    detect(x, y, 2, copy);
                    detect(x, y, 3, copy);
                    solve(index + 1, copy);
                    break;
            }
        }
    }

    private static void getResult(int[][] prev) {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (prev[i][j] == 0) result++;
            }
        }

        min = Math.min(min, result);
    }

    // 벽을 만나면 그 뒤에 위치한 공간은 더이상 감시할 수 없다.
    // 따라서 벽을 만나면 반복문을 탈출한다.
    // 감시했음을 9로 표시한다.
    private static void detect(int x, int y, int dir, int[][] a) {
        switch (dir) {
            case 0: // 위쪽 방향.
                for (int row = x - 1; row >= 0; row--) {
                    if (a[row][y] == 6) break;
                    a[row][y] = 9; // 감시했음을 표시한다.
                }
                break;
            case 1: // 오른쪽 방향.
                for (int col = y + 1; col < M; col++) {
                    if (a[x][col] == 6) break;
                    a[x][col] = 9;
                }
                break;
            case 2: // 아래쪽 방향.
                for (int row = x + 1; row < N; row++) {
                    if (a[row][y] == 6) break;
                    a[row][y] = 9;
                }
                break;
            case 3: // 왼쪽 방향.
                for (int col = y - 1; col >= 0; col--) {
                    if (a[x][col] == 6) break;
                    a[x][col] = 9;
                }
                break;
        }
    }

    // 2차원 배열 deep copy.
    private static void copyArray(int[][] copy, int[][] og) {
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(og[i], 0, copy[i], 0, M);
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node {
        int x;
        int y;
        int num;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
