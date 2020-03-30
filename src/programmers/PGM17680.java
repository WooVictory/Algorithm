package programmers;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/30
 * 캐시 - 2018 카카오 블라인드 채용 코딩 테스트 3번 문제.
 * 어렵지만, 재미있는 문제!
 */
public class PGM17680 {
    public static void main(String[] args) {
        System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"})); // tc 1
        System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"})); // tc 2
        System.out.println(solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"})); // tc 3
        System.out.println(solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"})); // tc 6
    }

    public static int solution(int cacheSize, String[] cities) {
        // 예외 처리. 캐시 사이즈가 0이라면 캐시하지 않기 때문에 cities 배열의 각 도시마다 모두 5초 걸린다.
        if (cacheSize == 0) return 5 * cities.length;

        // map : cache 관리.
        // linkedList : LRU 캐시를 간단하게 사용하기 위함.
        // 가장 마지막 원소가 최근에 사용한 원소(혹은 최근에 접근한 원소)
        // 가장 앞의 원소가 최근에 사용하지 않은 원소.
        HashMap<String, Integer> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        int time = 0;
        for (String s : cities) {
            // 문제에서 대소문자를 구분하지 않는다고 했으므로 대소문자를 구분하지 않고 동일하게 바라본다.
            s = s.toLowerCase();
            // cache 를 관리하는 map 에 s 가 포함되지 않았다면 cache 하고 있지 않으므로 실행 시간에 5초를 더한다.
            if (!map.containsKey(s)) {
                time += 5;
                // map.size()가 cacheSize 보다 작으면 그냥 넣어준다.
                if (map.size() < cacheSize) {
                    map.put(s, 0);
                    linkedList.add(s);
                } else {
                    // map.size() >= cacheSize 인 경우에는 넣어주고, 맨 위의 최근에 사용하지 않은 원소를 삭제한다.
                    map.put(s, 0);
                    linkedList.add(s);

                    // 맨 위의 원소를 삭제 : 최근에 사용하지 않은 원소를 삭제.
                    map.remove(linkedList.get(0));
                    linkedList.remove(0);
                }
            } else {
                // 캐시 되어 있는 경우, 실행 시간에 1초를 더한다.
                time += 1;
                // 해당 원소를 삭제하고, 다시 추가한다.
                // 이를 통해서 최근에 사용했기 때문에 linkedList 의 마지막으로 원소를 추가해준다.
                linkedList.remove(s);
                linkedList.add(s);
            }
        }

        return time;
    }
}