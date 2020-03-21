package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/03/21
 * 나누어 떨어지는 숫자 배열.
 * 나누어 떨어지면 리스트에 넣고, 아니면 안 넣고
 * 하나도 없으면 배열에 -1을 넣어서 반환하면 된다.
 */
public class PGM12910 {
    public static void main(String[] args) {
        int[] arr = {5, 9, 7, 10};
        int[] arr2 = {2, 36, 1, 3};
        int[] arr3 = {3, 2, 6};
        int divisor = 5;
        int divisor2 = 1;
        int divisor3 = 10;
        System.out.println(solution(arr3, divisor3));
    }

    public static int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int a : arr) {
            if (a % divisor == 0) list.add(a);
        }

        int[] answer;
        if (list.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        } else {
            answer = new int[list.size()];
            Collections.sort(list);
            for (int i = 0; i < answer.length; i++) answer[i] = list.get(i);
            return answer;
        }
    }
}
