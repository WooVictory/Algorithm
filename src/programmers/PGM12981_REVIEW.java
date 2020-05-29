package programmers;

import java.util.HashSet;

/**
 * created by victory_woo on 2020/05/28
 * 영어 끝말잇기.
 * 다시 풀기.
 */
public class PGM12981_REVIEW {
    public static void main(String[] args) {
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        solution(3, words);
    }

    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        // 단어를 관리하기 위한 Set : 중복을 허용하지 않음.
        HashSet<String> list = new HashSet<>();
        int index = 0, turn = 1;


        for (int i = 0; i < words.length; i++) {
            // 현재 단어를 말한 사람의 번호! (1부터 시작함)
            index++;

            // 0보다 큰 경우, 이전 단어와 비교할 수 있다.
            // 이전 단어의 마지막 글자와 현재 단어의 마지막 글자가 다르다면
            // 현재 수행 중인 사람은 탈락자!
            // 따라서 탈락자의 번호와 차례를 저장한 뒤, 종료!
            if (i > 0) {
                String a = words[i - 1], b = words[i];
                if (a.charAt(a.length() - 1) != b.charAt(0)) {
                    answer[0] = index;
                    answer[1] = turn;
                    break;
                }
            }

            // 해당 단어가 이미 등장한 적이 있으면 현재 사람의 번호와 차례를 저장한 뒤, 종료!
            if (list.contains(words[i])) {
                answer[0] = index;
                answer[1] = turn;
                break;
            }

            // 단어를 저장한다.
            list.add(words[i]);

            // n명이 다 수행했으면, 다시 처음부터 수행한다.
            // 그리고 다음 턴으로 증가시킨다.
            if (index == n) {
                index = 0;
                turn++;
            }
        }

        for (int num : answer) System.out.print(num + " ");
        return answer;
    }
}
