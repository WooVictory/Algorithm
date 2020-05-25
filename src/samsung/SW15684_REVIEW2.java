package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/25
 * 사다리 조작 다시 풀기.
 */
public class SW15684_REVIEW2 {
    private static int N, M, H;
    private static int[][] map;
    private static int answer;
    private static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        N = toInt(in[0]);
        M = toInt(in[1]);
        H = toInt(in[2]);

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            int a = toInt(in[0]), b = toInt(in[1]);

            // 2개의 세로선을 가로선으로 연결하기 위해 1과 2로 표시한다.
            map[a][b] = 1;
            map[a][b + 1] = 2;
        }

        // 0,1,2,3개의 가로선을 차례로 놓아보면서 가로선의 개수의 최소값을 찾는다.
        for (int i = 0; i <= 3; i++) {
            answer = i;
            solve(1, 0);
            // 정답을 찾았기 때문에 반복문을 탈출한다.
            // 더 많은 개수의 가로선을 놓을 필요가 없다.
            if (flag) break;
        }

        System.out.println(flag ? answer : -1);
    }

    // 재귀 + 백트래킹을 통해 가능한 경우를 모두 만들어서 확인해본다.
    private static void solve(int v, int depth) {
        // 이미 정답을 찾았으므로 종료한다.
        if (flag) return;

        // answer 개의 가로선을 놓고난 뒤, check()가 true -> flag = true 로 설정함으로써 정답을 찾았음을 표시한다.
        if (depth == answer) {
            if (check()) flag = true;
            return;
        }

        // 백트래킹을 통해 세로선 사이에 가로선을 놓는다.
        // 인접한 두 세로선이 0이어야 가로선을 놓을 수 있다.
        for (int i = v; i <= H; i++) {
            for (int j = 1; j <= N - 1; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    solve(i, depth + 1);

                    map[i][j] = map[i][j + 1] = 0;
                }
            }
        }
    }

    // 세로선 모두를 조사하며, i번 세로선이 사다리를 타고 도착한 지점도 i인지 확인한다.
    // 모두 i -> i로 간다면 true.
    // 하나라도 그렇지 않은 경우가 존재한다면 false.
    // 1을 만나면 오른쪽으로 이동 후, 아래로 한칸 이동.
    // 2를 만나면 왼쪽으로 이동 후, 아래로 한칸 이동.
    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int row = 1, col = i;
            for (int j = 1; j <= H; j++) {
                if (map[row][col] == 1) col++;
                else if (map[row][col] == 2) col--;

                row++;
            }

            if (col != i) return false;
        }
        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
