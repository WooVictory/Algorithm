package 카카오19인턴;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/05/04
 * 카카오 19 인턴 기출.
 * 튜플.
 */
public class Test2_re {
    public static void main(String[] args) {
        System.out.println(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
        /*System.out.println(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));
        System.out.println(solution("{{20,111},{111}}"));
        System.out.println(solution("{{123}}"));
        System.out.println(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));*/
    }


    public static int[] solution(String s) {
        int len = s.length();

        // 앞 뒤에 존재하는 중괄호를 제거한다.
        s = s.substring(1, len - 1);
        // 몇 개의 튜플이 존재하는지 개수를 구한다.
        int count = getCountOfTuple(s);
        Map<String, Integer> map = new HashMap<>();

        int start = 0, end;
        // 총 count 만큼의 집합이 존재한다.
        // 집합 하나씩 중괄호를 제거하여 숫자만을 얻어내기 위해 파싱한다.
        for (int i = 0; i < count; i++) {
            start = s.indexOf('{', start);
            end = s.indexOf('}', start);

            // 중괄호 안의 숫자 문자열을 얻는다.
            String num = s.substring(start + 1, end);
            // 숫자만을 얻기 위해 ,를 제거한다.
            String[] numbers = num.split(",");

            // 숫자 문자열을 key 로 하고, 등장 횟수를 Value 로 하여 map 에 저장한다.
            for (String key : numbers) map.put(key, map.getOrDefault(key, 0) + 1);

            // 하나의 부분 집합을 처리한 뒤, start 의 시작 위치를 조정해준다. 그 다음 집합을 찾을 수 있도록
            start += num.length();
        }

        // map -> list 에 담는다.
        ArrayList<Node> list = new ArrayList<>();
        for (String key : map.keySet()) list.add(new Node(toInt(key), map.get(key)));

        // 정렬한다. count 를 기준으로 내림차순 정렬.
        Collections.sort(list);

        // 배열을 만들고, 배열에 list 의 값을 담는다.
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).index;
            System.out.println(answer[i]);
        }

        return answer;
    }

    private static int getCountOfTuple(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') count++;
        }

        return count;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }

    static class Node implements Comparable<Node> {
        int index;
        int count;

        Node(int index, int count) {
            this.index = index;
            this.count = count;
        }

        // 등장 횟수가 많은 순으로 앞에 오도록 정렬한다. (내림차순 정렬)
        @Override
        public int compareTo(Node that) {
            return that.count - this.count;
        }
    }
}
