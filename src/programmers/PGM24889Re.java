package programmers;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/03/25
 * 실패율.
 * 1. 같은 stage 에 있는 사람의 수를 계산한다. -> 실패율을 계산하기 위해서!
 * 2. 실패율을 계산하기 위해 Fail 이라는 클래스를 만든다. Fail -> 스테이지 번호, 실패율을 저장한다. 그리고 실패율을 계산한다.
 * 3. 실패율에 대해서 내림차순 정렬을 진행하고, 실패율이 같은 경우에는 스테이지 번호에 대해서 오름차순 정렬을 한다.
 */
public class PGM24889Re {
    public static void main(String[] args) {
        solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
    }

    public static int[] solution(int N, int[] stages) {
        // 1. 같은 stages 에 있는 사람의 수를 계산한다. -> 실패율을 계산하기 위해서!
        int[] current = new int[N + 1];
        for (int stage : stages) {
            if (stage != N + 1) current[stage]++;
        }

        // 2. 실패율을 계산한다.
        // 0번째는 사용하지 않기 위해서 스테이지 번호는 큰 값과 실패율도 -1을 넣어준다.
        // 실패율에 대해서 내림차순 정렬시 맨 마지막 값으로 간다.
        int len = stages.length;
        Fail[] fails = new Fail[N + 1];
        fails[0] = new Fail(1000, -1);

        for (int i = 1; i < N + 1; i++) {
            if (len == 0) fails[i] = new Fail(i, 0);
            else fails[i] = new Fail(i, (double) current[i] / len);

            len -= current[i];
        }

        // 3. 실패율에 대해서 내림차순 정렬. 실패율이 같으면 스테이지 번호에 대해서 오름차순 정렬.
        Arrays.sort(fails, (o1, o2) -> {
            if (o1.failRate != o2.failRate) return Double.compare(o2.failRate, o1.failRate);
            return Integer.compare(o1.num, o2.num);
        });

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = fails[i].num;
        }
        return answer;
    }

    static class Fail {
        int num;
        double failRate;

        Fail(int num, double failRate) {
            this.num = num;
            this.failRate = failRate;
        }
    }
}
