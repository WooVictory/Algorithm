package 카카오19인턴;

import java.util.*;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 19 인턴 기출.
 * 다시 푸는 중.
 * 튜플.
 */
public class RE_Test2 {
    public static void main(String[] args) {
        System.out.println(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
        System.out.println(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));
        System.out.println(solution("{{20,111},{111}}"));
        System.out.println(solution("{{123}}"));
        System.out.println(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));
    }

    public static int[] solution(String s) {
        int len = s.length();
        // 1. 앞, 뒤의 {,}를 제거한다.
        s = s.substring(1, len - 1);
        // 1-1. 부분 집합의 개수를 구한다.
        int count = getCount(s);

        Map<Integer, Integer> map = new HashMap<>();
        List<Node> list = new ArrayList<>();

        // 2. 문자열을 파싱해서 부분 집합을 하나씩 처리한다.
        int start = 0, end;
        for (int i = 0; i < count; i++) {
            start = s.indexOf('{', start);
            end = s.indexOf('}', start);

            // 부분 집합에서 숫자만을 구한다.
            String num = s.substring(start + 1, end);
            // 콤마를 제거하여 숫자만을 얻는다.
            String[] nums = num.split(",");
            // map 을 사용해서 원소를 Key, 등장 횟수를 Value 로 하여 저장한다.
            for (String number : nums) map.put(toInt(number), map.getOrDefault(toInt(number), 0) + 1);

            // 다음 부분 집합을 찾기 위해 부분 집합의 크기만큼 뒤로 옮겨준다.
            start += num.length();
        }

        // 3. map -> list 로 바꿔 담는다.
        for (int key : map.keySet()) list.add(new Node(key, map.get(key)));

        // 4. 정렬.
        Collections.sort(list);

        // 5. answer 배열에 담아 반환한다.
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).number;
            System.out.print(answer[i] + " ");
        }
        System.out.println();

        return answer;
    }

    // 부분 집합의 갯수를 구한다.
    private static int getCount(String s) {
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
        int number;
        int count;

        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }

        // 등장 횟수를 기준으로 많이 등장한 원소가 앞에 오도록 정렬한다.(내림차순 정렬)
        @Override
        public int compareTo(Node that) {
            return that.count - this.count;
        }
    }
}
