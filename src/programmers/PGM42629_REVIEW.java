package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created by victory_woo on 2020/05/28
 * 라면 공장.
 * 다시 풀기.
 */
public class PGM42629_REVIEW {
    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{4, 10, 15}, new int[]{20, 5, 10}, 30));
    }

    public static int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        // 1. 우선순위 큐를 사용한다.
        // 역순으로 정렬된 자료구조로 이용하면서 가장 많은 양의 밀가루를 먼저 뺄 수 있도록 한다.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int index = 0;

        // 2. 오늘부터 k일 전까지 확인한다.
        for (int i = 0; i < k; i++) {
            // 우선적으로 밀가루를 공급받을 수 있는 날짜에 밀가루를 공급받아 우선순위 큐에 저장한다.
            if (index < dates.length && i == dates[index]) pq.add(supplies[index++]);

            // 3. 밀가루가 필요한 시점(즉, 밀가루를 다 사용해서 없는 경우)에 우선순위 큐에서 밀가루를 빼서 공급해준다.
            // 이때, 공급받을 수 있는 밀가루 중 가장 많은 양의 밀가루를 공급받는다. -> 그래야 밀가루를 공급받는 횟수를 최소화할 수 있다.
            // 우선순위 큐가 역순으로 정렬되어 있기 때문!
            if (stock == 0) {
                stock += pq.remove();

                // 공급받는 횟수를 카운트 한다.
                answer++;
            }

            // 하루마다 밀가루를 1톤씩 사용한다.
            stock--;
        }
        return answer;
    }
}
