package 완전탐색;

import java.io.*;
import java.util.Arrays;

/**
 * created by victory_woo on 03/05/2019
 * 완전 탐색 : 차이를 최대로.
 * 배열은 매개변수로 넘기더라고 배열 자체가 주소값을 가지기 때문에 값을 변경할 수 있다.
 */
public class BOJ10819 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        // 정렬.
        Arrays.sort(arr);
        int result = 0;
        do {
            // 모든 경우를 다 돌면서 다음 순열로 구해진 배열에 대해서
            // 차이의 최대값을 구한다.
            int sum = calculate(arr);
            result = Math.max(result, sum);
        } while (next_permutation(arr));

        bw.write(result + "");
        bw.flush();

    }

    // 순열을 구하기 위함.
    // 결국 모든 경우의 수를 다 해보기 위함.
    private static boolean next_permutation(int[] arr) {
        int i = arr.length - 1;

        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) {
            j--;
        }

        // swap
        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        j = arr.length - 1;

        // 뒤에 있는 순열을 뒤집기 위함.
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }


    private static int calculate(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            sum += Math.abs(arr[i] - arr[i + 1]);
        }
        return sum;
    }
}
