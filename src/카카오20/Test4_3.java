package 카카오20;

import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/04/23
 * 카카오 20 기출.
 * 가사 검색.
 * 트라이 자료구조.
 */
public class Test4_3 {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        solution(words, queries);
    }

    public static int[] solution(String[] words, String[] queries) {
        Trie[] tries = new Trie[10001]; // 검색 단어의 최대 길이가 10,000이다.
        for (String word : words) {
            int length = word.length();
            // 해당 단어의 길이를 인덱스로 가지도록 trie 를 만든다.
            if (tries[length] == null) tries[length] = new Trie();
            // 단어의 길이를 index 로 가지는 trie 에 단어를 insert 한다.
            tries[length].insert(word);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // 검색한 쿼리의 길이랑 같은 trie 가 있는지 확인한다.
            // 없다면, 쿼리와 같은 길이의 가사가 없다는 걸 뜻한다.
            int length = queries[i].length();
            if (tries[length] == null) answer[i] = 0;
            else answer[i] = tries[length].getCount(queries[i]);
        }

        for (int a : answer) System.out.println(a);
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

        // ??가 앞이나 뒤에 등장할 수 있기 때문에
        // word 문자열에 대해서 순차적으로 저장하는 트라이와, 반대로 저장하는 트라이 2개를 갖는다.
        void insert(String word) {
            insertFront(word);
            insertBack(word);
        }

        private void insertFront(String word) {
            Node thisNode = this.front;
            for (int i = 0; i < word.length(); i++) {
                thisNode.increaseCount(); // 해당 문자 노드의 등장 횟수를 증가시킨다.
                // 문자열의 한 문자를 Key 로 하는 자식 노드가 없다면 새롭게 자식 노드를 생성한다.
                // thisNode 의 childNode 중에서 word.charAt(i) 문자를 key 값으로 하는 노드가 있는지 확인한다.
                // 없으면 해당 문자를 key 값으로 하는 자식 노드를 만든다.
                // 있으면 새로운 노드를 만드는 람다식을 수행하지 않고,
                // 해당 문자를 key 로 하는 자식 노드를 반환할 뿐이다.
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

        // ?가 등장하는 위치가 앞인지 뒤인지 확인한다.
        // ?가 앞에 등장한다면 back 에서 count 개수를 가져온다.
        // ?가 뒤에 등장한다면 front 에서 count 개수를 가져온다.
        int getCount(String query) {
            if (query.charAt(0) == '?') return getCountFromBack(query);
            else return getCountFromFront(query);
        }

        // 이 경우는 와일드 카드 문자인 ?? 가 뒤쪽에 있으므로, 쿼리의 앞에서부터 반복문을 시작한다.
        // 그리고 각 문자를 비교하며, 문자가 ?를 만나면 종료한다.
        // 또한, thisNode 의 childNode 중에서 쿼리의 문자를 key 로 하여 자식 노드를 가지는 노드가 하나도 없으면 0을 반환한다.
        private int getCountFromFront(String query) {
            Node thisNode = this.front;
            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) == '?') break;
                if (!thisNode.getChildNodes().containsKey(query.charAt(i))) return 0;
                thisNode = thisNode.getChildNodes().get(query.charAt(i));
            }
            return thisNode.getCount();
        }

        // ??가 앞에 있으므로 쿼리에 뒤에서부터 반복문을 시작한다.
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
        private Map<Character, Node> childNodes;
        private int count;

        Node() {
            this.childNodes = new HashMap<>();
            this.count = 0;
        }

        Map<Character, Node> getChildNodes() {
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