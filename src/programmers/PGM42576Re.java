package programmers;

import java.util.Arrays;
import java.util.HashMap;

/**
 * created by victory_woo on 2020/03/29
 * 완주하지 못한 선수.
 */
public class PGM42576Re {
    public static void main(String[] args) {
        System.out.println(solution2(new String[]{"leo", "kiki", "eden"}, new String[]{"kiki", "eden"}));
    }

    public static String solution(String[] participant, String[] completion) {
        // 참가자와 완주자 모두 정렬하여 같은 순서를 가지도록 한다.
        Arrays.sort(participant);
        Arrays.sort(completion);

        String fail = "";
        // 완주자의 숫자만큼 반복문을 돌면서 참가자와 같다면 위로 올라가서 반복문을 반복한다.
        for (int i = 0; i < completion.length; i++) {
            if (participant[i].equals(completion[i])) {
                continue;
            } else {
                // 그렇지 않은 경우, fail 에 완주하지 못한 참가자를 저장한다.
                // 그리고 빠져나온다. 왜냐하면 한명만 완주하지 못하기 때문!
                fail = participant[i];
                break;
            }
        }

        // 혹여나 fail 변수에 완주하지 못한 선수가 저장되지 않을 수 있는데,
        // 이 경우는 정렬을 통해서 제일 마지막에 위치했을 경우이다.
        // 따라서 맨 마지막 값을 반환한다. 그렇지 않다면 fail 값을 반환한다.
        if (fail.equals("")) {
            return participant[participant.length - 1];
        } else {
            return fail;
        }
    }

    public static String solution2(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        for (String people : participant) map.put(people, map.getOrDefault(people, 0) + 1);
        for (String people : completion) map.put(people, map.get(people) - 1);

        for (String key : map.keySet()) {
            if (map.get(key) != 0) answer = key;
        }

        return answer;

    }
}
