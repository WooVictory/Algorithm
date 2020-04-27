package 카카오19;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * created by victory_woo on 2020/04/27
 * 카카오 19 기출.
 * 무지의 먹방 라이브.
 */
public class Test4_3 {
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

        // 음식을 먹는데 걸리는 시간을 기준으로 오름차순 정렬.
        foods.sort(compareTime);

        // 시간 기준으로 오름차순 정렬되어 있으므로
        // 순회하면서 한 번에 뺄 수 있는 것들은 한 번에 빼도록 한다.
        int index = 0, preTime = 0;
        for (Food food : foods) {
            // diff 값을 구한다.
            long diff = food.time - preTime;

            // diff 값이 0이 아닐 때만 계산한다.
            // 0인 경우는 건너뛴다.
            if (diff != 0) {

                // 한 번에 뺄 수 있는 시간(쓸 수 있는 시간)을 구한다.
                long spendTime = diff * n;

                // 한 번에 뺄 수 있는 시간이 k 보다 작거나 같으면 사용 가능.
                // 시간을 k에서 빼주고 preTime 을 현재 food.time 값으로 갱신.
                if (spendTime <= k) {
                    k -= spendTime;
                    preTime = food.time;
                } else {
                    // 그렇지 않은 경우, 순회를 중단하고 답을 찾으러 가면 된다.
                    // 현재 시간 k를 n으로 나눈 나머지 연산을 통해 재개되었을 때, 다시 음식을 먹어야 할 위치를 구한다.
                    k %= n;

                    // 현재 위치부터 끝까지 리스트를 자르고 index 기준으로 오름 차순 정렬한다.
                    foods.subList(index, food_times.length).sort(compareIndex);
                    // 그리고 현재 인덱스만큼 온 것 + k 인덱스를 더해 그 음식의 번호를 반환한다.
                    return foods.get(index + (int) k).index;
                }
            }
            // index 는 증가시켜 다음 음식을 가리킨다.
            index++;
            // n은 감소시켜 음식의 수를 하나씩 줄인다.(음식을 시간 안에 먹었음을 표현한다.)
            n--;
        }

        // 위에 과정을 무사히 마쳐서 여기까지 온 것이라면 음식을 시간 안에 다 섭취했다는 것이고
        // 재개되어도 더 이상 먹을 음식이 없다는 것을 의미한다. 따라서 섭취해야 할 음식이 없으므로 -1을 반환한다.
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
