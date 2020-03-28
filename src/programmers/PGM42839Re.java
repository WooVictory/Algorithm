package programmers;

import java.util.HashSet;
import java.util.Set;

/**
 * created by victory_woo on 2020/03/29
 * 소수 찾기. Level2
 * 완전탐색.
 */
public class PGM42839Re {
    public static void main(String[] args) {
        System.out.println(solution("17"));
    }

    public static int solution(String numbers) {
        // 1. String[] -> int[] 배열로 변환한다.
        int[] arr = new int[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) arr[i] = numbers.charAt(i) - '0';

        // 2. 순열을 구해본다.
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= numbers.length(); i++) {
            perm(arr, 0, i, set);
        }

        return set.size();
    }

    // 순열을 구한다.
    private static void perm(int[] arr, int depth, int k, Set<Integer> set) {
        if (depth == k) {
            print(arr, k, set);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap(arr, i, depth);
            perm(arr, depth + 1, k, set);
            swap(arr, i, depth);
            // 위의 swap()으로 인해서 원본 배열이 바뀌기 때문에
            // 바뀐 배열을 다시 원래대로 돌려놓는다.
        }
    }

    // 순열을 구성한 arr 배열을 StringBuilder 객체를 통해 붙인다.
    // 그리고 이 수가 소수가 맞는지 판단한다.
    private static void print(int[] arr, int k, Set<Integer> set) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(arr[i]);
        }

        System.out.println(sb.toString());
        primeNumber(set, sb);
    }

    // sb 객체를 정수로 변환한다.
    // 3. 소수인지 판단한다. 소수일 경우, set 에 추가한다.
    // 소수의 판별은 소수의 특징인 "소수는 1과 자기 자신으로만 나누어 떨어진다."를 활용한다.
    private static void primeNumber(Set<Integer> set, StringBuilder sb) {
        int value = Integer.parseInt(sb.toString());
        boolean isPrime = true;
        if (value <= 1) return;

        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) {
                isPrime = false;
                break;
            }
        }

        // 소수일 경우, 추가한다.
        if (isPrime) set.add(value);
    }

    // 배열의 원소를 바꿔준다.
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
