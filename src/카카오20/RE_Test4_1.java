package 카카오20;

import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 가사 검색.
 */
public class RE_Test4_1 {
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
            String query = queries[i];
            int len = query.length();
            if (tries[len] == null) answer[i] = 0;
            else answer[i] = tries[len].getCount(query);
        }

        return answer;
    }

    // 트라이 자료구조.
    // 와일드 문자인 '?'이 앞, 뒤에 존재하기 때문에
    // 두 가지 경우를 처리하기 위해서 front, back 2가지로 나누어서 관리한다.
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
                // 해당 노드에 연결된 자식 노드의 개수를 카운트 한다.
                // 따라서 해당 단어를 Key 값으로 갖는 자식 노드의 개수이다.
                thisNode.increaseCount();
                // 이 노드의 자식 노드 중에서 단어의 문자를 Key 값으로 하는 노드가 있는지 찾는다.
                // 없다면, 람다를 실행해서 그 문자를 Key 값으로 하는 자식 노드를 생성한다.
                // 있다면, 람다를 실행하지 않고 그 자식 노드를 반환한다.
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new Node());
            }
        }

        // 단어를 거꾸로 뒤집어서 넣는다.
        private void insertBack(String word) {
            Node thisNode = this.back;
            for (int i = word.length() - 1; i >= 0; i--) {
                thisNode.increaseCount();
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new Node());
            }
        }

        // 쿼리와 매칭되는 단어의 개수를 찾는다.
        // ? 와일드 문자가 앞에 나타나면 back 에서 찾는다.
        // ? 와일드 문자가 뒤에 나타나면 front 에서 찾는다.
        int getCount(String query) {
            if (query.charAt(0) == '?') return getCountFromBack(query);
            else return getCountFromFront(query);
        }

        // 쿼리와 매칭되는 단어의 개수를 찾는다.
        private int getCountFromFront(String query) {
            Node thisNode = this.front;
            for (int i = 0; i < query.length(); i++) {
                // 와일드 문자를 만나기 전까지 찾는다.
                char c = query.charAt(i);
                if (c == '?') break;
                // 이 노드의 자식 노드가 쿼리의 문자를 Key 값으로 가지고 있지 않으면 쿼리는 가사와 매칭되지 않음을 뜻한다.
                if (!thisNode.getChildNodes().containsKey(c)) return 0;
                // 그게 아니라면 이 노드의 자식 노드 중에서 쿼리의 문자를 key 로 하는 자식 노드를 꺼낸다.
                thisNode = thisNode.getChildNodes().get(c);
            }
            // 이 노드의 카운트를 반환한다.
            // 이는 이 노드에서 어떤 문자를 Key 값으로 갖는 자식 노드의 개수이다.
            // 따라서 쿼리와 매칭되는 단어의 개수를 뜻한다.
            return thisNode.getCount();
        }

        private int getCountFromBack(String query) {
            Node thisNode = this.back;
            for (int i = query.length() - 1; i >= 0; i--) {
                char c = query.charAt(i);
                if (c == '?') break;
                if (!thisNode.getChildNodes().containsKey(c)) return 0;
                thisNode = thisNode.getChildNodes().get(c);
            }
            return thisNode.getCount();
        }
    }

    // Node : 해당 노드가 얼마나 등장했는지 표현하는 count. 어떠한 문자를 Key 값으로 하는 자식 노드를 갖고 있다.
    // 자신이 어떤 문자에 해당하는지에 대한 정보를 갖고 있지 않다.
    static class Node {
        private int count;
        private Map<Character, Node> childNodes;

        Node() {
            count = 0;
            childNodes = new HashMap<>();
        }

        public int getCount() {
            return count;
        }

        Map<Character, Node> getChildNodes() {
            return childNodes;
        }

        void increaseCount() {
            this.count++;
        }
    }
}
