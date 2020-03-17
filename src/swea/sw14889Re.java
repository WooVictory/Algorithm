package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 2020/03/17
 * 스타트와 링크.
 * 삼성 기출.
 * dfs.
 */
public class sw14889Re {
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
            for (int j = 1; j < n + 1; j++) map[i][j] = toInt(in[j - 1]);
        }

        solve(0, 0);
        System.out.println(answer);
    }

    // 1. dfs()를 통해 팀을 나눈다.
    // visit[] = true 인 팀
    // visit[] = false 인 팀
    // 위의 두 팀으로 나눈다.
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

        // 백트래킹을 한다.
        // divideTeam()을 통해서 팀을 한번 나눈 후 호출되는 것이 일반적이다.
        // 따라서 그 사람은 이미 true 인 팀에 속했다가 false 를 통해 초기화 된다.
        // 그리고 for 반복문을 통해서 다시 다른 팀에 속할 수 있도록 해준다.
        // visit[]을 초기화함으로써 이 사림이 다른 팀에 속할 수 있도록 해준다.
        visit[v] = false;
    }

    // 2. 구해진 경우를 가지고 두 배열로 팀을 나눈다.
    // true, false 를 기준으로 두 팀으로 나눈다.
    private static void divideTeam() {
        int[] a = new int[(n / 2) + 1];
        int[] b = new int[(n / 2) + 1];
        int ai = 1, bi = 1;

        // true, false 인 경우로 나누어 두 개의 배열에 나눠준다.
        // N 이 짝수이며, 두 개의 팀으로 나누기 때문에 두 인덱스는 같다.
        for (int i = 1; i <= n; i++) {
            if (visit[i]) a[ai++] = i;
            else b[bi++] = i;
        }

        // 3. 두 팀의 능력 차이를 구한다.
        int aResult = getResult(a), bResult = getResult(b);
        int diff = Math.abs(aResult - bResult);

        // 능력치의 차이 중 최소 값을 저장한다.
        if (answer > diff) answer = diff;
    }

    // 능력 차이를 구하기 위해서 먼저, 각 팀의 능력치의 합을 구한다.
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
