package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 22/07/2019
 * SW 역량 기출 주사위 굴리기.
 */
public class sw14499 {
    private static StringBuilder sb;
    private static final String SPACE = " ";
    private static int n, m, k;
    private static int diceX, diceY;
    private static int[][] map;
    private static int[] dice;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    // 동(1), 서(2), 북(3), 남(4)
    // 동서북남을 행렬이 아닌 좌표기준으로 했기 때문에
    // 아래에서 x,y가 바뀐다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(SPACE);
        sb = new StringBuilder();
        n = parse(in[0]);
        m = parse(in[1]);
        diceY = parse(in[2]);
        diceX = parse(in[3]);
        k = parse(in[4]);
        map = new int[20][20];
        dice = new int[7];

        for (int i = 0; i < n; i++) {
            String[] num = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                map[i][j] = parse(num[j]);
            }
        }

        String[] cmd = br.readLine().split(SPACE);
        solve(cmd);
    }

    private static void solve(String[] cmd) {

        for (int i = 0; i < k; i++) {
            int d = parse(cmd[i]);
            // 다음에 이동할 칸의 좌표를 구한다.
            int nx = dx[d - 1] + diceX;
            int ny = dy[d - 1] + diceY;

            // 범위에서 벗어나는지 확인한다.
            if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                changeDice(d);

                // 이동할 다음 칸이 0이라면 주사위의 밑바닥에 있는 숫자를 칸에 쓴다.
                if (map[ny][nx] == 0) {
                    map[ny][nx] = dice[6];
                } else {
                    // 값이 있다면 해당 값을 주사위의 밑바닥에 적는다.
                    // 그리고 해당 칸을 0으로 변경한다.
                    dice[6] = map[ny][nx];
                    map[ny][nx] = 0;
                }

                diceX = nx;
                diceY = ny;
                sb.append(dice[1]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void changeDice(int d) {
        // 원본 배열을 복사한다.
        int[] copy = dice.clone();

        if (d == 1) { // 동쪽으로 굴리기.
            dice[1] = copy[4];
            dice[3] = copy[1];
            dice[4] = copy[6];
            dice[6] = copy[3];
        } else if (d == 2) { // 서쪽으로 굴리기.
            dice[1] = copy[3];
            dice[3] = copy[6];
            dice[4] = copy[1];
            dice[6] = copy[4];
        } else if (d == 3) { // 북쪽으로 굴리기.
            dice[1] = copy[5];
            dice[2] = copy[1];
            dice[5] = copy[6];
            dice[6] = copy[2];
        } else if (d == 4) { // 남쪽으로 굴리기.
            dice[1] = copy[2];
            dice[2] = copy[6];
            dice[5] = copy[1];
            dice[6] = copy[5];
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}