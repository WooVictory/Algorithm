package 카카오19;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/07
 * 카카오 19 기출.
 * 다시 푸는 중.
 * 후보키.
 */
public class RE_Test8 {
    public static void main(String[] args) {
        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};

        System.out.println(solution(relation));
    }

    private static int rowSize, colSize;
    // 비트에서 1의 개수가 적은 순서대로 정렬한다.
    // 이는 즉, 속성의 개수가 적은 부분 집합 순으로 정렬한다.
    // -> 속성을 적게 갖는 순서대로 정렬하다.
    private static Comparator<Integer> comp = (a, b) -> {
        int x = countBits(a), y = countBits(b);
        if (x > y) return 1;
        else if (x < y) return -1;
        else return 0;
    };

    // 비트에서 1의 개수를 구한다.
    private static int countBits(int n) {
        int count = 0;
        while (n != 0) {
            // 1과 & 연산을 해서 0이 아니라면 1이 존재한다는 것이므로 1의 개수를 카운트한다.
            if ((n & 1) != 0) count++;

            // n을 오른쪽으로 비트 시프트 하면서 값을 감소시킨다.
            n = n >> 1;
        }
        return count;
    }

    public static int solution(String[][] relation) {
        int answer = 0;
        rowSize = relation.length;
        colSize = relation[0].length;
        LinkedList<Integer> candidates = new LinkedList<>();

        // 공집합은 필요 없으므로, 1부터 속성의 갯수만큼 부분 집합을 구한다.
        for (int i = 1; i < 1 << colSize; i++) {
            // 유일성을 만족하는지 확인한다.
            if (check(relation, i)) candidates.add(i);
        }

        candidates.sort(comp);

        // 최소성을 만족하는 지 확인한다.
        // 속성의 개수가 적은 순으로 정렬이 되어 있다.
        // 따라서 제일 앞에 있는 속성은 최소성을 만족한다.
        while (candidates.size() != 0) {
            int n = candidates.remove(0);
            answer++; // 후보키의 개수를 증가시킨다.

            // 이 속성을 포함하는 나머지 후보키를 지우면 된다.
            // 왜냐하면 그 후보키들은 최소성을 만족하지 못하는 것이기 때문이다!
            Iterator<Integer> it = candidates.iterator();
            while (it.hasNext()) {
                int c = it.next();

                // c가 n을 포함한다면 c 후보키를 지운다.
                if ((n & c) == n) it.remove();
            }
        }

        return answer;
    }

    // 유일성을 만족하는지 확인한다.
    private static boolean check(String[][] relation, int subset) {
        // 2개의 튜플을 비교하는 가능한 조합을 모두 구한다.(2중 for 반복문을 통해서)
        for (int a = 0; a < rowSize - 1; a++) {
            for (int b = a + 1; b < rowSize; b++) {
                boolean isSame = true;

                for (int k = 0; k < colSize; k++) {
                    // subset 을 k번째 비트와 & 연산을 했을 때,
                    // 0이라는 것은 subset 이 해당 속성을 포함하고 있지 않다는 것이다.
                    if ((subset & 1 << k) == 0) continue;

                    // a 튜플의 k 속성과 b 튜플의 k 속성이 다르다면
                    // 두 튜플은 완전히 다르다. 따라서 더 이상 뒤의 속성을 볼 필요가 없다.
                    // 하나라도 다르면 두 튜플은 다른 것으로 인식되기 때문이다.
                    // 따라서 속성의 부분 집합이 후보키로 될 가능성이 있다.
                    if (!relation[a][k].equals(relation[b][k])) {
                        isSame = false;
                        break;
                    }
                }

                // isSame 이 true 이면 두 튜플이 완전 똑같다.
                // 따라서 유일성을 만족하지 않는다.
                if (isSame) return false;
            }
        }
        return true;
    }
}
