package programmers;

import java.util.HashSet;
import java.util.Set;

/**
 * created by victory_woo on 2020/05/28
 * 영어 끝말잇기.
 */
public class PGM12981 {
    public static void main(String[] args) {
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        System.out.println(solution(3, words));

        /*String[] words2 = {"hello", "one", "even", "never", "now", "world", "draw"};
        System.out.println(solution(2, words2));*/
    }

    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        int turn = 1; // 차례는 첫 번째부터 시작.
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            index++;

            // i가 0보다 큰 경우에 대해서만 현재 단어와 이전 단어를 비교할 수 있음.
            // 이전 단어의 마지막 글자와 현재 단어의 첫 번째 글자가 다른 경우, 그 사람의 번호와 차례를 저장한다!
            if (i > 0) {
                String a = words[i - 1], b = words[i];
                if (a.charAt(a.length() - 1) != b.charAt(0)) {
                    answer[0] = index;
                    answer[1] = turn;
                    break;
                }
            }

            // 혹시 이미 저장된 단어가 나오면 그 사람은 탈락자이므로 그 사람의 번호와 차례를 저장한다.
            if (set.contains(words[i])) {
                answer[0] = index;
                answer[1] = turn;
                break;
            }

            // 해당 단어를 저장한다.
            set.add(words[i]);

            // n명 까지 말했으면 다음 차례가 되기 때문에 turn 값을 증가시킨다.
            if (index == n) {
                index = 0;
                turn++;
            }
        }

        for (int i : answer) System.out.println(i);
        return answer;
    }
}
