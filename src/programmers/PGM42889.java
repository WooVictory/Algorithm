package programmers;


import java.util.Arrays;

/**
 * created by victory_woo on 2020/03/24
 * 실패율.
 * 소팅을 하는 것이 조금 까다롭고, 클래스를 하나 만들어서 해결하는 편이 낫다.
 */
public class PGM42889 {
    public static void main(String[] args) {
        solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
        //solution(4, new int[]{4, 4, 4, 4, 4});
    }

    public static int[] solution(int N, int[] stages) {
        // 사용자가 사용하는 stages 배열의 인덱스는 1부터 시작하기 때문에 1 ~ N까지 사용하기 위해서 N+1
        int[] current = new int[N + 1];
        // N+1은 마지막 스테이지까지 클리어한 사용자를 의미한다.
        // 하지만, 여기서 내가 구해야 할 것은 스테이지에 도달했으나 아직 클리어하지 못한 사용자이기 때문에
        // 클리어한 사용자가 아닌 사용자를 구해야 한다.
        for (int stage : stages) {
            if (stage != N + 1) current[stage]++;
        }

        int len = stages.length;
        // fails 배열은 1부터 시작할 것이기 때문에 N+1로 초기화를 진행한다.
        // 그리고 사용하지 않은 0번째 인덱스에는 의미없는 값을 넣어둔다.
        Fail[] fails = new Fail[N + 1];
        fails[0] = new Fail(1000, -1);

        // len 이 0이 아닌 경우에는 current[i] / len 값을 index 에 맞춰서
        // Fail 객체를 생성하며 fails 배열에 넣어준다. 그 후에는 len -= current[i] 하여
        // 실패율 계산을 한 뒤, len 값을 업데이트해야 한다.
        // len == 0일 때는 실패율이 0인 경우를 처리하기 위함이다.
        // index 값에 맞게 0을 넣어준다.
        for (int i = 1; i <= N; i++) {
            if (len == 0) fails[i] = new Fail(i, 0);
            else fails[i] = new Fail(i, (double) current[i] / len);

            len -= current[i];
        }

        // 정렬을 해준다.
        Arrays.sort(fails, (o1, o2) -> {
            // 실패율이 다를 때는 내림차순 정렬을 한다.
            // 람다식의 매개변수 순서는 o1, o2이다. 이를 그대로 compare()함수의 인자로 전달하면 오름차순이다.
            // 하지만, 반대로 전달하면 내림 차순이다.
            if (o1.failRate != o2.failRate) {
                return Double.compare(o2.failRate, o1.failRate);
            }

            // 만약, 두 실패율이 같은 경우에는 index 를 기준으로 오름차순 정렬을 한다.
            return Integer.compare(o1.num, o2.num);
        });

        int[] answer = new int[N];
        for (int i = 0; i < fails.length - 1; i++) answer[i] = fails[i].num;

        return answer;
    }

    // num : index, failRate : 실패율.
    static class Fail {
        int num;
        double failRate;

        Fail(int num, double failRate) {
            this.num = num;
            this.failRate = failRate;
        }
    }
}
