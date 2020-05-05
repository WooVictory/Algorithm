package 카카오19인턴;

/**
 * created by victory_woo on 2020/05/05
 * 카카오 19 인턴 기출.
 * 징검다리 건너기.
 */
public class Test5 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

    public static int solution(int[] stones, int k) {
        int answer = 0;
        int len = stones.length;

        // 디딤돌에 쓰여있는 숫자는 결국, 건널 수 있는 친구들의 수와 같다.
        // 따라서 최대, 최소 값을 구해서 건널 수 있는 사람의 최대, 최소 값을 구한다.
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, stones[i]);
            min = Math.min(min, stones[i]);
        }

        // 이분 탐색.
        int mid;
        while (min <= max) {
            // 이분 탐색을 활용해서 그 중간인 mid 명이 디딤돌을 건널 수 있는지 확인한다.
            mid = (max + min) / 2;

            // mid 명이 지나갈 수 있다면 더 많은 인원이 지나갈 수 있는지 확인하기 위해 min 값을 증가시킨다.
            if (check(mid, len, k, stones)) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    // mid 명을 stones 배열에서 모두 뺀 후, 0보다 작은 디딤돌이 연속된 k개 만큼 존재하는지 확인한다.
    // 존재한다면 false 를 반환하고, 이는 mid 명이 지날 수 없음을 뜻한다.
    // 존재하지 않는다면 true 를 반환하고, 이는 mid 명이 지날 수 있음을 뜻한다.
    private static boolean check(int mid, int n, int k, int[] stones) {
        stones = stones.clone();
        for (int i = 0; i < n; i++) stones[i] -= mid;

        int count = 0;
        for (int i = 0; i < n; i++) {
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
