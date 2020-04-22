package 카카오20;

import java.util.Collections;
import java.util.HashSet;

/**
 * created by victory_woo on 2020/04/22
 * 카카오 20 기출.
 * 가사 검색.
 * Trie 자료 구조.
 */
public class Test4 {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        solution(words, queries);
    }

    public static int[] solution(String[] words, String[] queries) {
        HashSet<String> set = new HashSet<>();
        int[] result = new int[queries.length];
        Collections.addAll(set, words);

        int index = 0;
        for (String query : queries) {
            int count = 0;

            for (String word : set) {
                // 길이가 같은지 확인한다. 길이가 다르다면 아예 다름.
                if (word.length() == query.length()) count += process(query, word);
            }
            result[index++] = count;
        }

        for (int a : result) System.out.println(a);

        return result;
    }

    private static int process(String query, String word) {
        int index, count = 0;
        // ? 가 먼저 등장하는 경우.
        if (query.charAt(0) == '?') {
            index = query.lastIndexOf('?');
            query = query.substring(index + 1);
            if (word.endsWith(query)) count++;
        } else {
            index = query.indexOf('?');
            query = query.substring(0, index);
            if (word.startsWith(query)) count++;
        }

        return count;
    }
}
