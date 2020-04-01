package programmers;

/**
 * created by victory_woo on 2020/04/01
 * 타겟 넘버
 * bfs/dfs.
 */
public class PGM43165 {
    public static int count = 0;

    public static void main(String[] args) {
        solution(new int[]{1, 1, 1, 1, 1}, 3);
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        solve(0, 0, numbers, target);
        System.out.println(count);
        return answer;
    }

    public static void solve(int index, int total, int[] numbers, int target) {
        if (index == numbers.length) {
            if (target == total) count++;

            return;
        }

        solve(index + 1, total + numbers[index], numbers, target);
        solve(index + 1, total - numbers[index], numbers, target);
    }
}
