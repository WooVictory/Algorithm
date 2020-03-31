package programmers;

import java.util.ArrayList;
import java.util.Stack;

/**
 * created by victory_woo on 2020/04/01
 * 숫자 야구.
 * 완전탐색 그대로의 문제!
 */
public class PGM42841 {
    public static void main(String[] args) {
        int[][] baseball = {
                {123, 1, 1},
                {356, 1, 0},
                {327, 2, 0},
                {489, 0, 1}
        };
        solution(baseball);
    }

    public static int solution(int[][] baseball) {
        int answer = 0;

        Stack<String> stack = getNumbers();
        ArrayList<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            String num = stack.pop();
            if (check(num, baseball)) {
                result.add(num);
            }
        }

        for (String s : result) System.out.println(s);
        System.out.println(result.size());
        return answer;
    }

    private static boolean check(String num, int[][] baseball) {
        for (int i = 0; i < baseball.length; i++) {
            String target = String.valueOf(baseball[i][0]);
            int strike = checkStrike(num, target);
            int ball = checkBall(num, target);
            ball = ball - strike;

            if (strike != baseball[i][1] || ball != baseball[i][2]) return false;
        }
        return true;
    }

    public static Stack<String> getNumbers() {
        Stack<String> stack = new Stack<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                for (int k = 1; k < 10; k++) {
                    if (i == j || j == k || k == i) continue;
                    stack.push(String.valueOf(i * 100 + j * 10 + k));
                }
            }
        }
        return stack;
    }

    public static int checkStrike(String num, String target) {
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (num.charAt(i) == target.charAt(i)) count++;
        }
        return count;
    }

    public static int checkBall(String num, String target) {
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (num.contains(String.valueOf(target.charAt(i)))) count++;
        }
        return count;
    }

}
