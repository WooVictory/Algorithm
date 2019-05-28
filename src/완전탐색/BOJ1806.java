package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 28/05/2019
 * 완탐 : 부분합
 * 투포인터
 */
public class BOJ1806 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(SPACE);
        int n = parse(input[0]);
        int S = parse(input[1]);

        int[] arr = new int[n + 1];
        String[] num = br.readLine().split(SPACE);
        for (int i = 0; i < n; i++) {
            arr[i] = parse(num[i]);
        }

        int start = 0, end = 0, sum = arr[0];
        int result = n + 1;

        // 부분합이 S 이상이 되는 것 중에서 가장 짧은 길이이기 때문에
        // 클 경우와 같을 경우를 둘 다 나눠서 처리해줘야 한다.
        while (start <= end && end < n) {

            if (sum < S) {
                end++;
                sum += arr[++end];
            } else if (sum == S) {
                //System.out.println("end: " + end);
                //System.out.println("start: " + start);

                // 길이는 즉 개수를 의미하는데, 이렇게 빼면 개수 카운팅이 안되서 1을 더해줘야 한다.
                result = Math.min(result, end - start + 1);
                end++;
                sum += arr[end];
            } else if (sum > S) {
                result = Math.min(result, end - start + 1);
                sum -= arr[start];
                start++;
            }
        }

        // 합을 만들 수 없는 경우.
        if (result > n) {
            result = 0;
        }

        System.out.println(result);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }

}
