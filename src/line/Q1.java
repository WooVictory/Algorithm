package line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/17
 * 19년도 상반기 라인 코딩 테스트
 * 브라운과 코니.
 */
public class Q1 {
    private static final int MAX = 300000;
    private static int[] map, from;
    private static boolean[] visit;
    private static int C, B;
    private static int[] state = {-1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        C = toInt(in[0]);
        B = toInt(in[1]);

        map = new int[MAX + 1];
        from = new int[MAX + 1];
        visit = new boolean[MAX + 1];

        solve();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= MAX; i++) {
            int position = map[i];

            if (0 <= position && position <= MAX) {
                if (i == from[position]) result = i;
            }
        }

        System.out.println((result != Integer.MAX_VALUE) ? result : -1);
    }

    private static void solve() {
        moveCony();

        // 브라운의 위치는 bfs()를 이용해 구한다.
        // from 배열을 통해서 해당 좌표에 오기까지 얼만큼의 시간이 걸렸는지 저장한다.
        LinkedList<Integer> q = new LinkedList<>();
        q.add(B);
        visit[B] = true;
        int next;

        while (!q.isEmpty()) {
            int now = q.remove();

            for (int i = 0; i < 3; i++) {
                if (i == 2) next = now * state[i];
                else next = now + state[i];

                if (0 <= next && next <= MAX) {
                    if (!visit[next]) {
                        visit[next] = true;
                        from[next] = from[now] + 1;
                        q.add(next);
                    }
                }
            }
        }
    }

    // map 배열에는 시간에 따른 코니의 위치가 저장된다.
    // 코니의 위치는 매초 가속도가 붙어 이전 이동거리+1이 된다.
    // 따라서 이는 수열의 특징을 갖고 있어 코니의 위치를 미리 구한다.
    private static void moveCony() {
        map[0] = C;
        for (int i = 1; i <= MAX; i++) map[i] = map[i - 1] + i;
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}