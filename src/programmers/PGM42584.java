package programmers;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/03/26
 * 주식 가격.
 * 내가 푼 풀이는 공개된 테케에 대해서만 정답을 맞혔다.
 * 그리고 효율성 부분에서도 하나도 맞지 못했다.
 * 다른 사람의 풀이를 보니 List, Stack 같은 자료구조를 사용하지 않고 순수한 배열로만 풀이를 이어나갔다.
 *
 * money 라는 변수로 배열의 갯수 - 1만큼에서 기준이 되는 수가 커질 때 money 값을 감소시킨다.
 * 그리고 또 list 를 사용하는데, 굳이 사용하지 않아도 되는 부분이다.
 */
public class PGM42584 {
    public static void main(String[] args) {
        solution2(new int[]{1, 2, 3, 2, 3});
    }

    public static int[] solution(int[] prices) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            int standard = prices[i];
            int money = prices.length - (i + 1);
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] < standard) money--;
            }
            list.add(money);
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = list.get(i);
        for (int i : list) System.out.println(i);
        return answer;
    }

    public static int[] solution2(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int st = prices[i];
            int count = 0;
            // 해당 시점에서 가격은 1초 뒤에 떨어지더라도 떨어진 것으로 판단하지 않는다.
            // 따라서 1초간 가격이 떨어지지 않은 것으로 간주하기 때문에 먼저 count 값을 증가시킨다.
            // 그리고 st 가 prices[j]보다 크다는 것은 주식 가격이 떨어졌음을 의미한다.
            // 하지만, 해당 시점에서는 떨어진 것이 아니라 다음 초에 떨어지는 것이기 때문에 count 값은 먼저 증가시킨다.
            for (int j = i + 1; j < prices.length; j++) {
                count++;
                if (prices[j] < st) break;
            }

            // 그리고 answer 배열에 값을 넣어준다.
            answer[i] = count;
        }
        for (int a : answer) System.out.println(a);
        return answer;
    }
}
