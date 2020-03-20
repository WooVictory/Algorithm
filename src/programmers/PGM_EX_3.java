package programmers;

import java.io.IOException;

/**
 * created by victory_woo on 06/04/2019
 * 3번은 사실 이해가 잘 안감. 다시 풀어봐야 할듯!
 */
public class PGM_EX_3 {

    public static void main(String[] args) throws IOException {
        int[] grade = {4, 1, 3, 5};
        System.out.println(solution(grade, 2));

    }
   /* private static void solution(int[] grade, int max_diff){

    }*/

    static public int solution(int[] grade, int max_diff) {
        int[][] teamArr = new int[10001][10001];

        int max = 0;

        int temp = 0;

        int incressedMaxDiff = max_diff + 1;

        for (int x = 0; x < incressedMaxDiff; x++) {
            for (int i = 0; i < grade.length; i++) {
                temp = (grade[i] + x) / (incressedMaxDiff);
                teamArr[x][temp]++;
                max = Math.max(max, teamArr[x][temp]);
                /*if(max < teamArr[x][temp]){
                    max = teamArr[x][temp];
                }
                return ;*/
            }
        }

        return max;
    }
}
