package programmers;

import java.util.ArrayList;
import java.util.Stack;

/**
 * created by victory_woo on 2020/04/01
 * 숫자 야구.
 * 완전 탐색 유형.
 */
public class PGM42841Re {
    public static void main(String[] args) {
        int[][] baseball = {
                {123, 1, 1},
                {356, 1, 0},
                {327, 2, 0},
                {489, 0, 1}
        };
        System.out.println(solution(baseball));
    }

    public static int solution(int[][] baseball) {
        Stack<String> stack = getNumbers();
        ArrayList<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            String num = stack.pop();
            if (check(num, baseball)) result.add(num);
        }
        return result.size();
    }

    private static boolean check(String num, int[][] baseball) {
        for (int i = 0; i < baseball.length; i++) {
            String target = String.valueOf(baseball[i][0]);
            int strike = getStrike(num, target);
            int ball = getBall(num, target) - strike;

            if (strike != baseball[i][1] || ball != baseball[i][2]) return false;
        }
        return true;
    }

    // 3. ball 갯수를 센다.
    // ball 의 갯수를 센다.
    // num 값이 스택에서 뺀 값이므로 스택에서 뺀 값이 기존의 baseball 배열이 가지고 있는 숫자와 비교했을 때,
    // ball 을 확인한다. (몇 개가 겹치는지)
    private static int getBall(String num, String target) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (num.contains(String.valueOf(target.charAt(i)))) count++;
        }
        return count;
    }

    // 3. strike 갯수를 센다.
    // strike 의 갯수를 센다.
    public static int getStrike(String num, String target) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (num.charAt(i) == target.charAt(i)) count++;
        }
        return count;
    }

    // 1. 1~9사이의 숫자를 이용해서 3자리의 숫자를 만들어 스택에 넣는다.
    public static Stack<String> getNumbers() {
        Stack<String> stack = new Stack<>();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    if (i == j || j == k || k == i) continue;
                    stack.push(String.valueOf(i * 100 + j * 10 + k));
                }
            }
        }
        return stack;
    }
}
