package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/05/13
 * 구명보트.
 */
public class PGM42885 {
    public static void main(String[] args) {
        //System.out.println(solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(solution(new int[]{70, 80, 50}, 100));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        // 오름차순으로 정렬한다.
        Arrays.sort(people);

        int start = 0;
        int end;

        // 가장 몸무게가 적게 나가는 사람과 가장 몸무게가 많이 나가는 사람의 몸무게 합산을 limit 와 비교한다.
        for (end = people.length - 1; end > start; end--) {
            // 두 사람의 몸무게 합이 limit 보다 작거나 같으면 두 사람을 보트에 태울 수 있다.
            // 보트 수를 증가시키고, 몸무게가 적은 사람도 탔으므로 인덱스를 증가시킨다.
            if (people[start] + people[end] <= limit) {
                start++;
                answer++;
            } else {
                // 두 사람의 몸무게 합이 limit 보다 크다면 한 사람만을 보트에 태울 수 있으며
                // 이때는 몸무게가 큰 사람만 태우므로 작은 사람을 태울 수 없다. 따라서 인덱스는 증가시키지 않는다.
                answer++;
            }
        }

        // start == end 인 경우는 타지 못한 마지막 한 사람이 있는 경우이다
        // 따라서 마지막으로 이 한명을 태우면 된다.
        if (start == end) answer++;

        return answer;
    }
}
