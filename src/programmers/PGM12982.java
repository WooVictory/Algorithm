package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/03/24
 * 예산 - 2018 Summer / Winter Coding 문제.
 */
public class PGM12982 {
    public static void main(String[] args) {
        System.out.println(solution2(new int[]{1, 3, 2, 5, 4}, 9));
        System.out.println(solution2(new int[]{2, 2, 3, 3}, 10));
    }

    static int max = 0;

    public static int solution(int[] d, int budget) {
        int answer;
        Arrays.sort(d);
        dfs(0, 0, 0, d, budget);
        answer = max;

        return answer;
    }

    public static void dfs(int index, int total, int cnt, int[] d, int budget) {
        if (index == d.length) {
            if (total <= budget) {
                max = Math.max(max, cnt);
            }
            return;
        }

        dfs(index + 1, total + d[index], cnt + 1, d, budget); // 선택하는 경우
        dfs(index + 1, total, cnt, d, budget); // 선택하지 않는 경우
    }

    public static int solution2(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        for (int i = 0; i < d.length; i++) {
            budget -= d[i];
            if (budget < 0) break;
            answer++;
        }

        return answer;
    }
}
