package programmers;

/**
 * created by victory_woo on 2020/03/21
 * 서울에서 김서방 찾기.
 * 인덱스 찾아서 문자열 반환하면 된다.
 */
public class PGM12919 {
    public static void main(String[] args) {
        String[] s = {"Jane", "Kim"};
        System.out.println(solution(s));
    }

    public static String solution(String[] seoul) {
        String answer;
        int index = 0;
        for (int i = 0; i < seoul.length; i++) {
            if ("Kim".equals(seoul[i])) {
                index = i;
                break; // 찾으면 더 이상 탐색하지 않고, 탈출.
            }
        }
        answer = "김서방은 " + index + "에 있다";
        return answer;
    }
}
