package 그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * created by victory_woo on 04/04/2019
 * 그래프 : 반복 수열
 * 단방향
 * 배열
 * 범위가 A가 9999이고 P=5일 때 최대가 된다.
 * 30만 보다는 작다.
 * 범위 때문에 런타임 에러 떴었음.
 * 범위 잘 생각하자.
 *
 */
public class BOJ2331 {
    private static final String SPACE = " ";
    private static long[] check;
    private static final int MOD = 10;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(SPACE);
        int A = parse(input[0]);
        int P = parse(input[1]);

        check = new long[500000];


        bw.write(dfs(A, P, 1) + "");
        bw.flush();
        //System.out.println(dfs(A, P, 1));

    }

    private static int parse(String input) {
        return Integer.parseInt(input);
    }

    // 제곱 해주는 기능.
    private static int pow(int a, int p) {
        int result = 1;

        for (int i = 0; i < p; i++) {
            result = result * a;
        }
        return result;
    }

    private static int next(int a, int p) {
        int result = 0;

        while (a > 0) {
            result = result + pow(a % MOD, p);
            a = a / MOD;
        }

        return result;
    }

    private static long dfs(int a, int p, int count) {
        // 방문한 적이 있다면 갯수 반환.
        if (check[a] != 0) {
            return check[a] - 1;
        }

        check[a] = count;
        int b = next(a, p);
        return dfs(b, p, count + 1);
    }
}
