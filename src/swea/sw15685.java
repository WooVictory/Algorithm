package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * created by victory_woo on 2020/03/26
 * 드래곤 커브 - 삼성 SW 기출
 * 시뮬레이션.
 */
public class sw15685 {
    private static int n;
    private static int[][] map = new int[101][101]; // 100번째 인덱스를 사용해야 하기 때문에.
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};
    // 문제에서 주어진 방향에 따라서 dx, dy 배열을 정의한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        for (int i = 0; i < n; i++) {
            List<Integer> curves = new ArrayList<>();
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]);
            int y = toInt(in[1]);
            int d = toInt(in[2]); // 방향.
            int g = toInt(in[3]); // 몇 세대인지.

            curves.add(d); // 입력으로 주어진 방향을 list 에 추가한다.
            // g 값으로 주어진 세대만큼 반복문을 돌면서 각 세대별 방향을 추가한다.
            for (int j = 0; j < g; j++) {
                // 각 세대별 방향을 추가하는 방법은 방향이 들어있는 curves 리스트를 거꾸로
                // 순회하면서 추가하면 된다.
                for (int k = curves.size() - 1; k >= 0; k--) {
                    curves.add((curves.get(k) + 1) % 4);
                }
            }

            // 2차원 배열에 드래곤 커브를 그려야 한다.
            // y를 2차원 배열의 row index, x를 2차원 배열의 col index
            // 드래곤 커브의 꼭짓점에 1을 놓는다.
            map[y][x] = 1;
            // curves 리스트에 저장된 방향을 꺼내서 x,y 좌표에 dx,dy 값을 더해
            // 다음으로 가야할 방향을 구하고 1을 놓음으로써 드래곤 커브를 그린다.
            for (int dir : curves) {
                x += dx[dir];
                y += dy[dir];
                map[y][x] = 1;
            }
        }
        System.out.println(solve());
    }

    // 네 꼭짓점이 모두 1인 사각형을 카운팅하여 반환한다.
    private static int solve() {
        int count = 0;
        // 99까지만 반복문을 진행시킨다. 이유는 반복문 안에서 if 문을 통해서 현재 좌표, +1하여 오른쪽, +1하여 왼쪽, 대각선을 확인하기
        // 때문에 101까지 하게 된다면 100+1 -> 101이므로 배열의 범위를 벗어나게 된다.
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 현재 좌표가 1이고 오른쪽 방향도 1이고
                if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
