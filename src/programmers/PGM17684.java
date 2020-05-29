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

        // 1. A ~ Z까지 미리 넣는다.
        ArrayList<String> alphabets = new ArrayList<>();
        int index = 65;
        while (index <= 90) {
            char c = (char) index;
            alphabets.add(String.valueOf(c));
            index++;
        }

        ArrayList<Integer> result = new ArrayList<>();
        String[] words = msg.split("");
        String now = words[0];
        for (int i = 1; i < words.length; i++) {
            String next = words[i];

            if (!alphabets.contains(now + next)) {
                result.add((alphabets.indexOf(now)) + 1); // 현재 인덱스를 넣고.
                alphabets.add(now + next); // 현재와 다음 문자열을 이어붙인 문자열을 사전에 추가한다.
                now = next;
            } else {
                now = now + next;
            }
        }

        result.add((alphabets.indexOf(now)) + 1);
        System.out.println(result);

        answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = result.get(i);

        return answer;
    }
}
