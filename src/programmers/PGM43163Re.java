package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/02
 * 단어 변환.
 * bfs/dfs.
 * 다시 푸는 중!
 */
public class PGM43163Re {
    private static int answer = 0, count = 0;

    public static void main(String[] args) {
        String[] s = {"hot", "dot", "dog", "lot", "log", "cog"};
        String[] s2 = {"hot", "dot", "dog", "lot", "log"};
        System.out.println(solution("hit", "cog", s2));
    }

    public static int solution(String begin, String target, String[] words) {
        bfs(begin, target, words);
        return answer;
    }

    private static void bfs(String begin, String target, String[] words) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));

        int n = words.length;
        boolean[] visit = new boolean[n];

        while (!q.isEmpty()) {
            Node current = q.remove();
            if (current.word.equals(target)) {
                answer = count;
                return;
            }

            for (int i = 0; i < n; i++) {
                if (!visit[i] && isPossible(current.word, words[i])) {
                    visit[i] = true;
                    q.add(new Node(words[i], current.count + 1));
                    count += 1;
                }
            }
        }
    }

    // 단어의 차이가 1이어야 변환할 수 있다. 따라서 true 반환.
    // 단어의 차이가 1보다 크다면 변환할 수 없으며, false 반환.
    // 두 단어의 차이를 센다.
    private static boolean isPossible(String current, String word) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != word.charAt(i)) count++;
        }

        return (count == 1);
    }

    static class Node {
        String word;
        int count;

        Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

}
