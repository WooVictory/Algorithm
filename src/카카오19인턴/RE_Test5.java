package 카카오19인턴;

/**
 * created by victory_woo on 2020/05/05
 * 카카오 19 인턴 기출.
 * 다시 푸는 중.
 * 징검다리 건너기.
 */
public class RE_Test5 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1},3));
    }

    private static int len;

    public static int solution(int[] stones,int k) {
        len = stones.length;
        int answer = 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, stones[i]);
            min = Math.min(min, stones[i]);
        }

        int mid;
        while (min <= max) {
            mid = (min + max) / 2;
            if (check(mid, k, stones)) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    private static boolean check(int mid, int k, int[] stones) {
        stones = stones.clone();
        for (int i = 0; i < len; i++) stones[i] -= mid;

        int count = 0;
        for (int i = 0; i < len; i++) {
            if (stones[i] < 0) {
                count++;
                if (k <= count) return false;
            } else {
                count = 0;
            }
        }
        return true;
    }
}
