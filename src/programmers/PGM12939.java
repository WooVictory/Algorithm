package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by victory_woo on 2020/05/13
 * 최댓값과 최솟값.
 */
public class PGM12939 {
    public static void main(String[] args) {
        System.out.println(solution("1 2 3 4"));
        System.out.println(solution("-1 -2 -3 -4"));
        System.out.println(solution("-1 -1"));
    }

    public static String solution(String s) {
        String[] strArr = s.split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for (String aStrArr : strArr) list.add(toInt(aStrArr));

        int max = Collections.max(list);
        int min = Collections.min(list);

        return String.valueOf(min) + " " + max;
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
