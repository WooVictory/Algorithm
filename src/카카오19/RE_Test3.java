package 카카오19;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 무지의 먹방 라이브.
 */
public class RE_Test3 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2}, 5));
    }

    private static Comparator<Food> compareTime = new Comparator<Food>() {
        @Override
        public int compare(Food a, Food b) {
            return a.time - b.time;
        }
    };

    private static Comparator<Food> compareIndex = new Comparator<Food>() {
        @Override
        public int compare(Food a, Food b) {
            return a.index - b.index;
        }
    };

    public static int solution(int[] food_times, long k) {
        int len = food_times.length, n = food_times.length;
        List<Food> foods = new ArrayList<>();

        // 1. 배열에서 list 로 옮겨 담는다.
        for (int i = 0; i < len; i++) {
            foods.add(new Food(i + 1, food_times[i]));
        }

        // 2. 소요 시간 기준으로 오름차순 정렬한다.
        foods.sort(compareTime);

        // 3. foods 리스트를 순회하면서 한 번에 음식을 처리할 수 있는지 확인한다.
        int preTime = 0, index = 0;
        for (Food food : foods) {
            long diff = food.time - preTime;
            if (diff != 0) {

                // 한 번에 음식을 처리할 수 있다면 처리하고 preTime 값을 업데이트 한다.
                long spendTime = diff * n;
                if (spendTime <= k) {
                    k -= spendTime;
                    preTime = food.time;
                } else {
                    // 한 번에 처리할 수 없다면 순회를 중단하고 네트워크 고장이 처리되고 난 후,
                    // 무지가 다시 먹기 시작할 음식의 위치를 찾아서 반환한다.
                    k = k % n;
                    foods.subList(index, len).sort(compareIndex);
                    return foods.get(index + (int) k).index;
                }
            }

            // 현재 위치를 다음으로 옮겨주고, 남은 갯수인 n을 감소시킨다.
            index++;
            n--;
        }

        return -1;
    }

    static class Food {
        int index;
        int time;

        Food(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}