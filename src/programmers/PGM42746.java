package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * created by victory_woo on 2020/03/27
 * 가장 큰 수.
 * 30, 34 와 같이 두 자리 수에 대해서는 짤라버리면 역순 정렬했을 때, 4가 앞으로 가서 반례가 생김..
 */
public class PGM42746 {
    public static void main(String[] args) {
        System.out.println(solution2(new int[]{6, 10, 2}));
        System.out.println();
        System.out.println(solution2(new int[]{3, 30, 34, 5, 9}));
    }

    public static String solution(int[] numbers) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (10 <= numbers[i]) {
                int value = numbers[i];
                while (value != 0) {
                    list.add(value % 10);
                    value = value / 10;
                }
            } else {
                list.add(numbers[i]);
            }
        }

        list.sort(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int a : list) sb.append(a);
        return sb.toString();
    }

    public static String solution2(int[] numbers) {
        String[] strNumbers = new String[numbers.length];

        // 1. int[] -> String[] 변환.
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        // String 배열을 내림차순으로 정렬한다.
        Arrays.sort(strNumbers, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        StringBuilder sb = new StringBuilder();
        if (strNumbers[0].equals("0")) {
            sb.append("0");
        } else {
            for (String s : strNumbers) sb.append(s);
        }

        //for (int i = 0; i < strNumbers.length; i++) System.out.println(strNumbers[i]);

        return sb.toString();
    }
}
