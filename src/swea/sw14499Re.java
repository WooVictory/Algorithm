package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/10
 * 주사위 굴리기.
 * 시뮬레이션.
 * 다시 풀어보기!
 */
public class sw14499Re {
    private static StringBuilder sb = new StringBuilder();
    private static int n, m, k, x, y;
    private static int[][] map;
    private static int[] dir;
    private static int[] dice = new int[7];
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    // 동,서,북,남.
    // 이 배열은 문제에서 제시한 방향 순서대로 구성되었다.
    // 그리고 행렬을 기준으로 갖는다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        m = toInt(in[1]);
        x = toInt(in[2]);
        y = toInt(in[3]);
        k = toInt(in[4]);

        map = new int[n][m];
        dir = new int[k];

        // 지도 칸 위에 적힌 숫자를 map 배열에 저장한다.
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < m; j++) map[i][j] = toInt(in[j]);
        }

        // 방향을 저장한다.
        in = br.readLine().split(" ");
        for (int i = 0; i < k; i++) dir[i] = toInt(in[i]);

        solve();
    }

    private static void solve() {
        // 방향 명령어의 갯수 동안 for 문을 돌린다.
        for (int i = 0; i < k; i++) {
            // 방향과 다음 좌표를 구한다.
            int d = dir[i];
            int nx = dx[d - 1] + x;
            int ny = dy[d - 1] + y;

            // 좌표가 범위에 해당하는지 구한다.
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 방향에 맞게 주사위를 굴린다.
            changeDice(d);

            // 지도의 칸이 0이면 주사위의 아랫면에 값을 바닥에 복사한다.
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[6];
            } else {
                // 반대로 지도의 칸이 0이 아니면 지도에 적혀 있는 숫자를 주사위로 복사하고
                // 지도는 0으로 변경한다.
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            // nx,ny 가 현재 좌표가 되어 다음 좌표를 구할 때, 이용한다.
            x = nx;
            y = ny;

            // 주사위 윗면에 보이는 값을 추가한다.
            sb.append(dice[1]).append("\n");
        }
        // 최종적으로 결과를 출력한다.
        System.out.println(sb.toString());
    }

    // 윗면이 1이고, 아랫면이 6인 걸 정해서 동,서,북,남쪽으로 각각 굴렸을 때,
    // 어떻게 위치가 바뀌는지 직접 그려보면서 구현한다.
    private static void changeDice(int d) {
        int[] copy = dice.clone();
        switch (d) {
            case 1: // 동쪽.
                dice[1] = copy[4];
                dice[3] = copy[1];
                dice[4] = copy[6];
                dice[6] = copy[3];
                break;
            case 2: // 서쪽.
                dice[1] = copy[3];
                dice[3] = copy[6];
                dice[4] = copy[1];
                dice[6] = copy[4];
                break;
            case 3: // 북쪽.
                dice[1] = copy[5];
                dice[2] = copy[1];
                dice[5] = copy[6];
                dice[6] = copy[2];
                break;
            case 4: // 남쪽.
                dice[1] = copy[2];
                dice[2] = copy[6];
                dice[5] = copy[1];
                dice[6] = copy[5];
                break;
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
