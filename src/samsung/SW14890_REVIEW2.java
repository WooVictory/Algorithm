package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/25
 * 경사로 다시 풀기.
 */
public class SW14890_REVIEW2 {
    private static int N, L, count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = toInt(in[0]);
        L = toInt(in[1]);

        int[][] map = new int[N][N];
        int[][] map2 = new int[N][N];

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

    private static void solve(int index, int[][] a) {
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = a[index][i] - a[index][i + 1];
            if (diff == 0) continue; // 경사로의 높이가 같다면 다음 높이를 확인한다.

            if (diff != 1 && diff != -1) return; // 경사로의 높이차가 1이 아니라면 경사로를 놓아 길을 만들 수 없다.

            // 경사로의 높이차가 1이라면, 오른쪽으로 경사로를 놓는다.
            if (diff == 1) {
                // L개의 경사로를 놓는 동안 조건을 확인한다.
                for (int j = 1; j <= L; j++) {
                    // 범위를 벗어나거나 이미 경사로를 놓았다면 경사로를 놓을 수 없으며 지나갈 수 있는 길이 되지 못한다.
                    if (i + j >= N || visit[i + j]) return;

                    // L개 칸 모두 높이가 높은 칸과 낮은 칸의 차이가 1이어야 한다.
                    // 그렇지 않은 칸이 있으면 경사로를 놓을 수 없으며 지나갈 수 없는 길이다.
                    if (a[index][i] - a[index][i + j] == 1) visit[i + j] = true;
                    else return;
                }
            } else {
                // 경사로의 높이차가 -1이라면, 왼쪽으로 경사로를 놓는다.
                // L개의 경사로를 놓는 동안 조건을 확인한다.
                for (int j = 0; j < L; j++) {
                    // 범위를 벗어나거나 이미 경사로를 놓았다면 경사로를 놓을 수 없으며 지나갈 수 있는 길이 되지 못한다.
                    if (i - j < 0 || visit[i - j]) return;

                    // L개 칸 모두 현재 칸과 높이가 같아야 한다. 현재칸이 낮은 칸이므로!!
                    // 그렇지 않은 칸(높이가 같지 않은 칸)이 있다면 경사로를 놓을 수 없으며 지나갈 수 없는 길이다.
                    if (a[index][i] == a[index][i - j]) visit[i - j] = true;
                    else return;
                }
            }
        }
        // 무사히 지나왔으므로 길이 된다. 따라서 개수를 카운트한다.
        count++;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
