package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/04/02
 * 전화번호부.
 * 해시.
 */
public class PGM42577 {
    public static void main(String[] args) {
        System.out.println(solution2(new String[]{"119", "97674223", "1195524421"}));
        //System.out.println(solution2(new String[]{"123", "456", "789"}));
        //System.out.println(solution2(new String[]{"12"}));
        System.out.println(solution2(new String[]{"12", "123", "567", "1235", "88"}));
        //System.out.println(solution2(new String[]{"22", "123", "3", "1235", "88"}));
    }

    // 오름차순으로 정렬한 뒤, 바로 다음 문자열하고만 비교하여 결과를 도출한다.
    // 정렬을 하면 길이가 짧은게 앞으로 가는게 아니라 문자열의 숫자가 작은 값이 앞으로 간다.
    public static boolean solution2(String[] phone_book) {
        Arrays.sort(phone_book);
        for (String s : phone_book) System.out.println(s);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) return false;
        }
        return true;
    }

    // 이중 for 문을 사용해서 현재 문자열과 다음 문자열을 비교한다.
    // s의 시작이 after 인 경우나 after 의 시작이 s인 경우를 확인하여 조건을 만족한다면 false 리턴하고 종료.
    // phone_book 배열을 다 돌아도 그런 경우가 없다면 true 리턴하고 종료.
    public static boolean solution(String[] phone_book) {
        if (phone_book.length == 1) return true;
        for (int i = 0; i < phone_book.length; i++) {
            String s = phone_book[i];
            for (int j = i + 1; j < phone_book.length; j++) {
                String after = phone_book[j];
                if (s.startsWith(after)) return false;
                if (after.startsWith(s)) return false;
            }
        }
        return true;
    }
}