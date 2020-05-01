package 카카오18;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/30
 * 카카오 18 기출.
 * [1차] 캐시.
 * LRU 캐시 구현.
 */
public class Test3_re {
    public static void main(String[] args) {
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };
        int cacheSize = 3;
        System.out.println(solution(cacheSize, cities));

        String[] cities1 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" };
        System.out.println(solution(cacheSize, cities1));

        String[] cities2 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" };
        System.out.println(solution(2, cities2));

        String[] cities3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome" };
        System.out.println(solution(5, cities3));

        String[] cities4 = {"Jeju", "Pangyo", "NewYork", "newyork" };
        System.out.println(solution(2, cities4));

        String[] cities5 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA" };
        System.out.println(solution(0, cities5));
    }

    public static int solution(int cacheSize, String[] cities) {
        // 캐시 사이즈가 0인 경우, 전부 실행 시간이 5초 걸린다.
        if (cacheSize == 0) return cities.length * 5;

        int answer = 0;
        LinkedList<String> list = new LinkedList<>();
        for (String city : cities) {
            // 대소문자를 구분하지 않음.
            city = city.toLowerCase();

            // cache hit 인 경우, 실행 시간은 1초 걸린다.
            if (list.contains(city)) {
                answer += 1;
                list.remove(city);
                list.addFirst(city);
            } else {
                // 그렇지 않은 경우, 실행 시간은 5초 걸린다.
                answer += 5;
                // 캐시 사이즈보다 작은 경우, 별다른 연산 없이 추가할 수 있다.
                if (list.size() < cacheSize) {
                    list.addFirst(city);
                } else {
                    // 캐시 사이즈보다 큰 경우, LRU 캐시 알고리즘대로
                    // 가장 오랫동안 사용하지 않은 원소를 삭제하고 새로 들어온 city 를 맨 앞에 추가한다.
                    list.removeLast();
                    list.addFirst(city);
                }
            }
        }
        return answer;
    }
}