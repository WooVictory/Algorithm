package programmers;

import java.util.PriorityQueue;

/**
 * created by victory_woo on 2020/05/12
 * 더 맵게.
 * 힙(우선순위 큐)
 */
public class PGM42626 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        // 1. 우선순위 큐를 사용한다.
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 2. 맵기 정도를 heap 넣으면 낮은 값부터 큰 값으로 자동으로 오름차순 정렬된다.
        for (int a : scoville) heap.offer(a);

        // 3. 제일 앞에 있는 값이 K보다 작은 동안 반복한다.
        while (heap.peek() <= K) {

            // 4. heap 의 size 가 1이라면 스코빌 지수를 K 이상으로 만들 수 없으므로 -1을 반환한다.
            // 왜냐하면 스코빌 지수를 K 이상으로 만들기 위해서는 2개의 원소가 필요하기 때문!
            if (heap.size() == 1) return -1;

            // 가장 맵지 않은 음식의 스코빌 지수.
            int a = heap.poll();
            // 두 번째 맵지 않은 음식의 스코빌 지수.
            int b = heap.poll();
            // 음식을 섞어서 나온 값을 다시 heap 에 넣는다.
            int result = a + b * 2;
            heap.offer(result);
            // 음식을 섞은 횟수를 증가시킨다.
            answer++;
        }

        // 우선순위 큐에 다시 넣어도 우선 순위 큐의 특징에 따라 작은 값이 앞으로 오게 된다.
        // 따라서 K보다 작은 값인 동안 계속해서 값을 가공하여 K 이상이 되도록 만들고,
        // 제일 앞에 있는 원소가 K 이상이 되면 그 뒤의 원소는 모두 K보다 클 것이기 때문에 루프를 탈출한다.

        return answer;
    }
}
