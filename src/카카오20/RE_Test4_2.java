package 카카오20;

import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 가사 검색.
 */
public class RE_Test4_2 {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        solution(words, queries);
    }

    public static int[] solution(String[] words, String[] queries) {
        Trie[] tries = new Trie[10001];

        for (String word : words) {
            int len = word.length();
            if (tries[len] == null) tries[len] = new Trie();
            tries[len].insert(word);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if (tries[len] == null) answer[i] = 0;
            else answer[i] = tries[len].getCount(queries[i]);
        }

        for (int a : answer) System.out.println(a + " ");

        return answer;
    }

    static class Trie {
        Node front;
        Node back;

        Trie() {
            front = new Node();
            back = new Node();
        }

        void insert(String word) {
            insertFront(word);
            insertBack(word);
        }

        private void insertFront(String word) {
            Node thisNode = this.front;
            for (int i = 0; i < word.length(); i++) {
                thisNode.increaseCount();
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new Node());
            }
        }

        private void insertBack(String word) {
            Node thisNode = this.back;
            for (int i = word.length() - 1; i >= 0; i--) {
                thisNode.increaseCount();
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new Node());
            }
        }

        int getCount(String query) {
            if (query.charAt(0) == '?') return getCountFromBack(query);
            else return getCountFromFront(query);
        }

        private int getCountFromFront(String query) {
            Node thisNode = this.front;
            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) == '?') break;
                if (!thisNode.getChildNodes().containsKey(query.charAt(i))) return 0;
                thisNode = thisNode.getChildNodes().get(query.charAt(i));
            }
            return thisNode.getCount();
        }

        private int getCountFromBack(String query) {
            Node thisNode = this.back;
            for (int i = query.length() - 1; i >= 0; i--) {
                if (query.charAt(i) == '?') break;
                if (!thisNode.getChildNodes().containsKey(query.charAt(i))) return 0;
                thisNode = thisNode.getChildNodes().get(query.charAt(i));
            }
            return thisNode.getCount();
        }

    }

    static class Node {
        int count;
        Map<Character, Node> childNodes;

        Node() {
            count = 0;
            childNodes = new HashMap<>();
        }

        public int getCount() {
            return count;
        }

        public Map<Character, Node> getChildNodes() {
            return childNodes;
        }

        void increaseCount() {
            this.count++;
        }
    }
}
