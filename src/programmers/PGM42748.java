package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/03/22
 * k번째 수.
 * commands 2차원 배열에서 i,j,k를 뽑아서 범위 안에 있는 요소를 뽑아서
 * list 에 넣어주고 정렬한 뒤, k번째 수를 answer 배열에 넣어준다.
 */
public class PGM42748 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        solution(array, commands);
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        ArrayList<Integer> tmp;
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0], end = commands[i][1], k = commands[i][2];
            tmp = new ArrayList<>();
            for (int l = start - 1; l <= end - 1; l++) {
                tmp.add(array[l]);
                answer[i] = array[l];
            }

            Collections.sort(tmp);
            answer[i] = tmp.get(k - 1);
        }

        return answer;
    }
}
