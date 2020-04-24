package 카카오20;

/**
 * created by victory_woo on 2020/04/24
 * 카카오 20 기출.
 * 외벽 점검.
 */
public class Test6_2 {
    public static void main(String[] args) {
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution(12, weak, dist));

        int[] weak2 = {1, 3, 4, 9, 10};
        int[] dist2 = {3, 5, 7};
        System.out.println(solution(12, weak2, dist2));
    }

    private static int n, num = 0;
    private static int[] choice;
    private static int[][] rotateWeak;
    private static boolean finish = false;

    public static int solution(int len, int[] weak, int[] dist) {
        n = len;
        setWeak(weak);

        boolean[] visit;
        for (int i = 1; i <= dist.length; i++) {
            num = i;
            choice = new int[num];
            visit = new boolean[dist.length];
            permutation(0, visit, dist);
            if (finish) break;
        }

        return finish ? num : -1;
    }

    // 순열을 통해서 외벽을 점검할 친구들을 선택한다.
    private static void permutation(int depth, boolean[] visit, int[] dist) {
        if (finish) return;
        if (depth == num) {
            check();
            //print();
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                choice[depth] = dist[i];
                permutation(depth + 1, visit, dist);
                visit[i] = false;
            }
        }
    }

    private static void check() {
        for (int[] weak : rotateWeak) {
            int index = 0, start = 0;
            boolean[] visit = new boolean[weak.length];

            while (index != num) {
                int i = start;
                int value = choice[index++];

                for (int j = start; j < weak.length; j++) {
                    if (weak[i] <= weak[j] && weak[j] <= weak[i] + value) {
                        visit[j] = true;
                        start++;
                    } else {
                        break;
                    }
                }
            }

            if (isFinish(visit)) {
                finish = true;
                return;
            }
        }
    }

    private static boolean isFinish(boolean[] visit) {
        for (boolean flag : visit) {
            if (!flag) return false;
        }
        return true;
    }

    private static void setWeak(int[] weak) {
        int n = weak.length;
        rotateWeak = new int[n][n];

        for (int i = 0; i < n; i++) {
            rotateWeak[i] = rotate(weak, i);
        }
    }

    private static int[] rotate(int[] weak, int index) {
        int len = weak.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            if (i + index < len) result[i] = weak[i + index];
            else result[i] = weak[i + index - len] + n;
        }
        return result;
    }

    // 순열 확인용. 순열을 확인하기 위해서는 check() 안에서 finish 변수를 false 로 만들어야 함.
    private static void print() {
        for (int a : choice) System.out.print(a + " ");

        System.out.println();
    }
}
