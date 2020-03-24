package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/03/24
 * 모의고사.
 * 완전 탐색 문제이다.
 * 수포자 3명을 만들고, answers 배열을 입력받았을 때, 각각의 수포자를 뽑아서 문제에 해당하는 답과 일치하는지 확인한다.
 * 그리고 count > 0 일 때만 list 에 추가하여 이를 answer 배열에 담아 반환한다.
 * 5개 정도의 테케만 맞고 나머지는 틀린 결과를 보여준다. 왜일까?
 *
 *
 */
public class PGM42840 {
    public static void main(String[] args) {
        solution(new int[]{1, 2, 3, 4, 5});
        solution(new int[]{1, 3, 2, 4, 2});
        //solution(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

    }

    public static int[] solution(int[] answers) {
        int[][] person = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        ArrayList<Integer> list = new ArrayList<>();

        // 위에서 정의한 각 수포자가 찍는 답과 문제의 정답을 비교해서 count 를 증가시킨다.
        // 그리고 list 에 추가해준다.
        for (int i = 0; i < 3; i++) {
            int[] p = person[i];
            int len = p.length;
            int count = 0;
            for (int j = 0; j < answers.length; j++) {
                int num = j % len;
                if (answers[j] == p[num]) count++;
            }

            list.add(count);
        }

        // 가장 많이 정답을 맞춘 count 값을 max 값으로 뽑는다.
        int max = Collections.max(list);

        ArrayList<Integer> result = new ArrayList<>();

        // 기존의 list 에서 max 값과 같은 값이 있다면 result 에 index 를 추가한다.
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == max) {
                result.add(i);
            }
        }

        int[] answer = new int[result.size()];

        // result 크기만큼 배열을 만들어서 result 에서 뽑은 값을 answer 배열에 추가한다.
        // 대신, index 값이기 때문에 +1을 해준다.
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i) + 1;
            System.out.println(answer[i]);
        }
        return answer;
    }
}
