package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/22
 * 스타트와 링크.
 */
public class SW14889_REVIEW2 {
    private static int n;
    private static int[][] map;
    private static boolean[] visit;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n + 1][n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = toInt(in[j - 1]);
            }
        }

        solve(0, 0);
        System.out.println(min);
    }

    // 스타트와 링크 두 팀으로 나누기 위해 visit 배열에 n/2만큼 true 로 체크한다.
    // 그래서 n/2 만큼 체크를 했으면 스타트 팀과 링크 팀으로 사람들을 나눈다.
    // -> 재귀와 백트래킹을 통해 구현한다.
    private static void solve(int v, int count) {
        if (count == n / 2) {
            divide();
        } else {
            for (int i = v + 1; i < n; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    solve(i, count + 1);
                }
            }
        }

        // 백트래킹.
        visit[v] = false;
    }

    // 스타트 팀과 링크 팀으로 나눈다.
    private static void divide() {
        int[] a = new int[(n / 2) + 1], b = new int[(n / 2) + 1];
        int ai = 1, bi = 1;

        for (int i = 1; i <= n; i++) {
            if (visit[i]) a[ai++] = i;
            else b[bi++] = i;
        }

        // 능력치의 차이를 구해서 차이가 가장 작은 값을 찾는다.
        int diff = Math.abs(getResult(a) - getResult(b));
        min = Math.min(min, diff);
    }

    // 팀에 속한 사람들의 능력치의 합을 구한다.
    // 2명끼리의 합을 구해 나가는 것이기 때문에 2명을 뽑는 조합은 이중 for 반복문을 이용해 쉽게 구할 수 있다.
    private static int getResult(int[] arr) {
        int result = 0, len = n / 2;
        for (int i = 1; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                result += map[arr[i]][arr[j]];
                result += map[arr[j]][arr[i]];
            }
        }
        return result;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
