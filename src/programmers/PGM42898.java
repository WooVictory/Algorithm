package programmers;

/**
 * created by victory_woo on 2020/04/04
 * 등굣길.
 * 유형 : dp.
 * dfs + 백트래킹으로 풀 수 있을 것 같음.
 */
public class PGM42898 {
    private static final int MOD = 1000000007;
    private static int[][] dp;

    public static void main(String[] args) {
        int[][] puddles = {{2, 2}};
        System.out.println(solution(4, 3, puddles));
    }

    public static int solution(int m, int n, int[][] puddles) {
        dp = new int[n + 1][m + 1];

        // 1. 웅덩이 부분을 -1로 초기화한다.
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        // 2. 집의 위치는 1로 초기화한다.
        dp[1][1] = 1;

        // 3. 최단거리를 구하기 때문에 왼쪽과 윗쪽으로 이동하지 않고, 아래쪽과 오른쪽으로만 이동한다.
        // 또한, 아래쪽은 위에서만 오기 때문에 이전 좌표인 위가 웅덩이가 아닌지 확인한다.
        // 마찬가지로 오른쪽은 왼쪽에서만 오기 때문에 이전 좌표인 왼쪽이 웅덩이가 아닌지 확인한다.
        // 또한, 웅덩이라면 건너뛴다.
        // dp[i] = i로 갈 수 있는 경우의 수.
        // (2,4) 같은 경우는 위에서도 올 수 있고, 왼쪽에서도 올 수 있기 때문에 두 번 모두 계산이 이루어져 2가 되어야 한다.
        // 아마 굉장히 큰 값으로 효율성 테스트를 하는 것 같다. 저장하는 값들도 MOD 연산을 해서 저장해야 한다.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == -1) continue;
                if (i - 1 > 0 && dp[i - 1][j] != -1) dp[i][j] += dp[i - 1][j] % MOD;
                if (j - 1 > 0 && dp[i][j - 1] != -1) dp[i][j] += dp[i][j - 1] % MOD;
            }
        }

        // 4. 결과는 당연히 MOD 연산을 하여 반환한다.
        return dp[n][m] % MOD;
    }
}
