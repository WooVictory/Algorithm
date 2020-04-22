package 카카오20;

import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/04/22
 */
public class Trie4_Re {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        solution(words, queries);
    }

    public static int[] solution(String[] words, String[] queries) {
        // 문제에서 문자열의 최대 길이가 10,000이기 때문에 Trie 배열의 크기를 10001으로 잡는다.
        Trie[] tries = new Trie[10001];
        for (String word : words) {
            int len = word.length();
            // 해당 단어의 길이를 인덱스로 가지도록 trie 를 만든다.
            if (tries[len] == null) tries[len] = new Trie();
            // 단어의 길이를 index 로 가지는 trie 에 단어를 insert 한다.
            tries[len].insert(word);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // 검색한 쿼리의 길이랑 같은 trie 가 있는지 확인한다.
            // 없다면, 쿼리와 같은 길이의 가사가 없다는 걸 뜻한다.
            int len = queries[i].length();
            if (tries[len] == null) answer[i] = 0;
            else answer[i] = tries[len].getCount(queries[i]);
        }

        for (int a : answer) System.out.print(a + " ");
        return answer;
    }

    // Trie 자료구조.
    // ??가 앞에 올 수도, 뒤에 올 수도 있기 때문에 가사 단어를 저장할 때, 앞과 뒤 두 부분으로 나누어 저장.
    static class Trie {
        Node front;
        Node back;

        Trie() {
            this.front = new Node();
            this.back = new Node();
        }

        void insert(String word) {
            insertFront(word);
            insertBack(word);
        }

        void insertFront(String word) {
            Node thisNode = this.front;
            for (int i = 0; i < word.length(); i++) {
                thisNode.increaseCount();
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new Node());
            }
        }

        void insertBack(String word) {
            Node thisNode = back;
            for (int i = word.length() - 1; i >= 0; i--) {
                thisNode.count++;
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

    // 노드들은 '부모 노드'나 '자신이 어떤 알파벳(key)에 해당하는 노드(Value)인지'를 가지고 있는게 아니다.
    // 이 노드 객체는 Character 값을 key 값으로 하는 자식 노드를 갖고 있을 뿐이다.
    // count : Character 값을 Key 로 하는 자식 노드의 갯수를 가지고 있다.
    static class Node {
        private Map<Character, Node> childNodes;
        private int count; // 해당 노드 아래로 몇 개의 자식 노드를 갖는지 count 값으로 표현한다.

        Node() {
            this.childNodes = new HashMap<>();
            this.count = 0;
        }

        public Map<Character, Node> getChildNodes() {
            return childNodes;
        }

        public int getCount() {
            return count;
        }

        void increaseCount() {
            this.count++;
        }
    }
}
