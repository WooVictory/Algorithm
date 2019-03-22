package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 22/03/2019
 * 수학 : 소수 구하기
 * 방법 : 에라토스테네스의 체
 */
public class BOJ1929 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(SPACE);
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        boolean[] check = new boolean[n + 1];
        // 에라토스테네스의 체에서 0과 1은 제외하고 시작하기 때문에 체크한다.
        check[0] = check[1] = true;

        for (int i = 2; i <= n; i++) {
            // 체크되어 있으면 건너뛴다.
            // 체크가 되어 있다는 뜻은 소수가 아니라는 뜻이다.
            if (check[i]) {
                continue;
            }

            for (int j = i + i; j <= n; j += i) {
                // 소수가 아닌 것들을 true로 체크한다.
                check[j] = true;
            }
        }

        for (int i = m; i <= n; i++) {
            if (!check[i]) {
                bw.write(i + NEW_LINE);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
