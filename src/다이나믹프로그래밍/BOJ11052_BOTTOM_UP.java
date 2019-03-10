package 다이나믹프로그래밍;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by victory_woo on 10/03/2019
 * DP : 카드 구매하기
 * 96ms
 * 조금 어려움.
 */
public class BOJ11052_BOTTOM_UP {
    private static int[] dp = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // arr[i]는 i개가 들어있는 카드의 가격.
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(soultionCard(n, arr) + "");

        bw.flush();
        bw.close();
        br.close();

    }

    private static int soultionCard(int n, int[] arr) {
        dp[0] = arr[0];

        /*
         * 마지막으로 구매할 카드 팩이 몇개의 카드를 가지고 있는지에 초점을 맞춘다.
         * dp[i]는 i개의 카드를 구매하기 위해 지불해야 하는 금액의 최대값을 담는다.
         * arr[j]는 j개가 들어있는 카드의 가격을 나타낸다.
         * dp[i]에 금액의 최대값을 저장하고
         * dp[i]와 마지막으로 [ (i-j)개의 카드를 구매하기 위해 지불해야 하는 총 금액 + j개가 들어간 카드의 가격 ] 비교
         * 최대값을 dp에 저장.
         * */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], (dp[i - j] + arr[j]));
            }
        }
        return dp[n];
    }
}
