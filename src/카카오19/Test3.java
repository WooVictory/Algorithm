package 카카오19;

/**
 * created by victory_woo on 2020/04/25
 * 카카오 19 기출.
 * 후보키.
 */
public class Test3 {
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
    private static String[][] relations;

    public static int solution(String[][] relation) {
        int answer = 0;
        relations = relation;


        int n = relation[0].length;
        int r = 0;
        for (int i = 0; i < relation.length; i++) {
            r = i + 1;
            set = new int[r];
            combination(n, r, 0, 0);
        }
        return answer;
    }

    private static void combination(int n, int r, int index, int target) {
        if (r == 0) {
            for (int a : set) System.out.print(a + " ");
            System.out.println();

            check();
            return;
        }

        if (target == n) return;

        set[index] = target;
        combination(n, r - 1, index + 1, target + 1);
        combination(n, r, index, target + 1);
    }

    private static void check() {
        for (int attr : set) {
            String a = relations[0][attr];
            System.out.print(a + ", ");
            for (String[] row : relations) {

            }
        }
        System.out.println();
    }
}
