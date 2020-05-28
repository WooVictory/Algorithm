package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created by victory_woo on 2020/05/28
 * 라면 공장.
 */
public class PGM42629 {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{4, 10, 15}, new int[]{20, 5, 10}, 30));
    }

    public static int solution(int stock, int[] dates, int[] supplies, int k) {
        // 1. 우선순위 큐를 활용한다. -> 가장 큰 값이 먼저 나오도록 reverseOrder()를 설정한다.
        // 이렇게 되면 우선순위 큐는 자동으로 역순으로 정렬되며, poll(), remove()를 통해서 값을 뽑을 때, 큰 값이 먼저 나온다.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int answer = 0;
        int index = 0;

        // 오늘 : 0일부터 k-1일 까지 사용할 수 있는 양까지만 검사하면 된다.
        for (int i = 0; i < k; i++) {
            // 2. 우선적으로 밀가루를 공급받을 수 있는 날이 되면 우선순위 큐에 공급받을 수 있는 밀가루의 양을 저장한다.
            if (index < dates.length && i == dates[index]) pq.add(supplies[index++]);

            // 3. 그리고 밀가루가 필요한 시점에 즉, 가지고 있는 밀가루를 다 썼을 때
            // 공급 받을 수 있는 밀가루 중에서 가장 많은 양의 밀가루를 공급받는다.
            // 그래야 밀가루를 공급받는 횟수를 최소화할 수 있다.
            if (stock == 0) {
                stock += pq.remove();

                // 4. 공급받은 횟수를 카운트한다.
                answer++;
            }

            // 하루 하루 지날 때마다 밀가루의 양이 줄어든다.
            stock--;
        }
        return answer;
    }
}
