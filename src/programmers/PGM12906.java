package programmers;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/20
 * 같은 숫자는 싫어.
 * 정답은 나오는데, 테케도 다 맞음. 근데 효율성 검사에서 틀림.
 */
public class PGM12906 {
    public static void main(String[] args) {
        int[] a = {4, 4, 4, 3, 3};
        int[] a2 = {1, 1, 3, 3, 0, 1, 1};
        System.out.println(solution(a2).length);
    }

    /*public static int[] solution(int[] arr) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != list.getLast()) list.add(arr[i]);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);

        return answer;
    }*/

    // 아래처럼 풀면 효율성 측면에서 괜찮음.
    public static int[] solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int current = 10;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != current) {
                list.add(arr[i]);
                current = arr[i];
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);

        return answer;
    }

}
