package 카카오20;

/**
 * created by victory_woo on 2020/04/23
 * 카카오 20 기출.
 * 외벽 점검.
 */
public class Test6 {
    public static void main(String[] args) {
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        int[] weak2 = {1, 3, 4, 9, 10};
        int[] dist2 = {3, 5, 7};
        System.out.println(solution(12, weak, dist));
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
            num = i; // 몇 명을 뽑을 순열을 만들지 정한다.
            choice = new int[num]; // 순열을 통해서 선택된 친구들만 담긴다.
            visit = new boolean[dist.length];
            permutation(0, visit, dist);
            if (finish) break;
        }
        return finish ? num : -1;
    }

    // 순열을 통해서 점검을 할 친구들을 선택한다.
    // 누가 먼저 점검할지도 중요하기 때문에 조합이 아닌 순서가 있는 순열을 사용한다.
    private static void permutation(int depth, boolean[] visit, int[] dist) {
        if (finish) return;
        if (depth == num) {
            check();
            // 순열 확인용.
            //print();
            return;
        }

        // 순열을 만든다.
        for (int i = 0; i < dist.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                choice[depth] = dist[i];
                permutation(depth + 1, visit, dist);
                visit[i] = false;
            }
        }
    }

    // 회전하여 시작 번호를 다르게 한 weak 배열들이 모두 rotateWeak 배열에 저장되어 있다.
    // 따라서 rotateWeak 배열에서 시작 번호를 다르게 한 취약 지점에 대해서 순열로 만들어진 친구들이 점검한다.
    private static void check() {
        for (int[] weak : rotateWeak) {
            // index : 순열을 통해서 선택한 친구의 수만큼만 반복할 수 있도록 한다.
            // num : 순열을 통해 선택된 친구의 수.
            // start : 이전에 어디까지 비교했는지 저장한다.
            int index = 0, start = 0;
            boolean[] visit = new boolean[weak.length];

            // 선택된 친구의 수만큼만 반복한다.
            while (index != num) {
                int i = start; // 이전에 종료된 지점을 기억한다.
                int value = choice[index++]; // 친구 한 명을 꺼낸다.


                for (int j = start; j < weak.length; j++) {
                    // i가 j보다 작거나 같고 i 위치에서 친구가 이동할 수 있는 거리를 더해 j를 지날 수 있는지 확인한다.
                    // 지날 수 없다면 반복문을 나간다.
                    if (!(weak[i] <= weak[j] && weak[j] <= weak[i] + value)) break;

                    // 친구가 i 위치에서 이동거리 만큼 움직여 j 위치를 지날 수 있으면 visit 배열에 가능함을 표시하고
                    // start 는 현재 위치는 비교했으니, 다음 위치를 저장하기 위해 1을 증가시킨다.
                    visit[j] = true;
                    start++;
                }
            }

            // visit 배열이 모두 true 로 체크되어 있다면, 친구 몇 명을 뽑아서 외벽을 모두 점검했음을 나타낸다.
            if (isFinish(visit)) {
                finish = true;
                return;
            }
        }
    }

    // weak 배열을 0 ~ n-1 번 회전하여 rotateWeak 배열에 저장한다.
    private static void setWeak(int[] weak) {
        int len = weak.length;
        rotateWeak = new int[len][len];

        // i개씩 뒤로 밀어서 weak 배열을 회전시킨다.
        for (int i = 0; i < len; i++) {
            rotateWeak[i] = rotate(weak, i);
        }
    }

    // weak 배열을 나머지 번호에서도 시작할 수 있게끔 weak 배열을 i번 만큼 shift 하여 따로 저장해준다.
    // 인덱스를 넘어가는 번호는 그만큼 계산해준다.
    // i + index < len 을 만족하지 않는 경우에는 i + index - len 을 통해서 위치를 땡겨온다.
    // 그리고 n 만큼을 더해서 새로운 길이를 만들어준다.
    private static int[] rotate(int[] weak, int index) {
        int len = weak.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (i + index < len) result[i] = weak[i + index];
            else result[i] = weak[i + index - len] + n;
        }
        return result;
    }

    // 선택된 친구들로 외벽을 모두 점검했는지 확인한다.
    private static boolean isFinish(boolean[] visit) {
        for (boolean status : visit) {
            if (!status) return false;
        }
        return true;
    }

    // 순열 확인용.
    private static void print() {
        for (int a : choice) System.out.print(a + " ");
        System.out.println();
    }
}
