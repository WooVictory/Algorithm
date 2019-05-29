package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 29/05/2019
 * 완탐 : 소수의 연속합
 */
public class BOJ1644 {
    private static final int MAX = 4000000;
    private static boolean[] isPrime;
    private static int[] arr;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        isPrime = new boolean[MAX + 1];
        arr = new int[MAX + 1];
        isPrime[0] = isPrime[1] = true;

        getPrime();
        setPrime();


        int start = 0, end = 0, sum = 0, result = 0;

        while (true) {
            if (sum < N) {
                sum += arr[end++];
            } else if (end == count) {
                break;
            } else {
                sum -= arr[start++];
            }

            if (sum == N) {
                result++;
            }
        }

        System.out.println(result);
    }

    // 에라토스테네스의 체를 이용한 소수 구하기.
    private static void getPrime() {

        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i]) {
                continue;
            }
            for (int j = i + i; j <= MAX; j = j + i) {
                isPrime[j] = true;
            }
        }
    }

    // 소수를 배열에 저장하는 방법이다.
    // count -> 소수의 갯수가 될 것이다.
    private static void setPrime() {
        for (int i = 2; i <= MAX; i++) {
            if (!isPrime[i]) {
                arr[count++] = i;
            }
        }
    }
}
