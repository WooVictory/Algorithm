package 카카오19인턴;

import java.util.*;

/**
 * created by victory_woo on 2020/05/04
 * 카카오 19 인턴 기출.
 * 튜플.
 * 파싱 & 집합 개념.
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
        System.out.println(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));
        System.out.println(solution("{{20,111},{111}}"));
        System.out.println(solution("{{123}}"));
        System.out.println(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));
    }

    public static int[] solution(String s) {
        int len = s.length();
        s = s.substring(1, len - 1);
        int count = findCount(s);
        Map<String, Integer> map = new HashMap<>();

        int start = 0, end;
        for (int i = 0; i < count; i++) {
            start = s.indexOf('{', start);
            end = s.indexOf('}', start);

            String num = s.substring(start + 1, end);
            String[] strArr = num.split(",");
            for (String str : strArr) {
                if (map.get(str) == null) map.put(str, 1);
                else map.put(str, map.get(str) + 1);
            }

            start += num.length();
        }

        System.out.println(map);
        ArrayList<Node> list = new ArrayList<>();
        for (String key : map.keySet()) list.add(new Node(toInt(key), map.get(key)));

        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).index;
            System.out.print(list.get(i).index + " ");
        }
        System.out.println();

        return answer;
    }

    static class Node implements Comparable<Node> {
        int index;
        int count;

        Node(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(Node that) {
            return that.count - this.count;
        }
    }

    private static int findCount(String s) {
        int count = 0;
        int fromIndex = -1;
        while ((fromIndex = s.indexOf('{', fromIndex + 1)) >= 0) {
            count++;
        }
        return count;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
