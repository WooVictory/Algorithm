package programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * created by victory_woo on 2020/04/02
 * 위장.
 * 해시.
 * 해시 문제라기 보다는 경우의 수이기 때문에 수학 문제에 가깝다..
 */
public class PGM42578 {
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        solution(clothes);
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] s : clothes) {
            if (map.containsKey(s[1])) map.put(s[1], map.get(s[1]) + 1);
            else map.put(s[1], 1);
        }

        int answer = 1;
        for (int value : map.values()) answer *= (value + 1);

        return answer - 1;
    }
}
