package 다이나믹프로그래밍;

import java.io.*;

/**
 * created by victory_woo on 09/03/2019
 * DP : 1로 만들기 - Top Down
 * Memoization 사용.
 * 232ms
 */
public class BOJ1463_TOP_DOWN {
    // 10^6 => 1000000
    private static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        bw.write(solution(num) + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(int n) {
        // 0,1에 대한 예외 처리.
        if (n <= 1) {
            return 0;
        }

        // Memoization 방법.
        // 재귀 호출에 대해서 저장된 값이 있으면 재귀 호출을 타고 들어가지 않고 저장된 배열의 값을 사용하면 되는 개념.
        if (dp[n] > 0) {
            return dp[n];
        }

        // n에서 1을 빼는 경우.
        dp[n] = solution(n - 1) + 1;

        // n에서 n/2를 만드는 경우.
        if (n % 2 == 0) {
            int tmp = solution(n / 2) + 1;
            if (dp[n] > tmp) {
                dp[n] = tmp;
            }
        }

        // n에서 n/3을 만드는 경우.
        if (n % 3 == 0) {
            int tmp = solution(n / 3) + 1;
            if (dp[n] > tmp) {
                dp[n] = tmp;
            }
        }

        return dp[n];

    }
}
