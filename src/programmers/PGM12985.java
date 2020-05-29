package programmers;

/**
 * created by victory_woo on 2020/05/29
 * 예상 대진표.
 */
public class PGM12985 {
    public static void main(String[] args) {
        System.out.println(solution(8, 4, 7));
        System.out.println(solution(8, 5, 7));
        System.out.println(solution(8, 7, 8));
        System.out.println(solution(8, 2, 3));
    }

    public static int solution(int n, int a, int b) {
        int turn = 0;

        // 대진표를 직접 그려가면서 규칙을 파악해야 한다.
        // 두 사람과의 경기에서 이긴 사람이 다음 라운드로 진출하면서 처음에 가지고 있던 순서를 유지해서
        // 다음 라운드에서 차례로 번호를 부여 받아야 한다.

        // 홀수는 짝수와 대결하고 짝수는 홀수와 대결한다.
        // 따라서 홀수의 경우 +1을 해주어 두 수 모두 짝수로 만들어준다.
        // 짝수 번호를 2로 나눈 값이 다음 라운드에서 그 사람이 부여받는 차례가 된다.
        // 따라서 a와 b가 같아질 때까지 반복하며 라운드를 증가시킨다.
        while (a != b) {
            if (a % 2 != 0) a += 1;
            if (b % 2 != 0) b += 1;

            a = a / 2;
            b = b / 2;
            turn++;
        }

        return turn;
    }
}
