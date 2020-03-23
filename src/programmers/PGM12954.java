package programmers;

import java.util.ArrayList;

/**
 * created by victory_woo on 2020/03/23
 * x만큼 간격이 있는 n개의 숫자
 * 테케 2개가 틀리다. 히든 테케. 왜일까?
 * x * (i+1)이 int 의 범위를 초과한다면 엉뚱한 값이 들어가게 된다.
 * -10,000,000 <= x <= 10,000,000이다. 1 <= n <= 1000이다.
 * x = 10,000,000, n = 1000이라면 두 10,000,000 * 1000 = 10,000,000,000와 같이 최대값이 int 범위를 초과하게 된다.
 * 따라서 long 형으로 타입을 변환해서 계산해야 한다.
 */
public class PGM12954 {
    public static void main(String[] args) {
        //solution(2, 5);
        solution(-4, 2);
        solution(-4, 2);
    }

    public static long[] solution(int x, int n) {
        ArrayList<Long> list = new ArrayList<>();

        for (int i = 0; i < n; i++) list.add(((long) x * (i + 1)));

        long[] answer = new long[list.size()];

        for (int i = 0; i < n; i++) answer[i] = list.get(i);

        return answer;
    }

    // 아래의 풀이는 list 사용하지 않고, long 타입으로 변환하지도 않는다.
    // 다만, long 배열의 첫 번째 원소에 이미 x를 넣어주고 시작을 하고, 증가하는 값을 만들 때는 배열의 이전 원소 값 + x를 통해서 증가시킨다.
    public static long[] solution2(int x, int n) {
        long[] answer = new long[n];
        answer[0] = x;
        for (int i = 1; i < n; i++) answer[i] = answer[i - 1] + x;
        return answer;
    }
}
