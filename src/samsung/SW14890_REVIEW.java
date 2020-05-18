package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/18
 * 경사로.
 * 시뮬레이션.
 */
public class SW14890_REVIEW {
    private static int N, L;
    private static int[][] map, map2;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        L = toInt(in[1]);

        // 행과 열을 바꿔서 저장하기 위해 2개의 배열을 사용한다. -> 탐색을 1번으로 줄여준다.
        map = new int[N][N];
        map2 = new int[N][N];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = map2[j][i] = toInt(in[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            solve(i, map);
            solve(i, map2);
        }
        System.out.println(count);
    }

    private static void solve(int row, int[][] a) {
        boolean[] visit = new boolean[N];
        for (int col = 0; col < N - 1; col++) {
            // 높이가 같으면 건너뛴다. 만약, 모든 칸의 높이가 같다면 아래 로직을 실행하지 않고 반복문을 다 돌고 나올 것이다.
            // 길이 존재하므로 길의 개수를 카운트한다.
            if (a[row][col] == a[row][col + 1]) continue;

            // diff 를 구해서 높이 차이가 1이 아니라면 경사로를 놓아도 길을 만들 수 없으므로 종료한다.
            int diff = a[row][col] - a[row][col + 1];
            if (diff != 1 && diff != -1) return;

            if (diff == -1) { // 왼쪽으로 경사로를 놓는다.
                // col 가 현재 자기 자신(낮은 칸이다.)
                // 자기를 포함해 왼쪽으로 L개의 경사로를 놓을 수 있는지 확인한다.
                for (int j = 0; j < L; j++) {
                    // 범위를 벗어나거나 이미 경사로를 놓았다면 종료한다.
                    if (col - j < 0 || visit[col - j]) return;

                    // 경사로를 놓은 낮은 칸의 높이는 모두 같아야 하고,
                    // 자기 자신이 낮은 칸이므로 자기 자신과 왼쪽으로 L개의 칸이 연속되면서 같은지 확인한다.
                    // 같다면 visit 배열에 체크함으로써 경사로를 놓았음을 표현한다.
                    // 같지 않다면 종료한다. 경사로를 놓을 수 없으므로
                    if (a[row][col] == a[row][col - j]) visit[col - j] = true;
                    else return;
                }
            } else { // 오른쪽으로 경사로를 놓는다.
                // col 가 현재 자기 자신(높은 칸이다.)
                // 자신의 오른쪽으로 L개의 연속된 칸에 경사로를 놓을 수 있는지 확인한다.
                for (int j = 1; j <= L; j++) {
                    // 범위를 벗어나거나 이미 경사로를 놓았다면 종료한다.
                    if (col + j >= N || visit[col + j]) return;

                    // 자기 자신과 오른쪽에 있는 칸을 비교하면서 높이 차이가 1이라면 경사로를 놓을 수 있다.
                    // 낮은 칸들이 L개 연속된다면 경사로를 놓아 길을 만들 수 있을 것이다.
                    // 그렇지 않은 칸이 한 개라도 존재한다면 즉, 높이 차이가 1이 아닌 칸이 존재한다면 경사로를 놓지 못해
                    // 길을 만들 수 없으므로 종료한다.
                    if (a[row][col] - a[row][col + j] == 1) visit[col + j] = true;
                    else return;
                }
            }
        }
        // 여기까지 왔다면 길이 존재하거나 경사로를 놓아 길을 만들 수 있는 것이므로 길의 개수를 카운트한다.
        count++;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
