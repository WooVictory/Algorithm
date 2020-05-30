package programmers;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/05/29
 * 카카오 18 3차 압축.
 */
public class PGM17684 {
    public static void main(String[] args) {
        //solution("KAKAO");
        //solution("TOBEORNOTTOBEORTOBEORNOT");
        solution("ABABABABABABABAB");
    }

    public static int[] solution(String msg) {
        int[] answer;

        // 1. A ~ Z까지 미리 넣는다. 사전으로써 단어의 색인을 저장할 리스트.
        ArrayList<String> alphabets = new ArrayList<>();
        int index = 65;
        while (index <= 90) {
            char c = (char) index;
            alphabets.add(String.valueOf(c));
            index++;
        }

        // 출력할 단어의 인덱스를 저장하는 리스트.
        ArrayList<Integer> result = new ArrayList<>();
        String[] words = msg.split("");

        // 첫 글자를 먼저 담아서 시작한다.
        String now = words[0];
        // 다음 글자부터 for 반복문을 진행한다.
        for (int i = 1; i < words.length; i++) {
            // 다음 글자를 얻는다.
            String next = words[i];

            // now+next 문자열이 리스트에 존재하는지 확인한다.

            // now+next 문자열이 존재하는 않는 경우.
            // 현재 문자열인 now 의 인덱스를 result 에 추가한다.
            // 사전에 now+next 문자열을 추가한다.
            // next 는 다음 문자열과 비교할 때, now 로 사용한다.
            if (!alphabets.contains(now + next)) {
                result.add((alphabets.indexOf(now)) + 1); // 현재 인덱스를 넣고.
                alphabets.add(now + next); // 현재와 다음 문자열을 이어붙인 문자열을 사전에 추가한다.
                now = next;
            } else {
                // now+next 문자열이 존재하는 경우.
                // now 에 결합한 문자열을 놓고, 다음 next 를 다시 붙여서
                // 사전에 존재하는지 확인하러 간다.
                now = now + next;
            }
        }

        // 마지막으로 처리되지 않은 now 의 인덱스를 result 에 추가한다.
        result.add((alphabets.indexOf(now)) + 1);

        // 리스트를 배열에 담아서 반환한다.
        answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = result.get(i);

        return answer;
    }
}
