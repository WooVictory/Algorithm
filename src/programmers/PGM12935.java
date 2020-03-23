package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/03/23
 * 작은 수 제거하기.
 * 배열을 리스트에 넣은 뒤, 가장 작은 값을 찾는다.
 * 이에 대해 인덱스를 구해서 제거한다.
 * 제거된 리스트의 사이즈를 기반으로 배열을 만들고
 * 그 배열에 원소를 하나씩 넣어준다.
 */
public class PGM12935 {
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        int[] arr2 = {10};
        System.out.println(solution(arr));
    }

    public static int[] solution(int[] arr) {
        int[] answer;
        if (arr.length == 1) {
            return new int[]{-1};
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            for (int a : arr) list.add(a);

            int min = Collections.min(list);
            int index = list.indexOf(min);
            list.remove(index);

            answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);

            return answer;
        }
    }
}
