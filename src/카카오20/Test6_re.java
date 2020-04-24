package 카카오20;

/**
 * created by victory_woo on 2020/04/23
 * 카카오 20 기출.
 * 외벽 점검.
 */
public class Test6_re {
    public static void main(String[] args) {
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution(12, weak, dist));
        int[] weak2 = {1, 3, 4, 9, 10};
        int[] dist2 = {3, 5, 7};
        System.out.println(solution(12, weak2, dist2));
    }

    private static int n, num = 0;
    private static boolean finish = false;
    private static int[] choice;
    private static int[][] rotateWeak;

    public static int solution(int len, int[] weak, int[] dist) {
        n = len;
        setWeak(weak);

        boolean[] visit;
        for (int i = 1; i <= dist.length; i++) {
            num = i; // i명의 친구를 뽑는 순열을 만든다.
            choice = new int[num]; // 순열을 통해 선택한 친구들을 담을 배열.
            visit = new boolean[dist.length];
            permutation(0, visit, dist);
            if (finish) break;
        }

        return finish ? num : -1;
    }

    // 순열을 통해 외벽을 점검할 친구들을 순서를 포함하여 뽑는다.
    private static void permutation(int depth, boolean[] visit, int[] dist) {
        if (finish) return;

        // 친구가 모두 선택되면 선택된 친구들을 데리고 외벽을 점검하러 간다.
        if (depth == num) {
            check();
            return;
        }

        // 순열을 통해 친구를 선택한다.
        for (int i = 0; i < dist.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                choice[depth] = dist[i];
                permutation(depth + 1, visit, dist);
                visit[i] = false;
            }
        }
    }

    // 외벽을 점검한다.
    // 외벽은 시작번호를 다 다르게 하여 rotateWeak 배열에 저장되어 있다.
    // 이렇게 시적번호가 다른 외벽을 선택된 친구들로 점검한다.
    private static void check() {
        for (int[] weak : rotateWeak) {
            int index = 0, start = 0;
            boolean[] visit = new boolean[weak.length];

            // 선택된 친구들의 수만큼 반복한다.
            while (index != num) {
                int i = start; // start : 이전에 어디까지 비교했는지 저장한다.
                int value = choice[index++]; // 친구 한명을 뽑는다.

                // 이전에 비교했던 지점부터 weak 배열의 끝까지 반복한다.
                for (int j = start; j < weak.length; j++) {
                    // i<=j 이고 i 외벽 위치에서 친구가 이동할 수 있는 거리만큼 이동했을 때, j 외벽의 위치를 지나가면
                    // j 지점의 외벽을 점검할 수 있음을 뜻한다.
                    // 그리고 start 값을 증가시켜 다음 지점의 위치를 저장한다.
                    if (weak[i] <= weak[j] && weak[j] <= weak[i] + value) {
                        visit[j] = true;
                        start++;
                    } else {
                        // 점검할 수 없을 때는 반복문을 빠져나와 다른 친구를 선택한다.
                        break;
                    }
                }
            }

            // 모든 외벽을 점검했는지 확인한다.
            if (isFinish(visit)) {
                finish = true;
                return;
            }
        }
    }

    // 모든 외벽을 점검했는지 확인한다. 모든 외벽을 점검했으면 visit 배열의 모든 값이 true 이다.
    private static boolean isFinish(boolean[] visit) {
        for (boolean flag : visit) {
            if (!flag) return false;
        }

        return true;
    }

    // weak 배열을 1 ~ n-1번 회전시킨다.
    // 이를 통해 시작점이 다른 weak 배열을 만들어 rotateWeak 배열에 저장한다.
    private static void setWeak(int[] weak) {
        int len = weak.length;
        rotateWeak = new int[len][len];

        // weak 배열은 index 만큼 shift 한다.
        for (int i = 0; i < len; i++) {
            rotateWeak[i] = rotate(weak, i);
        }
    }

    // i+index<len 인 경우는 배열의 길이를 넘어가지 않아 인덱스를 벗어나지 않으므로 index 만큼 땡겨온다는 것이다.
    // i+index<len 이 아닌 경우는 i+index-len 을 통해서 weak 의 길이를 벗어나고 한바퀴 돌아 원래 오려는 위치에 맞춰준다.
    // 즉, 다시 길이만큼 빼주어 땡겨온다. 그리고 n을 더해 새로운 길이를 만들어준다.
    private static int[] rotate(int[] weak, int index) {
        int len = weak.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            if (i + index < len) result[i] = weak[i + index];
            else result[i] = weak[i + index - len] + n;
        }

        return result;
    }
}
