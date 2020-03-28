package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/03/27
 * 가장 큰 수.
 */
public class PGM42746Re {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{6, 10, 2}));
        System.out.println(solution(new int[]{3, 30, 34, 5, 9}));
    }

    public static String solution(int[] numbers) {
        // 1. int[] -> String[] 변환한다.
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) strNumbers[i] = String.valueOf(numbers[i]);

        // 2. 문자열에 대해서 내림차순 정렬.
        // 문자 a,b가 있을 때 ab ba 중 큰 값이 숫자가 큰 값이므로 두 문자를 합쳐서 대소를 비교하여
        // 내림차순 정렬을 한다.
        Arrays.sort(strNumbers, ((o1, o2) -> (o2 + o1).compareTo(o1 + o2)));

        // 3. 내림차순 정렬 시 맨 앞에 0 문자가 있다면 이는 0으로만 구성된 문자열이므로
        // 중복된 000... 이 출력되지 않고 0이 출력되도록 한다.
        // 그렇지 않은 경우에는 StringBuilder 객체를 이용해 값을 반환한다.
        if (strNumbers[0].equals("0")) {
            return "0";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : strNumbers) sb.append(s);
            return sb.toString();
        }
    }
}
