package 카카오19;

import java.util.Arrays;

/**
 * created by victory_woo on 2020/04/24
 * 카카오 19 기출.
 * 실패율.
 */
public class Test2_re {
    public static void main(String[] args) {
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] stages2 = {4, 4, 4, 4, 4};
        System.out.println(solution(5, stages));
        System.out.println(solution(4, stages2));
    }

    public static int[] solution(int n, int[] stages) {
        int[] current = new int[n + 1];

        // 해당 스테이지를 클리어하지 못하고 도전 중인 사용자를 저장한다.
        // N+1은 마지막 스테이지까지 클리어한 사용자를 나타내므로 제외.
        // current[2] = 3 => 2번 스테이지를 클리어하지 못하고 도전 중인 사용자가 3명이라는 뜻이다.
        for (int stage : stages) {
            if (stage != n + 1) current[stage]++;
        }

        int len = stages.length;
        Fail[] fails = new Fail[n + 1];
        fails[0] = new Fail(1000, -1);
        // 의미 없는 값을 넣음으로 인해서 나중에 정렬 시 이 값은 맨 뒤로 이동한다.
        // 왜냐하면 정렬은 실패율이 큰 값이 앞에 오는 내림 차순 정렬이기 때문에 -1은 제일 뒤에 나타난다.
        // 혹여나 -1과 같은 값을 가지더라도 1000으로 인해서 스테이지 번호가 작은게 앞으로 가므로 0번 인덱스 값은 가장 뒤로 이동한다.

        // 스테이지 번호와 실패율을 구해 fail 배열에 저장한다.
        // len 이 0인 경우는 0으로 나누는 경우를 방지하기 위해 실패율 0을 넣어준다.
        for (int i = 1; i <= n; i++) {
            if (len == 0) fails[i] = new Fail(i, 0);
            else fails[i] = new Fail(i, (double) current[i] / len);

            // 전체 사용자 수에서 해당 스테이지를 클리어하지 못하고 도전 중인 사용자 수를 빼서
            // len 값을 갱신한다.
            len -= current[i];
        }

        Arrays.sort(fails);
        int[] answer = new int[n];
        // 가장 뒤에 있는 의미 없는 값이 저장된 원소는 사용하지 않는다.
        for (int i = 0; i < fails.length - 1; i++) {
            answer[i] = fails[i].num;
        }

        return answer;
    }

    static class Fail implements Comparable<Fail> {
        int num;
        double fail;

        Fail(int num, double fail) {
            this.num = num;
            this.fail = fail;
        }

        // 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 한다. 즉, 번호를 오름차순으로 정렬.
        // 그렇지 않다면 실패율을 내림차순으로 정렬한다. 즉, 실패율이 높을수록 앞쪽에 배치.
        @Override
        public int compareTo(Fail that) {
            if (this.fail == that.fail) {
                return Integer.compare(this.num, that.num);
            }
            return Double.compare(that.fail, this.fail);
        }
    }
}
