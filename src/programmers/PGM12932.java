package programmers;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/03/22
 * 자연수 뒤집어 배열로 만들기
 * 분명 풀이가 맞았는데, 테케 3개를 틀리길래 뭐가 틀렸나 보다가
 * n % 10을 수행할 때, 앞에서 long -> int 형 변환을 하는데
 * 괄호로 감싸지 않으면 n만 형 변환이 될 때가 있나보다.
 * 그래서 (n%10)처럼 괄호로 감싸면 모든 테케를 통과한다,
 */
public class PGM12932 {
    public static void main(String[] args) {
        System.out.println(solution(12345));
    }

    public static int[] solution(long n) {
        ArrayList<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add((int) (n % 10));
            n /= 10;
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);

        return answer;

        // 다른 방법. list 사용하지 않고, n을 string 으로 변환해서 길이를 구하는 방법.
        /*int len = Long.toString(n).length();
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            answer[i] = (int) (n % 10);
            n /= 10;
        }

        return answer;*/
    }
}
