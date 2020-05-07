package 카카오19;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 실패율.
 */
public class RE_Test2 {
    public static void main(String[] args) {
        //System.out.println(solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3}));
        System.out.println(solution(4, new int[]{4, 4, 4, 4, 4}));
    }

    public static int[] solution(int n, int[] stages) {
        int len = stages.length;
        int[] nums = new int[n + 1];

        // 1. 스테이지 번호마다 도전 중인 사용자의 수를 배열에 담는다.
        // n+1 스테이지에 있는 사용자는 n까지 스테이지를 모두 클리어했으므로 실패율을 구하지 못하는 사용자이다.
        for (int stage : stages) {
            if (stage != n + 1) nums[stage]++;
        }

        // 2. Fail 타입의 배열을 생성한다.
        // 0번째에는 의미 없는 값을 두어, 실패율이 가장 작은 값으로 해둔다. 그래야 정렬할 때, 제일 뒤로가서 제외할 수 있다.
        Fail[] fails = new Fail[n + 1];
        fails[0] = new Fail(0, -1);

        // 3. 실패율을 구한다.
        for (int i = 1; i <= n; i++) {
            // len == 0 이면 예외가 발생하기도 하므로 실패율은 0
            if (len == 0) fails[i] = new Fail(i, 0);
            else fails[i] = new Fail(i, (double) nums[i] / len);
            // 실패율을 구한다.

            // 실패율을 구하고, 구했던 인원만큼은 빼줘야 한다.
            len -= nums[i];
        }

        // 정렬한다.
        Arrays.sort(fails);

        // answer 배열에 옮겨 담는다.
        int[] answer = new int[n];
        for (int i = 0; i < answer.length; i++) answer[i] = fails[i].index;
        return answer;
    }


    static class Fail implements Comparable<Fail> {
        int index;
        double rate;

        Fail(int index, double rate) {
            this.index = index;
            this.rate = rate;
        }

        // 실패율이 같은 경우, 스테이지 번호가 작은 순서대로 정렬.
        // 그렇지 않은 경우, 실패율이 큰 순서대로 정렬.
        @Override
        public int compareTo(Fail that) {
            if (this.rate == that.rate) return this.index - that.index;
            else if (this.rate < that.rate) return 1;
            else return -1;
        }
    }
}
