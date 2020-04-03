package programmers;

import java.util.HashMap;

/**
 * created by victory_woo on 2020/04/03
 * 위장.
 * 해시.
 * 다시 푸는 중!
 * HashMap 개념을 사용하여 Key : 의상의 종류, value : 의상 종류를 가지고 있는 갯수.
 * 경우의 수 개념을 사용해 결과를 도출!
 */
public class PGM42578Re {
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            // if ~ else 해결이 되는 걸 getOrDefault()로 한번에 해결 가능하다.
            // 만약, 해당 key 값으로 가져올 값이 있다면 그 value 값을 가져와 +1을 한다.
            // 그렇지 않다면 defaultValue=0으로 두어 0+1로 초기 값을 저장한다.
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);

            // 이미 해당 key 값으로 저장되어 있으면 해당 키 값으로 다시 저장할 때, 저장된 값 + 1을 한다.
            // 만약, 해당 key 값으로 저장된 값이 없다면 초기 저장을 할 때, 1을 저장한다.
            /*if (map.containsKey(clothes[i][1])) map.put(clothes[i][1], map.get(clothes[i][1]) + 1);
            else map.put(clothes[i][1], 1);*/
        }

        // 여기서는 경우의 수 개념이 사용된다.
        // headgear : 2개, eyewear : 1개
        // 만들 수 있는 가지 수는 아이템 1개만을 착용하는 경우 : 3개, headgear 1개(a) + eyewear 1개.
        // headgear 1개(b) + eyewear 1개. 따라서 총 5개이다.
        // (a,b,@) * (c, @) - 1 = 5로 구할 수 있다. @ : 아무것도 선택하지 않는 경우.
        int answer = 1;
        for (int value : map.values()) answer = answer * (value + 1);

        return answer - 1;
    }
}
