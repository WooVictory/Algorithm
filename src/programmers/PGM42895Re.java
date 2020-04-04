package programmers;

/**
 * created by victory_woo on 2020/04/04
 * N으로 표현하기.
 * 다시 푸는 중!
 * dp 유형이지만, dfs 탐색을 사용해 문제를 푼다.
 */
public class PGM42895Re {
    private static int answer = -1;

    public static void main(String[] args) {
        System.out.println(solution(5, 12));
        System.out.println(solution(2, 11));
    }

    public static int solution(int N, int number) {
        dfs(N, number, 0, 0);
        return answer;
    }

    // N, number : 입력으로 주어지는 값.
    // count : 연산의 횟수.
    // prev : 내가 만드는 값을 저장.
    private static void dfs(int N, int number, int count, int prev) {
        int temp = N;

        // 문제의 조건 중 count > 8 이면 -1를 반환한다.
        if (count > 8) {
            answer = -1;
            return;
        }

        // prev 값을 만들어서 number 값과 같아졌을 때,
        // answer 값이 -1이거나 answer 가 count 보다 크다면 최소 값을 찾아 answer 값에 저장한 뒤, return.
        if (prev == number) {
            if (answer == -1 || answer > count) {
                answer = count;
            }
            return;
        }

        // 반복문을 돌면서 사칙 연산을 수행한다. 그리고 temp 값을 업데이트한다.
        for (int i = 0; i < 8 - count; i++) {
            dfs(N, number, count + i + 1, prev + temp);
            dfs(N, number, count + i + 1, prev - temp);
            dfs(N, number, count + i + 1, prev / temp);
            dfs(N, number, count + i + 1, prev * temp);

            temp = temp * 10 + N;
        }
    }
}
