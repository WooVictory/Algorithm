package programmers;

/**
 * created by victory_woo on 2020/04/03
 * N으로 표현하기.
 * 유형 : dp.
 * 이해가 잘 안간다.
 * 다시 풀어보기!
 */
public class PGM42895 {
    private static int answer = -1;

    public static void main(String[] args) {
        System.out.println(solution(5, 12));
    }

    public static int solution(int N, int number) {
        dfs(N, number, 0, 0);
        return answer;
    }

    private static void dfs(int n, int number, int count, int prev) {
        int temp = n;
        if (count > 8) {
            answer = -1;
            return;
        }

        if (number == prev) {
            if (answer == -1 || answer > count) {
                answer = count;
            }

            return;
        }

        for (int i = 0; i < 8 - count; i++) {
            dfs(n, number, count + i + 1, prev + temp);
            dfs(n, number, count + i + 1, prev - temp);
            dfs(n, number, count + i + 1, prev / temp);
            dfs(n, number, count + i + 1, prev * temp);

            temp = temp * 10 + n;
        }
    }
}
