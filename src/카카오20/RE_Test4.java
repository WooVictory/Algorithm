package 카카오20;

import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/05/08
 * 카카오 20 기출.
 * 다시 푸는 중.
 * 가사 검색.
 */
public class RE_Test4 {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        solution(words, queries);
    }

    public static int[] solution(String[] words, String[] queries) {
        Trie[] tries = new Trie[100001];

        // 1. 해당 단어의 길이를 인덱스로 가지도록 trie 를 만든다.
        // 같은 단어 길이인 경우, 동일한 트라이가 된다.
        for (String word : words) {
            int length = word.length();
            if (tries[length] == null) tries[length] = new Trie();
            // 단어 길이를 인덱스로 가지는 trie 에 단어를 추가한다.
            tries[length].insert(word);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int length = queries[i].length();
            if (tries[length] == null) answer[i] = 0;
            else answer[i] = tries[length].getCount(queries[i]);
        }

        for (int a : answer) System.out.println(a);

        return answer;

    }


    // 와일드 문자가 앞에서 시작하는 것과 뒤에서 시작하는 것이 있기 때문에
    // front, back 2개의 노드를 갖는다.
    // front : 단어를 앞에서부터 저장.
    // back : 단어를 뒤에서부터 저장.
    static class Trie {
        Node front;
        Node back;

        Trie() {
            front = new Node();
            back = new Node();
        }

        // 단어를 노드에 추가한다.
        void insert(String word) {
            insertFront(word);
            insertBack(word);
        }

        // 문자로 쪼개서 자식 노드에 계속 추가한다.
        private void insertFront(String word) {
            Node thisNode = this.front;
            char key;
            for (int i = 0; i < word.length(); i++) {
                key = word.charAt(i);
                thisNode.count++;
                thisNode = thisNode.childNodes.computeIfAbsent(key, character -> new Node());
            }
        }

        // 문자로 쪼개서 단어의 뒤에서부터 문자를 노드에 추가한다.
        private void insertBack(String word) {
            Node thisNode = this.back;
            char key;
            for (int i = word.length() - 1; i >= 0; i--) {
                key = word.charAt(i);
                thisNode.count++;
                thisNode = thisNode.childNodes.computeIfAbsent(key, c -> new Node());
            }
        }

        // 와일드 카드 문자의 위치에 따라 어느 노드에서 카운트할지 정한다.
        int getCount(String query) {
            if (query.charAt(0) == '?') return getCountFromBack(query);
            else return getCountFromFront(query);
        }

        private int getCountFromBack(String query) {
            Node thisNode = this.back;
            for (int i = query.length() - 1; i >= 0; i--) {
                if (query.charAt(i) == '?') break;
                if (!thisNode.childNodes.containsKey(query.charAt(i))) return 0;
                thisNode = thisNode.childNodes.get(query.charAt(i));
            }
            return thisNode.count;
        }

        private int getCountFromFront(String query) {
            Node thisNode = this.front;
            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) == '?') break;
                if (!thisNode.childNodes.containsKey(query.charAt(i))) return 0;
                thisNode = thisNode.childNodes.get(query.charAt(i));
            }
            return thisNode.count;
        }
    }

    static class Node {
        int count;
        Map<Character, Node> childNodes;

        Node() {
            count = 0;
            childNodes = new HashMap<>();
        }
    }
}
