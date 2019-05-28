package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 28/05/2019
 * 완탐 : 수들의 합2
 * 투포인터 알고리즘
 * start : 시작
 * end : 끝
 */
public class BOJ2003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = parse(input[0]);
        int m = parse(input[1]);

        int[] arr = new int[n];

        String[] num = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = parse(num[i]);
        }

        int start = 0, end = 0, count = 0;
        int sum = 0;

        while (true) {
            // 부분합이 m 보다 큰 경우 start 가 가리키는 원소를 빼고
            // start 의 값을 증가시킨다. 즉, start 뒤로 이동.
            if (sum >= m) {
                sum = sum - arr[start++];

                // end 가 n 에 도달하면 종료한다.
            } else if (end == n) {
                break;
            } else {
                // 위의 두 경우에 해당하지 않으면 end 는 뒤로 이동하면서 원소의 값을
                // sum 에 더한다.
                sum = sum + arr[end++];
            }

            // 부분 합이 m 과 같다면 count 를 증가시켜준다.
            if (sum == m) {
                count++;
            }
        }

        System.out.println(count);

    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
