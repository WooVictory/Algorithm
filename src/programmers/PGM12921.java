package programmers;

/**
 * created by victory_woo on 2020/03/23
 * 소수 찾기.
 * 에라토스테네스의 체 방식을 이용해 소수를 구한다.
 */
public class PGM12921 {
    public static void main(String[] args) {

        System.out.println(solution(10));
        System.out.println(solution(5));
    }

    public static int solution(int n) {
        int answer = 0;
        boolean[] visit = new boolean[1000000];
        for (int i = 2; i <= n; i++) {
            if (visit[i]) continue; // 이미 체크된 배수는 건너뛴다.
            for (int j = i + i; j <= n; j += i) {
                visit[j] = true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!visit[i]) answer++;
        }

        return answer;
    }
}
