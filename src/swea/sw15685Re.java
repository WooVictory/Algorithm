package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * created by victory_woo on 2020/03/26
 * 드래곤 커브.
 * 삼성 기출 문제.
 * 시뮬레이션.
 * 다시 풀어보기!
 */
public class sw15685Re {
    private static final int MAX = 100;
    private static int[][] map = new int[MAX + 1][MAX + 1]; // 100번째 인덱스까지 접근해야 하기 때문에 101로 초기화한다.
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};
    // 0:오른쪽, 1:위쪽, 2:왼쪽, 3:아래쪽.
    // x : 우측으로 이동하면 1 증가.(col)
    // y : 아래쪽으로 이동하면 1 증가.(row)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = toInt(br.readLine());

        // n만큼 돌면서 입력을 받는다.
        for (int i = 0; i < n; i++) {
            // 방향을 저장하기 위한 curves list.
            List<Integer> curves = new ArrayList<>();
            String[] in = br.readLine().split(" ");
            int x = toInt(in[0]); // x좌표.
            int y = toInt(in[1]); // y좌표.
            int d = toInt(in[2]); // 방향.
            int g = toInt(in[3]); // 세대.

            curves.add(d); // 시작 방향을 curves 리스트에 넣는다.
            // g 세대만큼 반복문을 돌면서 각 세대별 방향을 추가해준다.
            // 각 세대별 방향을 추가하는 방법은 curves 리스트에 있는 방향을 거꾸로 탐색하면서 꺼낸 방향에 +1을 하고
            // curves list 뒤로 추가한다. 3의 경우 +1하면 4가 되기 때문에 0으로 바꾸기 위해서 %4 연산을 진행한다.
            for (int j = 0; j < g; j++) {
                for (int k = curves.size() - 1; k >= 0; k--) {
                    curves.add((curves.get(k) + 1) % 4);
                }
            }

            // 시작 지점에 대해서 1을 놓는다.
            // 1을 놓는 것은 드래곤 커브를 그리는 것을 의미한다.
            map[y][x] = 1;
            // curves list 에서 방향을 꺼내서 dx[dir], dy[dir]로 이동할 방향을 구해 x,y에 더하여 이동할 좌표를 구한다.
            // 해당 좌표에는 드래곤 커브를 그릴 수 있으므로 1을 놓는다.
            for (int dir : curves) {
                x += dx[dir];
                y += dy[dir];
                map[y][x] = 1;
            }
        }
        // 2차원 배열인 map 에 드래곤 커브를 그린 뒤, solve() 함수를 호출하여
        // 정사각형의 4 꼭짓점이 1로 이루어진 정사각형의 갯수를 구한다.

        System.out.println(solve());
    }

    // 정사각형의 4 꼭짓점이 1로 이루어진 정사각형의 갯수를 구한다.
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