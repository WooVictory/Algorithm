package programmers;

import java.util.HashSet;
import java.util.Set;

/**
 * created by victory_woo on 2020/03/27
 * 소수 찾기.
 * 완탐.
 */
public class PGM42839 {
    static boolean[] visit = new boolean[10000000];

    public static void main(String[] args) {
        getPrime();
        solution("17");
        System.out.println(solution("011"));
        //System.out.println(solution("011"));
    }

    public static int solution(String numbers) {
        char[] chars = numbers.toCharArray();
        int[] arr = new int[chars.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = chars[i] - '0';
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= arr.length; i++) {
            perm(arr, 0, i, set);
        }

        return set.size();
    }

    private static void perm(int[] arr, int depth, int k, Set<Integer> set) {
        if (depth == k) {
            print(arr, k, set);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap(arr, i, depth);
            perm(arr, depth + 1, k, set);
            swap(arr, i, depth);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(int[] arr, int k, Set<Integer> set) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i]);
            sb.append(arr[i]);
        }

        System.out.println();
        primeNumber(set, sb);
    }

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
        if (isPrime) set.add(value);

        for (int i = 2; i < 10000000; i++) {
            if (!visit[i]) {
                if (i == value) {
                    set.add(value);
                    break;
                }
            }
        }
    }

    // 에라토스테네스의 체를 이용한 소수 구하기.
    private static void getPrime() {
        for (int i = 2; i < 10000000; i++) {
            if (visit[i]) continue;
            for (int j = i + i; j < 10000000; j += i) {
                visit[j] = true;
            }
        }
    }
}
