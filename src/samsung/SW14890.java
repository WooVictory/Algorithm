package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/18
 * 경사로.
 * 개어렵네..
 */
public class SW14890 {
    private static int n, l;
    private static int[][] map, map2;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        n = toInt(in[0]);
        l = toInt(in[1]);
        map = new int[n][n];
        map2 = new int[n][n];

        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = map2[j][i] = toInt(in[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            solve(i, map);
            solve(i, map2);
        }

        System.out.println(count);
    }

    private static void solve(int row, int[][] arr) {
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            if (arr[row][i] == arr[row][i + 1]) continue;

            int diff = arr[row][i] - arr[row][i + 1];
            if (diff != 1 && diff != -1) return;

            if (diff == 1) {
                // 오른쪽으로 경사로를 만든다.

                // i는 높은 칸을 나타내기 때문에 다음칸부터 확인해야 한다.
                // 현재 칸 다음으로 동일한 높이의 연속된 l개의 칸이 존재하는지 확인.
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || visit[i + j]) return;

                    // i가 높은 칸이므로 이 칸과 다음 j개의 칸들이 모두 높이차가 1이어야 한다.
                    // 차이가 1이라면 방문 여부를 체크하여 경사로를 놓음을 표시한다.
                    // 차이가 1이 아닌 게 하나라도 존재하면 경사로를 놓을 수 없으므로 return 한다.
                    if (arr[row][i] - arr[row][i + j] == 1) visit[i + j] = true;
                    else return;
                }
            } else {
                // 왼쪽으로 경사로를 만든다.
                for (int j = 0; j < l; j++) {
                    // i인 현재 칸이 뒤에 칸보다 높이가 1 작은 칸이 된다.
                    // 따라서 현재 자기 자신을 포함해서 동일한 높이의 l개의 연속된 칸이 존재하는지 확인한다.
                    if (i - j < 0 || visit[i - j]) return;

                    if (arr[row][i] == arr[row][i - j]) visit[i - j] = true;
                    else return;
                }
            }
        }
        count++;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
