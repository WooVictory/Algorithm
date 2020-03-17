package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/17
 * 스타트와 링크.
 * 삼성 기출
 * dfs.
 * 다시 풀어보기!
 */
public class sw14889 {
    private static int n, answer = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = new int[n + 1][n + 1];
        visit = new boolean[21];
        for (int i = 1; i < n + 1; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 1; j < n + 1; j++) {
                map[i][j] = toInt(in[j - 1]);
            }
        }

        solve(0, 0);
        System.out.println(answer);
    }

    // 1. dfs()를 통해 팀을 나눈다.
    // visit[] 배열을 통해서 true, false 로 팀을 나눈다.
    private static void solve(int v, int len) {
        if (len == n / 2) {
            divideTeam();
        } else {
            for (int i = v + 1; i <= n; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    solve(i, len + 1);
                }
            }
        }

        // 백트래킹을 위해서 초기화 시켜 준다.
        // 이렇게 함으로써 팀을 나눈 후에 다른 사람과 팀을 이루기 위해서이다.
        // 그렇게 되면 이 정점은 false 로 초기화 되었기 때문에 visit[] 이 false 인 팀에 속하게 된다.
        visit[v] = false;
    }

    // 2. 구해진 경우에서 두 팀을 배열로 나눈다.
    // 3. 두 팀의 능력 차이를 구한다.
    private static void divideTeam() {
        int[] a = new int[(n / 2) + 1];
        int[] b = new int[(n / 2) + 1];
        int ai = 1, bi = 1;

        for (int i = 1; i <= n; i++) {
            if (visit[i]) a[ai++] = i;
            else b[bi++] = i;
        }

        int aResult = getResult(a), bResult = getResult(b);
        int diff = Math.abs(aResult - bResult);

        if (answer > diff) answer = diff;
    }

    // 두 팀의 능력 차이를 구하기 위해 각 팀의 능력치의 합을 구한다.
    private static int getResult(int[] arr) {
        int result = 0;
        int len = n / 2;
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
