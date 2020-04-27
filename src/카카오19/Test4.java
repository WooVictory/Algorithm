package 카카오19;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * created by victory_woo on 2020/04/25
 * 카카오 19 기출.
 * 무지의 먹방 라이브.
 */
public class Test4 {
    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        System.out.println(solution(food_times, 5));
    }

    // 시간 순서대로 오름차순으로 정렬한다.
    private static Comparator<Food> compareTime = new Comparator<Food>() {
        @Override
        public int compare(Food a, Food b) {
            return Integer.compare(a.time, b.time);
        }
    };

    // 음식 순서대로 정렬하는 것도 필요하다. (오름차순.)
    private static Comparator<Food> compareFoodIndex = new Comparator<Food>() {
        @Override
        public int compare(Food a, Food b) {
            return Integer.compare(a.index, b.index);
        }
    };

    public static int solution(int[] food_times, long k) {
        List<Food> foods = new LinkedList<>();
        int n = food_times.length;

        // 음식의 순서와 시간을 묶어서 foods 리스트에 저장한다.
        for (int i = 0; i < n; i++) {
            foods.add(new Food(i + 1, food_times[i]));
        }

        foods.sort(compareTime);
        int preTime = 0; // diff 값을 구하기 위한 이전 시간.
        int index = 0; // 몇 번째를 처리하고 있는지 인덱스.
        for (Food food : foods) {
            long diff = food.time - preTime;
            if (diff != 0) {
                // 쓸 수 있는 시간.
                long spend = diff * n;

                // 쓸 수 있는 시간이 k 이하라면 뺄 수 있다.
                // preTime 은 현재 시간으로 업데이트 해준다.
                if (spend <= k) {
                    k -= spend;
                    preTime = food.time;
                } else {
                    // 아니라고 하면 순회를 중단하고 답을 구해야 한다.
                    // 나머지 연산을 해서 위치를 구한다.
                    k = k % n;

                    // 남아 있는 음식들을 원래 음식 순서대로 정렬해야 한다. 여기서 전부 다 정렬할 필요는 없고
                    // 남아 있는 음식.
                    // 즉, 현재 위치에서 끝까지 정렬하면 된다.
                    // n을 사용하지 않는 이유는 n을 계속해서 업데이트할 것이기 때문이다.
                    // 하나씩 줄여가면서 남아있는 음식 수로 사용할 것이기 때문에!!
                    foods.subList(index, food_times.length).sort(compareFoodIndex);

                    // 현재 위치에서 바뀐 k만큼 뒤의 값을 반환한다.
                    return foods.get(index + (int) k).index;
                }
            }


            // 인덱스를 증가시키고, 남아있는 음식을 하나씩 처리하기 때문에 줄이면 된다.
            index++;
            n--;
        }

        // k라는 시간이 충분해서 for 문 동안 음식을 다 먹고
        // for 문 밖으로 나올 수 있으면 k초 후에 먹을 음식이 없기 때문에 -1을 반환하면 된다.
        return -1;
    }

    static class Food {
        int index; // 음식의 순서.
        int time; // 음식을 다 먹는데 걸리는 시간.

        Food(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
