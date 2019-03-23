package 수학;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * created by victory_woo on 22/03/2019
 * 수학 : Base Conversion
 * A진법을 10진법으로 바꾸고
 * 10진법을 B진법으로 바꾸면 된다.
 * <p>
 * 풀이랑 다 이해가 가는데 문제가 이해가 잘 안됨.
 */
public class BOJ11576 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]); // A진법 : 17
        int B = Integer.parseInt(input[1]);

        long result = 0;
        int m = Integer.parseInt(br.readLine());
        String[] nums = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            int number = Integer.parseInt(nums[i]);
            result = result * A + number;
        }

        convert(result, B);
    }

    private static void convert(long num, int base) {
        if (num == 0) {
            return;
        }
        // 재귀호출
        convert(num / base, base);
        System.out.print(num % base + " ");
    }
}
