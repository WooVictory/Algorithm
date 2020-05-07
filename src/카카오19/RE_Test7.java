package 카카오19;

import java.util.*;

/**
 * created by victory_woo on 2020/05/07
 */
public class RE_Test7 {
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

    private static int[] set;
    private static String[][] map;

    // 1인 비트의 수가 작은 순서대로 즉, 속성을 적게 갖은 순서대로 정렬이 된다.
    private static Comparator<Integer> comp = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            // 1로 되어있는 비트를 세야 한다. 1로 된 비트가 곧 속성의 갯수가 된다.
            // 각각의 부분 집합을 나타내는 int 값에서 1로 된 비트 수를 세도록 한다.
            int x = countBits(a), y = countBits(b);
            if (x > y) return 1;
            else if (x < y) return -1;
            else return 0;
        }
    };

    private static int countBits(int n) {
        int ret = 0;
        while (n != 0) {
            // 오른쪽으로 하나씩 shift 시키면 오른쪽에 있는 비트가 하나씩 없어진다.
            // 없애기 전에 그 비트가 1인지 아닌지 확인한다.
            // n을 1과 & 연산을 해서 0이 아니라는 것은 0번째 비트가 1이라는 뜻이다.
            // 따라서 1의 개수를 카운트 한다.
            if ((n & 1) != 0) ret++;
            n = n >> 1;
            // 그리고 비트를 하나씩 오른쪽으로 밀어내면서 1이면 카운트를 한다.
        }
        return ret;
    }


    public static int solution(String[][] relation) {
        int answer = 0;
        map = relation;
        int rowSize = relation.length; // 튜플의 개수.
        int colSize = relation[0].length; // 속성의 개수.
        LinkedList<Integer> candidates = new LinkedList<>();

        // 일반적으로 0부터 시작한다.
        // 하지만, 이 문제에서는 공집합이 필요없다.
        // 후보키가 되기 위해서는 최소한 1개의 속성이라도 있어야 한다.
        // i의 값이 공집합을 제외하고 1부터 시작해서 모든 부분 집합을 비트형으로 표현해준다.
        for (int i = 1; i < 1 << colSize; i++) {
            // 지금 우리가 만든 부분 집합이 유일성을 만족하는지 확인.
            if (check(relation, rowSize, colSize, i)) {
                candidates.add(i);
            }
        }

        // 비트가 작은 순서대로 정렬.
        // candidates : 유일성을 만족하는 모든 후보키를 가지고 있다.
        // 속성의 개수가 적은 순서대로 정렬을 한다.
        candidates.sort(comp);

        // 따라서 제일 처음의 후보키가 최소성을 만족할 것이다.
        while (candidates.size() != 0) {
            int n = candidates.remove(0);
            // 처음 꺼를 꺼내면 일단, 최초에는 최소성을 만족한다.
            // 가장 속성 개수가 적기 때문에!
            answer++;

            // n에 해당하는 이 속성을 포함하는 나머지 후보키를 모두 지우면 된다.
            // 왜냐하면 그 후보키들은 최소성을 만족하지 못하는 것이기 때문에!
            for (Iterator<Integer> it = candidates.iterator(); it.hasNext(); ) {
                // 하나를 뽑는다.
                int c = it.next();
                // 그리고 n(최소성을 만족한 후보키)와 & 연산을 해서
                // 그 결과가 n 이라고 하면 c는 n이 가지고 있는 모든 속성을 가지고 있음.
                // 그리고 c는 최소성을 만족하지 못하는 것! 따라서 c를 지워준다.
                if ((n & c) == n) it.remove();

                // 따라서 candidates 에서 최소성을 만족하지 못하는 모든 후보키들을 삭제하게 된다.
                // 그리고 이 과정이 끝나고 바깥 쪽의 while 문으로 가서
                // 다시 후보키를 꺼내면 그 후보키는 최소성을 만족하는 후보키가 될 것이다.
                // 왜냐하면 여기서 최소성을 만족하지 못하는 후보키를 다 지웠기 때문!
            }
        }

        return answer;
    }

    // 유일성을 만족하는지 확인한다.
    private static boolean check(String[][] relation, int rowSize, int colSize, int subset) {
        // 2개의 튜플만을 비교할 수 있도록 2중 for 반복문을 이용해 구성한다.
        for (int a = 0; a < rowSize - 1; a++) {
            for (int b = a + 1; b < rowSize; b++) {
                boolean isSame = true;
                // 모든 속성이 같은지 비교한다.
                // 여기서는 subset 에 속하는 속성들만 같은지 비교한다.
                for (int k = 0; k < colSize; k++) {
                    // subset 의 k 번째 비트가 0이라면 subset 에 k번째 속성이 포함되지 않았음을 의미한다.
                    // k번째 비트를 켠다는 것을 뜻한다.
                    // 따라서 이 경우에는 0. -> skip.
                    if ((subset & 1 << k) == 0) continue;

                    // a 튜플의 k 속성과 b 튜플의 k 속성이 다른지 확인!
                    // 다르면, 더 이상 볼 필요가 없다.
                    // 하나라도 다르면 두 튜플이 다른 것으로 식별되기 때문에
                    if (!relation[a][k].equals(relation[b][k])) {
                        isSame = false;
                        break;
                    }
                }

                // isSame 값이 true 라는 것은
                // 두 튜플이 한번도 다른 적이 없다. 두 튜플이 같다는 것이고 즉, 이는 k 라는 속성으로 두 튜플을 구분할 수 없다.
                // 따라서 이는 유일성을 만족하지 못한다.
                if (isSame) return false;

                // 만약, 여기서 리턴되지 않고 하나라도 달라서 false 로 내려왔다면.

            }
        }

        // 여기까지 내려왔다면 위에서 false 로 반환을 하지 않았으므로
        // 모든 튜플이 속성으로 구분이 된다는 것을 뜻함. 따라서 유일성을 만족!
        return true;
    }

    private static void combination(int n, int r, int index, int target) {
        if (r == 0) {
            for (String[] tuple : map) {
                for (int a : set) System.out.print(tuple[a] + " ");
                System.out.println();
            }
            return;
        }

        if (n == target) return;


        set[index] = target;
        combination(n, r - 1, index + 1, target + 1);
        combination(n, r, index, target + 1);
    }
}
