package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 25/03/2019
 * 수학 : 에라토스테네스의 체 문제
 */
public class BOJ2960 {
    private static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        check = new boolean[n + 1];

        bw.write(checkPrime(n, k) + "");

        bw.flush();
        bw.close();
        br.close();

    }

    private static int checkPrime(int n, int k) {
        int count = 0;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            // 소수가 아닌 수는 true로 체크한다.
            /*
             * 기존의 에라토스테네스의 체를 구하는 방식에서는 이 위치에서
             * if(check[i]){
             *   continue;
             * }
             * 로 작은 수의 배수가 체크되면 큰 수의 배수는 체크하지 않고 건너뛰도록 했다.
             * 예를 들어 2,4,6,8,10이 소수가 아닌걸로 체크하면 4의 배수는 2의 배수와 같기 때문에
             * 체크하지 않고 건너뛴다는 것이다.
             * */
            for (int j = i; j <= n; j = j + i) {

                if (check[j]) {
                    continue;
                }
                check[j] = true;
                count++;
                if (count == k) {
                    result = j;
                    break;
                }
            }
        }

        return result;
    }
}
