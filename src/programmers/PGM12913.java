package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/26
 * 땅따먹기.
 * dp.
 */
public class PGM12913 {
    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
        System.out.println(solution(land));
    }

    public static int solution(int[][] land) {
        int n = land.length;

        // N개의 행이 존재하고 열은 4개 밖에 없다.
        // 따라서 dp 를 통해서 가능한 경우에 대해 점화식을 세운다.
        // 각 행의 열마다 자신의 열을 제외한 이전까지의 최대값을 구해서 갱신하는 방법으로 문제를 해결한다.
        for (int i = 1; i < n; i++) {
            land[i][0] += Math.max(land[i - 1][1], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][1] += Math.max(land[i - 1][0], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][2] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][3]));
            land[i][3] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][2]));
        }

        // 각 행의 값들을 오름차순 정렬한다.
        for (int[] row : land) Arrays.sort(row);

        // 마지막 행의 마지막 열이 최고점이 된다.
        return land[n - 1][3];
    }
}
