package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * created by victory_woo on 30/05/2019
 * 완탐 : 합이0인 네 정수
 * 내 풀이 실패함..
 */
public class BOJ7453 {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parse(br.readLine());

        // 열의 갯수가 4개이기 때문에
        // 1,2열 3,4열로 나눠서 부분 배열을 만든다.
        // 따라서 4열 N행의 구조를 갖도록 하기 위해 아래와 같이 배열을 구성했다.
        int[][] arr = new int[4][N];

        for (int i = 0; i < N; i++) {
            String[] num = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                arr[j][i] = parse(num[j]);
            }
        }

        // 반씩 나눠서 담기 위한 배열을 선언한다.
        int[] first = new int[N * N];
        int[] second = new int[N * N];

        // 1,2열 3,4열로 나눳 부분 배열의 합을 구한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                first[count] = arr[0][i] + arr[1][j];
                second[count] = arr[2][i] + arr[3][j];
                count++;
            }
        }

        // 배열을 정렬해준다.
        Arrays.sort(first);
        Arrays.sort(second);

        int left = 0, right = second.length - 1;
        long result = 0;


        // 나눠진 두 부분 배열을 가지고 합이 0이 되는지 찾는 과정이다.
        while (left < N * N && right >= 0) {
            long lv = first[left];
            long rv = second[right];

            // 합이 0이 되는 경우를 찾았을 때.
            if (lv + rv == 0) {
                long lc = 0;

                while (left < first.length && first[left] == lv) {
                    lc++;
                    left++;
                }

                long rc = 0;

                while (right >= 0 && second[right] == rv) {
                    rc++;
                    right--;
                }

                result = result + (lc * rc);
            }

            // 합이 0보다 클 때, right 를 땡겨야 하기 때문에 감소시킨다.
            if (lv + rv > 0) {
                right--;
            }

            // 합이 0보다 작을 때, left 를 밀어야 하기 때문에 증가시킨다.
            if (lv + rv < 0) {
                left++;
            }
        }
        // 결과 출력.
        System.out.println(result);
    }

    private static int parse(String str) {
        return Integer.parseInt(str);
    }
}
