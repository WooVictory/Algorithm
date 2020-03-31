package programmers;

/**
 * created by victory_woo on 2020/03/31
 * Level2 - 카펫.
 * 완전탐색.
 * 어려움...
 * 다시 풀어보기!
 */
public class PGM42842 {
    public static void main(String[] args) {
        solution(10, 2);
        solution(8, 1);
        solution(24, 24);
    }

    public static int[] solution(int brown, int red) {
        int[] answer = new int[2];
        int width = 0;
        int height = 0;

        // width 값은 i=1,2,3, ... red 값까지 증하면서 확인하고
        // height 값은 red,red-1, ... 1까지 감소하면서 확인한다.
        // 2*width + 2*height + 4 == brown 을 만족하면 탈출하고 width, height 값을 사용한다.
        for (int i = 1; i <= red; i++) {
            width = i;
            height = (red % i == 0) ? red / i : red / i + 1;

            if (2 * width + 2 * height + 4 == brown) break;
        }

        // [0] : 가로의 길이.
        // [1] : 세로의 길이.
        // 가로의 길이가 세로의 길이와 같거나 세로의 길이보다 크므로 max 값을 취하고 세로 길이는 min 값을 취한다.
        answer[0] = Math.max(width, height) + 2;
        answer[1] = Math.min(width, height) + 2;

        System.out.println(answer[0]);
        System.out.println(answer[1]);
        return answer;
    }
}
