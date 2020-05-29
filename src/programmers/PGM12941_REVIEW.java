package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/28
 */
public class PGM12941_REVIEW {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}));
        System.out.println(solution(new int[]{1, 2}, new int[]{3, 4}));
    }

    public static int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int len = B.length - 1;
        int answer = 0;
        for (int i = 0; i < A.length; i++) {
            answer += (A[i] * B[len--]);
        }
        return answer;
    }
}
