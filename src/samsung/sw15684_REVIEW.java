package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/22
 * 사다리 조작.
 */
public class sw15684_REVIEW {
    private static int N, M, H;
    private static int[][] map;
    private static int answer = 0;
    private static boolean finish = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        M = toInt(in[1]);
        H = toInt(in[2]);

        // 1. 입력을 받는다.
        map = new int[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            int a = toInt(in[0]), b = toInt(in[1]);

            // 2. b : 1, b+1 : 2로 표시한다.
            map[a][b] = 1;
            map[a][b + 1] = 2;
        }

        // 3. 사다리 조작을 위해 가로 연결선을 0,1,2,3개까지 놓아보면서 최소값을 찾는다.
        // 1개 놓았을 때, 사다리를 타면서 모든 세로선이 i번 -> i번 으로 이동한다면
        // 이미 최소값을 찾았기 때문에 2개, 3개를 놓았을 때는 볼 필요가 없다.
        for (int i = 0; i <= 3; i++) {
            answer = i;
            solve(1, 0);
            // 정답을 찾았다면 finish = true 가 되므로 더 많이 놓는 경우는 볼 필요가 없다.
            if (finish) break;
        }

        System.out.println(finish ? answer : -1);
    }

    private static void solve(int v, int depth) {
        // 이미 최소값을 찾았기 때문에 더 이상 볼 필요가 없다.
        if (finish) return;

        // answer 개의 연결선을 놓았다면 사다리 조작이 성공해서 모든 세로선이 i번 -> i번 으로 이동하는지 확인한다.
        if (answer == depth) {
            if (check()) finish = true;
            return;
        }

        // 세로선을 연결하는 가로선을 놓아본다. -> 백트래킹 활용!
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

    // 사다리를 타면서 모든 세로선이 i번 -> i번 으로 이동하는지 확인한다.
    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int row = 1, col = i;
            for (int j = 1; j <= H; j++) {
                // 1인지 2인지에 따라 오른쪽, 왼쪽으로 이동한다.
                if (map[row][col] == 1) col++;
                else if (map[row][col] == 2) col--;

                // 무조건 한칸 아래로 이동한다.
                row++;
            }

            // i와 col 값이 다르면 i번 -> i번 이 성립하지 않으므로 false 반환.
            if (i != col) return false;
        }
        // 여기까지 왔으면 위의 조건을 다 통과했으므로 모든 세로선이 i번 -> i번 으로 이동!
        return true;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
