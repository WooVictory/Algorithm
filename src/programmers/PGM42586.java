package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by victory_woo on 2020/03/26
 * 기능 개발.
 * 스택, 큐를 사용하는 문제.
 */
public class PGM42586 {
    public static void main(String[] args) {
        solution(new int[]{93, 30, 55}, new int[]{1, 30, 5});
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        LinkedList<Integer> days = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int day = 0;

            // 현재 작업의 진도에 개발 속도를 더해 100보다 커질 때까지 돌면서 몇일이 걸리는 지 구한다.
            while (progresses[i] < 100) {
                progresses[i] += speeds[i];
                day++;
            }
            // 구해진 개발 기간을 days 에 추가한다.
            System.out.println(day);
            days.add(day);
        }

        List<Integer> result = new ArrayList<>();
        int max = days.get(0);
        int count = 1;

        // 첫번째에 개발 완료한 것은 배포할 수 있다.
        // 따라서 뒤에 있는 것 중에서 첫번째 배포 기간보다 작은 것들은 함께 배포할 수 있으므로 카운팅한다.
        // 첫번째 배포 기간보다 긴 배포 기간을 가진 기능이 나오면 이전까지 카운팅한 값을 result 리스트에 추가한다.
        // 그리고 긴 기능이 나왔기 때문에 count 값을 1로 초기화한다.
        // 또한, 긴 값이기 때문에 max 값도 갱신한다.
        for (int i = 1; i < days.size(); i++) {
            if (days.get(i) <= max) {
                count++;
            } else {
                result.add(count);
                count = 1;
                max = days.get(i);
            }

            // 만약, 뒤쪽에 해당 기능보다 더 긴 기간이 없이 끝난다면 이 기능은 배포할 리스트에 마지막으로 넣어준다.
            if (i == days.size() - 1) result.add(count);
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
            System.out.println(answer[i]);
        }
        return answer;
    }
}
