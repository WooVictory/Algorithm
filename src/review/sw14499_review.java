package review;

import java.io.*;

/**
 * created by victory_woo on 22/07/2019
 * SW 역량 기출 주사위 굴리기 Review.
 */
public class sw14499_review {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static StringBuilder sb;
    private static int n, m, k;
    private static int x, y;
    private static int[][] map;
    private static int[] dice;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    // 동(1),서(2), 북(3), 남(4).
    // 행렬 기준으로 동서북남을 짰다.

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(SPACE);
        n = parse(in[0]);
        m = parse(in[1]);
        x = parse(in[2]);
        y = parse(in[3]);
        k = parse(in[4]);
        sb = new StringBuilder();

        // 초기화 및 입력.
        map = new int[n][m];
        dice = new int[7];
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(SPACE);
            for (int j = 0; j < m; j++) {
                map[i][j] = parse(in[j]);
            }
        }

        // 명령을 입력받는다.
        String[] commands = br.readLine().split(SPACE);
        solve(commands);

    }

    private static void solve(String[] commands) throws IOException {
        // 명령어의 갯수만큼 반복문을 돌아서 주사위를 굴리면 된다.
        for (int i = 0; i < k; i++) {
            int direction = parse(commands[i]);
            int nx = dx[direction - 1] + x;
            int ny = dy[direction - 1] + y;

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                // 방향에 맞게 주사위를 굴린다.
                changeDice(direction);

                // 다음 이동할 칸이 0이라면
                // 주사위 밑면의 값을 칸에 쓴다.
                if (map[nx][ny] == 0) {
                    map[nx][ny] = dice[6];
                } else {
                    // 0이 아니라면, 주사위 밑면에 해당 칸의 숫자를 쓰고
                    // 해당 칸을 0으로 설정한다.
                    dice[6] = map[nx][ny];
                    map[nx][ny] = 0;
                }

                // 다음에도 써야 하기 때문에 x,y에 저장한다.
                x = nx;
                y = ny;
                sb.append(dice[1]).append(NEW_LINE);
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    // 방향에 맞게 주사위를 굴린다.
    // 주사위를 굴리기 위해 copy 배열에 복사해서 사용한다.
    private static void changeDice(int d) {
        int[] copy = dice.clone();

        switch (d) {
            case 1: // 동쪽으로 굴리기.
                dice[1] = copy[4];
                dice[3] = copy[1];
                dice[4] = copy[6];
                dice[6] = copy[3];
                break;
            case 2: // 서쪽으로 굴리기.
                dice[1] = copy[3];
                dice[3] = copy[6];
                dice[4] = copy[1];
                dice[6] = copy[4];
                break;
            case 3: // 북쪽으로 굴리기.
                dice[1] = copy[5];
                dice[2] = copy[1];
                dice[5] = copy[6];
                dice[6] = copy[2];
                break;
            case 4: // 남쪽으로 굴리기.
                dice[1] = copy[2];
                dice[2] = copy[6];
                dice[5] = copy[1];
                dice[6] = copy[5];
                break;
        }
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
