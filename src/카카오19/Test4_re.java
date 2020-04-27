package 카카오19;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * created by victory_woo on 2020/04/26
 */
public class Test4_re {
    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        System.out.println(solution(food_times, 5));
    }

    private static Comparator<Food> compareTime = new Comparator<Food>() {
        @Override
        public int compare(Food a, Food b) {
            return Integer.compare(a.time, b.time);
        }
    };

    private static Comparator<Food> compareIndex = new Comparator<Food>() {
        @Override
        public int compare(Food a, Food b) {
            return Integer.compare(a.index, b.index);
        }
    };

    public static int solution(int[] food_times, long k) {
        int n = food_times.length;
        List<Food> foods = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            foods.add(new Food(i + 1, food_times[i]));
        }

        // 음식을 먹는데 걸리는 시간이 작은 순서대로 정렬을 한다.
        foods.sort(compareTime);

        int preTime = 0, index = 0;

        for (Food food : foods) {
            long diff = food.time - preTime;
            if (diff != 0) {
                // 사용할 수 있는 시간을 구한다.
                long spendTime = diff * n;

                // 한 번에 뺄 수 있는 시간(쓸 수 있는 시간)이 k 이하라면 빼준다.
                // 그리고 preTime 을 현재 food.time 으로 업데이트 한다.
                if (spendTime <= k) {
                    k -= spendTime;
                    preTime = food.time;
                } else {
                    // 한번에 뺄 수 있는 시간이 k 값보다 크다면 순회를 중단하고 답을 찾아야 한다.
                    // 나머지 연산을 통해 다음에 먹어야 하는 음식의 번호를 인덱스를 구한다.
                    k = k % n;
                    // 현재 위치부터 끝까지 자르고, 음식의 번호 순서대로 정렬한다.
                    foods.subList(index, food_times.length).sort(compareIndex);

                    // 네트워크 문제를 해결한 후, 음식을 다시 먹을 때의 번호를 구하기 위해 현재 위치(index) + k 를 통해 번호를 구한다.
                    return foods.get(index + (int) k).index;
                }
            }
            index++;
            n--;
        }
        return -1;
    }

    static class Food {
        int index; // 음식 순서.
        int time; // 음식을 먹는데 걸리는 시간.

        Food(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
