package 카카오19;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 무지의 먹방 라이브.
 */
public class RE_Test3_1 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2}, 5));
    }

    // 시간순으로 오름차순 정렬.
    private static Comparator<Food> compareTime = (a, b) -> a.time - b.time;
    private static Comparator<Food> compareIndex = (a, b) -> a.index - b.index;

    public static int solution(int[] food_times, long k) {
        int len = food_times.length, n = len;
        ArrayList<Food> foods = new ArrayList<>();

        // 1. 배열을 foods 리스트에 옮겨 담는다.
        for (int i = 0; i < len; i++) foods.add(new Food(i + 1, food_times[i]));

        // 2. 시간순으로 오름차순 정렬하여, 음식을 먹는데 소요되는 시간이 작은 것부터 처리하기 위한 준비를 한다.
        foods.sort(compareTime);


        int preTime = 0, index = 0;
        // 3. foods 리스트를 순회하면서 한번에 시간을 써서 처리할 수 있는지 아닌지 확인한다.
        for (Food food : foods) {
            long diff = food.time - preTime;
            // 차이가 존재하는 경우에만 처리한다.
            // 차이가 없다면 건너뛴다.
            if (diff != 0) {
                // 한 번에 처리할 수 있는 시간을 구한다.
                long spendTime = diff * n;
                // 한 번에 처리할 수 있는 시간이 k보다 작거나 같다면 한 번에 처리할 수 있음을 의미한다.
                if (spendTime <= k) {
                    k -= spendTime;
                    preTime = food.time;
                } else {
                    // 한 번에 처리할 수 없다면 순회를 중단하고
                    // list 를 현재 위치부터 끝까지 자른 뒤, 인덱스 기반으로 오름차순 정렬한다.
                    // 그리고 다음에 무지가 먹어야 하는 음식의 위치를 % 연산을 통해 구한다.
                    k %= n;
                    foods.subList(index, len).sort(compareIndex);
                    // 현재 위치(index) + k(먹어야 할 위치)를 통해 다음에 먹어야 하는 곳의 위치를 반환한다.
                    return foods.get(index + (int) k).index;
                }
            }
            // 현재 위치를 증가시킨다.
            index++;
            // 남은 음식의 수를 감소시킨다.
            n--;
        }
        // 더 섭취해야 할 음식이 없다면 -1을 반환한다.
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
