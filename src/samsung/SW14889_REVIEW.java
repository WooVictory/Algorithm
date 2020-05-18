package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/05/18
 * 스타트와 링크.
 * 백트래킹 + 재귀(조합)
 */
public class SW14889_REVIEW {
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

    // 재귀 호출을 이용해서 n/2명씩 있는 스타트와 링크 팀으로 사람들을 나눈다.
    private static void solve(int v, int depth) {
        if (depth == n / 2) {
            divide();
        } else {
            for (int i = v + 1; i <= n; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    solve(i, depth + 1);
                }
            }
        }

        // 백트래킹.
        visit[v] = false;
    }

    // visit 배열의 true, false 를 기준으로 스타트 팀과 링크 팀으로 나눈다.
    private static void divide() {
        int[] a = new int[(n / 2) + 1];
        int[] b = new int[(n / 2) + 1];
        int ai = 1, bi = 1;

        for (int i = 1; i <= n; i++) {
            if (visit[i]) a[ai++] = i;
            else b[bi++] = i;
        }

        // 두 팀의 능력치를 구하고 그 차이를 구한다.
        int aResult = getResult(a), bResult = getResult(b);
        int diff = Math.abs(aResult - bResult);

        // 차이의 최소값을 구해 min 을 갱신한다.
        min = Math.min(min, diff);
    }

    // 팀의 능력치를 구한다.
    private static int getResult(int[] arr) {
        int len = n / 2;
        int result = 0;

        // 스타트 팀 => [1,3,6]인 경우
        // (13,31), (16,61), (36,63) 처럼 팀의 능력치를 구하면 된다.
        // 이게 두 가지를 뽑는 경우, 간단하게 조합을 구현할 수 있다.
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
