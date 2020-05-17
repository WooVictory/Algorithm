package programmers;


/**
 * created by victory_woo on 2020/05/17
 * 큰 수 만들기 - Level2
 */
public class PGM42883 {
    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
        System.out.println(solution("123", 1));
    }

    public static String solution(String number, int k) {
        // 문자열 연산을 빠르게 하기 위해 StringBuilder 사용.
        StringBuilder sb = new StringBuilder(number);

        int deleteCount = 0;
        int index = 1; // 이전 문자와 비교하므로 인덱스는 1부터 시작한다.

        // k를 삭제할 때까지 반복한다.
        while (deleteCount != k) {
            // index 가 1보다 크거나 같고 이전 문자가 현재 문자보다 작으면 이전 문자를 삭제한다.
            // 삭제했으므로 인덱스를 감소시키고 count 를 증가시킨다.
            if (1 <= index && sb.charAt(index - 1) < sb.charAt(index)) {
                sb.deleteCharAt(index - 1);
                index--;
                deleteCount++;
            } else {
                // 만약, 인덱스가 끝까지 갔고 현재 문자가 이전 문자보다 작거나 같은 경우
                // 현재 문자 즉, 마지막 문자를 삭제한다. 마찬가지로 인덱스를 감소시키고 count 를 증가시킨다.
                // 100000, k=3 과 같이 동일한 문자가 여러 번 나오는 경우에 대한 예외 처리!
                if (index == sb.length() - 1 && sb.charAt(index) <= sb.charAt(index - 1)) {
                    sb.deleteCharAt(index);
                    index--;
                    deleteCount++;
                } else {
                    // 그외의 경우에는 인덱스를 증가시킨다.
                    index++;
                }
            }
        }

        return sb.toString();
    }

}
