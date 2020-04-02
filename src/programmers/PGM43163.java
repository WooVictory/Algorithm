package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/01
 * 단어 변환.
 * dfs/bfs
 */
public class PGM43163 {
    private static int result = 0;

    public static void main(String[] args) {
        String[] s = {"hot", "dot", "dog", "lot", "log", "cog"};
        String[] s2 = {"hot", "dot", "dog", "lot", "log",};
        System.out.println(solution("hit", "cog", s));
        System.out.println(solution("hit", "cog", s2));
    }

    public static int solution(String begin, String target, String[] words) {
        // 처음에 예외를 처리한다.
        if (!check(target, words)) return 0;

        bfs(begin, target, words);

        return result;

    }

    // words 배열이 target 문자열을 가지고 있지 않게 되면 false 가 되며, 0을 반환한다.
    public static boolean check(String target, String[] words) {
        boolean flag = false;
        for (String word : words) {
            if (word.equals(target)) flag = true;
        }
        return flag;
    }

    public static void bfs(String begin, String target, String[] words) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));

        int n = words.length;
        // words 배열에 대한 방문 여부를 체크한다. 비교하는 데 사용된 단어는 다시 사용하지 않기 위해서!
        boolean[] visit = new boolean[n];

        while (!q.isEmpty()) {
            Node cur = q.remove();
            // 큐에서 꺼낸 단어가 target 과 일치하면 count 값을 result 변수에 저장하고 return.
            if (cur.str.equals(target)) {
                result = cur.count;
                return;
            }

            // 0 ~ n 까지 반복하면서 words 배열에서 하나씩 꺼내면서 방문 여부와 단어의 다름 정도가 1인 경우
            // 방문 여부를 체크하고 큐에 넣는다. 큐에 넣을 때, count 값을 이전 카운트 값 기준으로 1 증가시킨다.
            for (int i = 0; i < n; i++) {
                String word = words[i];
                if (!visit[i] && calculateDiff(cur.str, word)) {
                    visit[i] = true;
                    q.add(new Node(word, cur.count + 1));
                }
            }
        }
    }

    private static boolean calculateDiff(String cur, String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (cur.charAt(i) != word.charAt(i)) count++;
        }

        return count == 1;
    }

    static class Node {
        String str;
        int count;

        Node(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
