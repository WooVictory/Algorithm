package 카카오18;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 18 기출
 * 다시 푸는 중.
 * 캐시.
 */
public class RE_Test1 {
    public static void main(String[] args) {
        //System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        //System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        //System.out.println(solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 1. cacheSize 가 0인 경우, 캐시가 되지 않으므로 도시마다 실행 시간은 5초가 걸린다.
        if (cacheSize == 0) return cities.length * 5;

        LinkedList<String> list = new LinkedList<>();
        // 2. 도시를 하나씩 순회한다. 대소문자 구분하지 않음.
        for (String city : cities) {
            city = city.toLowerCase();

            // 3. Cache Miss 인 경우.
            if (!list.contains(city)) {
                // list 가 널널하기 때문에 캐싱을 한다.
                if (list.size() < cacheSize) {
                    list.addFirst(city);
                } else {
                    // list 가 꽉 차 있으므로 제일 뒤의 원소를 삭제하고
                    // 앞에 city 를 넣어준다.
                    list.removeLast();
                    list.addFirst(city);
                }

                // 실행 시간은 5초가 걸린다.
                answer += 5;
            } else {
                // 4. Cache hit 인 경우.
                // city 를 삭제하고, 맨 앞에 city 를 삽입한다.
                list.remove(city);
                list.addFirst(city);
                // 실행 시간은 1초가 걸린다.
                answer += 1;
            }

            System.out.println("CacheList : " + list + ", 실행 시간 : " + answer);
        }

        return answer;
    }
}
