package programmers;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/04/01
 * 숫자 야구.
 * 완전 탐색 유형
 * 순열을 구하는 방법을 이용해 모든 경우를 구한다.
 */
public class PGM42841Re2 {
    private static int[][] baseball;
    private static ArrayList<String> result = new ArrayList<>();

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
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        PGM42841Re2.baseball = baseball;
        perm(arr, 0, 3);

        return result.size();
    }

    // 1~9까지의 숫자를 이용해서 길이가 k인 즉, 3인 순열을 만든다.
    public static void perm(int[] arr, int depth, int k) {
        if (depth == k) {
            makeNumber(arr, k);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap(arr, i, depth);
            perm(arr, depth + 1, k);
            swap(arr, i, depth);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // 순서가 바뀐 배열에서 3개만 선택해서 순열을 만든 뒤, check() 함수에 넘겨 확인한다.
    public static void makeNumber(int[] arr, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) sb.append(arr[i]);

        check(sb.toString());
    }

    // num, target 값을 비교하여 strike, ball 을 판단해서
    // baseball 배열에 존재하는 모든 숫자의 strike, ball 조건에 맞는다면 true 이고, result 에 추가한다.
    private static void check(String num) {
        boolean flag = true;
        for (int i = 0; i < baseball.length; i++) {
            String target = String.valueOf(baseball[i][0]);
            int strike = getStrike(num, target);
            int ball = getBall(num, target) - strike;

            if (strike != baseball[i][1] || ball != baseball[i][2]) {
                flag = false;
                break;
            }
        }

        if (flag) result.add(num);
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

}
