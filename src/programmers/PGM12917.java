package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/03/20
 * 프로그래머스 문자열 내림차순으로 배치하기.
 * 문제를 처음에 풀었는데, 테스트 케이스만 맞추고 다른 예외 상황은 고려하지 않았다.
 * 다시 생각해보니 풀 수 있는 방법은 2개가 있다.
 * 1. 오름차순으로 먼저 정렬하고, for 문으로 역으로 출력하는 방법.
 * 2. char[] 배열로 바꿔서 오름차순으로 정렬하고 StringBuilder()의 reverse()를 사용해 역으로 출력하는 방법.
 * 2번의 방법이 더 깔끔한 코드가 나올 수 있다.
 */
public class PGM12917 {
    public static void main(String[] args) {
        String s = "Zbcdefg";
        String s1 = "bcd";
        System.out.println(solution(s));
    }

    private static String solution(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c); // 오름차순 정렬로 만든다.

        return new StringBuilder(new String(c)).reverse().toString();

    }
}
