package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/28
 */
public class PGM12941 {
    public static void main(String[] args) {
        int[] a = {1, 4, 2};
        int[] b = {5, 4, 4};
        /*int[] a = {1, 2};
        int[] b = {3, 4};*/
        System.out.println(solution(a, b));
    }

    public static int solution(int[] A, int[] B) {
        int answer = 0;
        // 1. 두 배열을 오름차순으로 정렬한다.
        Arrays.sort(A);
        Arrays.sort(B);
        int len = B.length - 1;

        // 2. 각 배열의 최소값과 최대값을 곱하면 최소값을 구할 수 있다.
        for (int num : A) answer += (num * B[len--]);
        return answer;
    }


}
