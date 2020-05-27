package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * created by victory_woo on 2020/05/26
 * 드래곤 커브.
 */
public class sw15685 {
    private static int[][] map = new int[101][101];
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};
    // 문제에서 제시한 방향대로 구현.
    // x축은 오른쪽 방향, y축 방향은 아래쪽 방향.
    // 우상좌하.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> curves = new ArrayList<>();
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]), y = toInt(in[1]);
            int d = toInt(in[2]), g = toInt(in[3]);

            curves.add(d);

            // 드래곤 커브를 그리기 위해 좌표가 아닌 방향 정보를 저장한다.
            // 다음 세대의 방향이 이전 세대의 방향을 거꾸로 읽으면서 반시계 방향으로 90도 회전한다는 규칙을 이용해야 한다!!
            for (int j = 0; j < g; j++) {
                for (int k = curves.size() - 1; k >= 0; k--) {
                    curves.add((curves.get(k) + 1) % 4);
                }
            }

            // 드래곤 커브를 그린다. -> 꼭짓점에 1을 놓는다.
            map[y][x] = 1;
            for (int dir : curves) {
                y += dy[dir];
                x += dx[dir];
                map[y][x] = 1;
            }
        }

        System.out.println(solve());
    }

    // 1x1 크기인 정사각형의 네 꼭지점이 모두 드래곤 커브에 속하는지 확인하고
    // 그렇다면 카운트한다.
    private static int solve() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) count++;
            }
        }
        return count;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
