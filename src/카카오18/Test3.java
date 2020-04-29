package 카카오18;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/04/29
 * 카카오 18 기출.
 * [1차] 캐시.
 */
public class Test3 {
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
        int answer = 0;
        if (cacheSize == 0) return cities.length * 5;

        LinkedList<String> list = new LinkedList<>();
        for (String city : cities) {
            city = city.toLowerCase();

            if (list.contains(city)) {
                answer += 1;
                list.remove(city);
                list.addFirst(city);

            } else {
                answer += 5;
                if (list.size() < cacheSize) {
                    list.addFirst(city);
                } else {
                    list.addFirst(city);
                    list.removeLast();
                }
            }

        }
        return answer;
    }
}
